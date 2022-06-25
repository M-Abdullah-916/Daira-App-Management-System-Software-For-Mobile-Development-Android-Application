package com.example.dairaapp.Model;

public class SubEvent {
    String subEventName, subEventDesc, subEventParent, subImageKey;
    String key;

    public SubEvent() { }

    public SubEvent(String subEventName, String subEventDesc, String subEventParent, String subImageKey) {
        this.subEventName = subEventName;
        this.subEventDesc = subEventDesc;
        this.subEventParent = subEventParent;
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

    public String getSubEventParent() {
        return subEventParent;
    }

    public void setSubEventParent(String subEventParent) {
        this.subEventParent = subEventParent;
    }

    public String getSubImageKey() {
        return subImageKey;
    }

    public void setSubImageKey(String subImageKey) {
        this.subImageKey = subImageKey;
    }
}
