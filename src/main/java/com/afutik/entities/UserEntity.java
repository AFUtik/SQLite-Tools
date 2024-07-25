package com.afutik.entities;

import com.afutik.annotations.Key;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity implements Entity {
    @Key
    private Integer id;
    private String name;
    private String username;
    private String password; /* Encrypted */
}
