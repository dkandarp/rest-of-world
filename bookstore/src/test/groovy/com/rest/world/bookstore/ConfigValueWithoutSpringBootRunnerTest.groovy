package com.rest.world.bookstore

import com.sun.xml.internal.fastinfoset.algorithm.BASE64EncodingAlgorithm
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.PropertySource
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver

/**
 * Created by Dave on 1/7/18.
 */
class ConfigValueWithoutSpringBootRunnerTest {
    def context

    @Before
    void setup(){
        context= new AnnotationConfigApplicationContext()
        context.register(ConfigComponent)
        context.register(TestConfig)
        context.refresh()
    }

    @Test
    void "print value"(){
        def component = context.getBean(ConfigComponent)
        println component.configValue
    }


    static class TestConfig{
        @Bean
        PropertySourcesPlaceholderConfigurer configurerSource(){
            PropertySourcesPlaceholderConfigurer  propertySourcesPlaceholderConfigurer= new PropertySourcesPlaceholderConfigurer()
            YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
            def resources = new PathMatchingResourcePatternResolver()
            yaml.setResources(resources.getResources("classpath*:/*.yaml"));
            propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
            return propertySourcesPlaceholderConfigurer;
        }

    }
}
