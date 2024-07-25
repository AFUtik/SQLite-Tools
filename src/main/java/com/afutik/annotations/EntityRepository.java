package com.afutik.annotations;

import com.afutik.entities.Entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EntityRepository {
    Class<? extends Entity> entityClass() default Entity.class;
    String table() default "";
}
