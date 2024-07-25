package com.afutik.entities;

import com.afutik.annotations.Key;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskEntity implements Entity {
    @Key
    private Integer id;
    private String title;
}
