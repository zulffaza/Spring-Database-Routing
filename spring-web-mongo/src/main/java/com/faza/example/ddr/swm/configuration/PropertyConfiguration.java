package com.faza.example.ddr.swm.configuration;

import com.faza.example.ddr.swm.model.properties.RoutingDataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.routing.datasource")
    public RoutingDataSourceProperties routingDataSourceProperties() {
        return new RoutingDataSourceProperties();
    }
}
