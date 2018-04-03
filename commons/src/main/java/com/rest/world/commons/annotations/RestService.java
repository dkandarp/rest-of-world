package com.rest.world.commons.annotations;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Lazy
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RestService {
}
