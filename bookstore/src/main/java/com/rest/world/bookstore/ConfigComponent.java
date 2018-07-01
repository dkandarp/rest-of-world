package com.rest.world.bookstore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigComponent {

    @Value("${config.value:Default Value}")
     String configValue;

}
