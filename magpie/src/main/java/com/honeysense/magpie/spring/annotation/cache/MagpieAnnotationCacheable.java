package com.honeysense.magpie.spring.annotation.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MagpieAnnotationCacheable {
    String namespace() default "";

    String key();

    int maxAge();
}
