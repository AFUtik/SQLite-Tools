package com.afutik.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserEntity {
    private int id;
    private String name;
    private String username;
    private String password; /* Encrypted */
}
