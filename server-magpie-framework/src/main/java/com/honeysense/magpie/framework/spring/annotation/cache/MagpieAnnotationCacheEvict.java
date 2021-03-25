package com.honeysense.magpie.framework.spring.annotation.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MagpieAnnotationCacheEvict {
    String namespace() default "";

    String key() default "";
}
