package com.afutik.entities;

import com.afutik.annotations.Key;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskEntity extends Entity {
    @Key
    private Integer id;
    private String title;
}
