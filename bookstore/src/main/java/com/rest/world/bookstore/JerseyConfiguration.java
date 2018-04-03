package com.rest.world.bookstore;

import com.rest.world.bookstore.annotations.RestService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

/**
 * Created by Dave on 3/4/18.
 */

@Configuration
@ApplicationPath("rest")
public class JerseyConfiguration extends ResourceConfig {

    @Inject
    private ApplicationContext context;

    @PostConstruct
    public void setUp() {
        context.getBeansWithAnnotation(RestService.class).forEach( (name, object) -> register(object));

    }
}
