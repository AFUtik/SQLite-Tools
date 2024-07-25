package com.afutik.entities;

import com.afutik.annotations.Key;
import com.afutik.annotations.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity extends Entity {
    @Key
    private Integer id;
    private String name;
    private String username;
    private String password; /* Encrypted */
}
