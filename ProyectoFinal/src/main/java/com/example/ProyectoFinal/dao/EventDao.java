package com.example.ProyectoFinal.dao;

import com.example.ProyectoFinal.model.Event;

import java.util.List;

public class EventDao implements IEventDao {
    private final String SAVE_EVENT = "call create_event(?,?,?,?,?,?)"
            ,GET_ALL_EVENTS = "call get_all_events()"
            ,DELETE_EVENT =""
            ,UPDATE_EVENT = ""
    @Override
    public Event saveEvent(Event even) {
        return null;
    }

    @Override
    public List<Event> getEvents() {
        return null;
    }

    @Override
    public boolean deleteEvent(int eventId) {
        return false;
    }

    @Override
    public boolean updateEvent(Event event) {
        return false;
    }
}
