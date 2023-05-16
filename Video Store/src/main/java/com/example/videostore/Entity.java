package com.example.videostore;

import java.util.Calendar;

public class Entity {
    private String id;

    public Entity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws ItemError {
        this.id = id;
    }

    public boolean idValid(String id) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (id.length() != 9) {
            return false;
        } else if (!id.matches("I\\d{3}-\\d{4}")) {
            return false;
        }
        return true;
    }
}