package com.example.ProyectoFinal.dao;

import com.example.ProyectoFinal.model.Event;

import java.util.List;

public interface IEventDao {
    Event saveEvent(Event even);
    List<Event> getEvents();
    Event getEvent(int event_id);
    boolean deleteEvent(int eventId);
    boolean updateEvent(Event event);

}
