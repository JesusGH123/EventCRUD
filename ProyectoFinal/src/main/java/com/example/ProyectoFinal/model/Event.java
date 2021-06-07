package com.example.ProyectoFinal.model;

import java.io.Serializable;
import java.sql.Date;

public class Event implements Serializable {
    private int event_id;
    private String title, description, category;
    private Date begin_date, end_date;
    private float price;

    public Event() {
    }
    public Event(String title, String description, String category, Date begin_date, Date end_date, float price) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.begin_date = begin_date;
        this.end_date = end_date;
        this.price = price;
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
    public Date getBegin_date() {
        return begin_date;
    }
    public void setBegin_date(Date begin_date) {
        this.begin_date = begin_date;
    }
    public Date getEnd_date() {
        return end_date;
    }
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
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
                '}';
    }
}
