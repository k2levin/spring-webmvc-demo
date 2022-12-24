package com.k2studio.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:properties/springdoc.properties")
@ComponentScan(basePackages = {"org.springdoc"})
@Import({
    org.springdoc.webmvc.ui.SwaggerConfig.class,
    org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration.class,
})
public class SpringDocOpenApiConfig {
    //
}
