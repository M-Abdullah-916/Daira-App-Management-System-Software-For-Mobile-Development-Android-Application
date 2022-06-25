package com.example.dairaapp.Model;

public class SubEvent {
    String subEventName, subEventDesc, subEventDate, subImageKey;
    String key;

    public SubEvent() { }

    public SubEvent(String subEventName, String subEventDesc, String subEventDate, String subImageKey) {
        this.subEventName = subEventName;
        this.subEventDesc = subEventDesc;
        this.subEventDate = subEventDate;
        this.subImageKey = subImageKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSubEventName() {
        return subEventName;
    }

    public void setSubEventName(String subEventName) {
        this.subEventName = subEventName;
    }

    public String getSubEventDesc() {
        return subEventDesc;
    }

    public void setSubEventDesc(String subEventDesc) {
        this.subEventDesc = subEventDesc;
    }

    public String getSubEventDate() {
        return subEventDate;
    }

    public void setSubEventDate(String subEventDate) {
        this.subEventDate = subEventDate;
    }

    public String getSubImageKey() {
        return subImageKey;
    }

    public void setSubImageKey(String subImageKey) {
        this.subImageKey = subImageKey;
    }
}
