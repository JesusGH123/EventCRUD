package com.example.ProyectoFinal.dao;

import com.example.ProyectoFinal.model.Event;

import java.util.List;

public interface IEventDao {
    Event saveEvent(Event even);
    List<Event> getEvents();
    boolean deleteEvent(int eventId);
    boolean updateEvent(Event event);

}
