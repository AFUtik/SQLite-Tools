package com.afutik.annotation;

import com.afutik.entity.Entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Repo {
    Class<? extends Entity> entityClass() default Entity.class;
    String table() default "";
}
