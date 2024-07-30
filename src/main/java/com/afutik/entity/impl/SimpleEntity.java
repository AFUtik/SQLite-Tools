package com.afutik.entity.impl;

import com.afutik.annotation.Key;
import com.afutik.annotation.Value;
import com.afutik.entity.Entity;

import java.sql.Timestamp;

/*
    You need place the variables in the same order as the schema.
    You can use @Key(name = name in schema) or @Value(name = name in schema) if you need another name for a variable.
    Using of Key is required.
    Using of @Value is optional.
*/
public class SimpleEntity implements Entity {
    @Key(name = "name")
    private final String name;

    @Value(name = "created_time")
    private final Timestamp time;

    public SimpleEntity(String name, Timestamp time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Timestamp getTime() {
        return time;
    }
}
