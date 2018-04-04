package com.rest.world.commons.config;

import com.rest.world.commons.annotations.RestService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;


@Configuration
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

    @Inject
    private ApplicationContext context;

    @PostConstruct
    public void setUp() {
        context.getBeansWithAnnotation(RestService.class).forEach((name, object) -> register(object));
    }
}