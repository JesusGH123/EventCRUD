package com.example.ProyectoFinal.dao;

import com.example.ProyectoFinal.model.Event;
import com.example.ProyectoFinal.utility.MySQLConnection;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDao implements IEventDao {
    private final String SAVE_EVENT = "call create_event(?,?,?,?,?,?)"
            ,GET_ALL_EVENTS = "call get_all_events()"
            ,GET_EVENT = "call get_event(?)"
            ,DELETE_EVENT ="call delete_event(?)"

            ,SAVE_ATTENDANCE_LIMIT = "call create_event_attendance_limit(?,?)"


            //,SAVE_EVENT_IMAGE = "call create_event_image(?,?,?,?,?)"
            //,GET_EVENT_IMAGE = "call get_event_image(?)"

            ,UPDATE_EVENT = "call update_event(?,?,?,?,?,?,?)"
            ,UPDATE_ATTENDANCE_LIMIT="call update update_event_attendance_limit(?,?)"
            ,UPDATE_EVENT_IMAGE="call update_event_image(?,?,?,?,?)"

            ,ATTENDANCE_CHANGE = "CALL attendance_change(?,?,?)";
    @Override
    public Event saveEvent(Event event) {
        try{
            Connection connection = MySQLConnection.getConnection();

            //insert in Event Table

            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_EVENT, Statement.RETURN_GENERATED_KEYS);
            //preparedStatement.setInt(1,event.getEvent_id());
            preparedStatement.setString(1,event.getTitle());
            preparedStatement.setString(2,event.getDescription());
            preparedStatement.setString(3,event.getCategory());
            preparedStatement.setTimestamp(4,new Timestamp(event.getBegin_date().getTime()));
            preparedStatement.setTimestamp(5,new Timestamp(event.getEnd_date().getTime()));
            preparedStatement.setFloat(6,event.getPrice());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int event_id = resultSet.getInt(1);

            if(event.getAttendance_limit()!=0) {
                //insert in Event Limit Table

                preparedStatement = connection.prepareStatement(SAVE_ATTENDANCE_LIMIT);
                preparedStatement.setInt(1, event_id);
                preparedStatement.setInt(2, event.getAttendance_limit());
                preparedStatement.executeUpdate(); //not sure!
            }

            event.setEvent_id(event_id);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return event;
    }

    @Override
    public List<Event> getEvents() {
        List<Event> events = new ArrayList();
        try{
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EVENTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Event event = new Event();
                event.setEnd_date(resultSet.getTimestamp("end_date"));
                event.setBegin_date(resultSet.getTimestamp("begin_date"));
                event.setPrice(resultSet.getFloat("price"));
                event.setCategory(resultSet.getString("category"));
                event.setDescription(resultSet.getString("description"));
                event.setEvent_id(resultSet.getInt("event_id"));
                event.setTitle(resultSet.getString("title"));
                events.add(event);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return events;
    }

    @Override
    public Event getEvent(int event_id) {
        try{
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_EVENT);
            preparedStatement.setInt(1,event_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ///ADD attendance limit and image LATER Event
            if(resultSet.next()) {
                Event event = new Event();
                event.setEvent_id(event_id);
                event.setTitle(resultSet.getString("title"));
                event.setDescription(resultSet.getString("description"));
                event.setCategory(resultSet.getString("category"));
                event.setPrice(resultSet.getFloat("price"));
                event.setBegin_date(resultSet.getTimestamp("begin_date"));
                event.setEnd_date(resultSet.getTimestamp("end_date"));
                ///event.setAttendance_limit();
                return event;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteEvent(int event_id) {
        try{
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EVENT);
            preparedStatement.setInt(1,event_id);
            int count = preparedStatement.executeUpdate();
            return count>0;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateEvent(Event event) {
        try{
            //ADD TRANSACTION STATEMENTS
            Connection connection = MySQLConnection.getConnection();

            //insert in Event Table

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EVENT);
            preparedStatement.setInt(1,event.getEvent_id());
            preparedStatement.setString(2,event.getTitle());
            preparedStatement.setString(3,event.getDescription());
            preparedStatement.setString(4,event.getCategory());
            preparedStatement.setTimestamp(5,new Timestamp(event.getBegin_date().getTime()));
            preparedStatement.setTimestamp(6,new Timestamp(event.getEnd_date().getTime()));
            preparedStatement.setFloat(7,event.getPrice());
            int affected_rows = preparedStatement.executeUpdate(),event_id = event.getEvent_id();
            if(affected_rows==0)
                return false;
            if(event.getAttendance_limit()!=0) {
                //insert in Event Limit Table
                preparedStatement = connection.prepareStatement(UPDATE_ATTENDANCE_LIMIT);
                preparedStatement.setInt(1,event_id);
                preparedStatement.setInt(2, event.getAttendance_limit());
                affected_rows = preparedStatement.executeUpdate();
                if(affected_rows==0)
                    return false;
                System.out.println(affected_rows);
            }

            /*if(event.getImage()!=null) {
                ///insert in Event Image Table
                preparedStatement = connection.prepareStatement(UPDATE_EVENT_IMAGE);
                preparedStatement.setInt(1, event_id);
                Event.EventImage event_image = event.getImage();
                preparedStatement.setString(2, event_image.getName());
                preparedStatement.setString(3, event_image.getType());
                preparedStatement.setDouble(4, event_image.getSize());
                preparedStatement.setString(5, event_image.getContent());
                affected_rows = preparedStatement.executeUpdate();
                if(affected_rows==0)
                    return false;
                System.out.println(affected_rows);
            }*/
            return true;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean changeAttendance(int user_id, int event_id, boolean is_attending) {
        try{
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ATTENDANCE_CHANGE);
            preparedStatement.setInt(1,user_id);
            preparedStatement.setInt(2,event_id);
            preparedStatement.setBoolean(3,is_attending);
            return preparedStatement.executeUpdate() > 0;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
