package com.example.dairaapp.Model;

public class Event {
    String eventName, eventDesc, eventDate, imageKey;
    String key;

    public Event(){}


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Event(String eventName, String eventDesc, String eventDate, String imageKey) {
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.eventDate = eventDate;
        this.imageKey = imageKey;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }
}
