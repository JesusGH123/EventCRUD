package com.example.ProyectoFinal.model;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Event implements Serializable {
    public static class EventImage implements Serializable{
        private int event_id;
        private String name,type;
        private double  size;
        private InputStream content;

        public EventImage(){}

        public EventImage(int event_id, String name, String type, double size, InputStream content) {
            this.event_id = event_id;
            this.name = name;
            this.type = type;
            this.size = size;
            this.content = content;
        }

        public int getEvent_id() {
            return event_id;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public double getSize() {
            return size;
        }

        public InputStream getContent() {
            return content;
        }

        public void setEvent_id(int event_id) {
            this.event_id = event_id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setSize(double size) {
            this.size = size;
        }

        public void setContent(InputStream content) {
            this.content = content;
        }
    }

    private int event_id,attendance_limit;
    private String title, description, category;
    private Timestamp begin_date, end_date;
    private float price;
    private EventImage image;

    public Event() {

    }
    public Event(String title, String description, String category, Timestamp begin_date, Timestamp end_date, float price, int attendance_limit,EventImage image) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.price = price;
        this.attendance_limit = attendance_limit;
        this.image = image;
    }

    public int getEvent_id() {
        return event_id;
    }
    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Timestamp getBegin_date() {
        return begin_date;
    }
    public void setBegin_date(Timestamp begin_date) {
        this.begin_date = begin_date;
    }
    public Timestamp getEnd_date() {
        return end_date;
    }
    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getAttendance_limit() {
        return attendance_limit;
    }
    public void setAttendance_limit(int attendance_limit) {
        this.attendance_limit = attendance_limit;
    }
    public EventImage getImage(){
        return image;
    }
    public void setImage(EventImage image){
        this.image = image;
    }

    @Override
    public String toString() {
        return "Event{" +
                "event_id=" + event_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", begin_date=" + begin_date +
                ", end_date=" + end_date +
                ", price=" + price +
                ", event_id=" + event_id +
                ", attendance_limit=" + attendance_limit +
                '}';
    }
}
