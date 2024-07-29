package com.afutik.entity.impl;

import com.afutik.annotation.Key;
import com.afutik.annotation.Value;
import com.afutik.entity.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

/*
    You need place the variables in the same order as the schema.
    You can use @Key(name = name in schema) or @Value(name = name in schema) if you need another name for a variable.
    Using of Key is required.
    Using of @Value is optional.
*/
@AllArgsConstructor
@Getter
public class SimpleEntity implements Entity {
    @Key(name = "name")
    private String name;

    @Value(name = "created_time")
    private Timestamp time;
}
