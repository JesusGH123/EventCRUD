package com.example.ProyectoFinal.model;

import java.io.Serializable;

public class EventLimit implements Serializable {
    private int event_id, attendance_limit;

    public EventLimit() {
    }
    public EventLimit(int attendance_limit) {
        this.attendance_limit = attendance_limit;
    }

    public int getAttendance_limit() {
        return attendance_limit;
    }
    public void setAttendance_limit(int attendance_limit) {
        this.attendance_limit = attendance_limit;
    }

    @Override
    public String toString() {
        return "EventLimit{" +
                "event_id=" + event_id +
                ", attendance_limit=" + attendance_limit +
                '}';
    }
}
