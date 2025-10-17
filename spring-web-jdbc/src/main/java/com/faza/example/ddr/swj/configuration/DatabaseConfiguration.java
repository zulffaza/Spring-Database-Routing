package com.faza.example.ddr.swj.configuration;

import com.faza.example.ddr.swj.model.properties.RoutingDataSourceProperties;
import com.faza.example.ddr.swj.repository.configuration.MdcRoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;
import java.util.Map;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public Map<Object, Object> dataSourceMap(RoutingDataSourceProperties routingDataSourceProperties) {
        return routingDataSourceProperties.getTargetDataSources()
            .entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey,
                entry -> new HikariDataSource(entry.getValue())));
    }

    @Bean
    @Primary
    public DataSource dataSource(RoutingDataSourceProperties routingDataSourceProperties,
        Map<Object, Object> dataSourceMap) {
        MdcRoutingDataSource mdcRoutingDataSource = new MdcRoutingDataSource();
        mdcRoutingDataSource.setDefaultTargetDataSource(dataSourceMap.get(
            routingDataSourceProperties.getDefaultTargetDataSource()));
        mdcRoutingDataSource.setTargetDataSources(dataSourceMap);
        return mdcRoutingDataSource;
    }
}
