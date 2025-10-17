package com.faza.example.ddr.swj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringWebJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebJdbcApplication.class, args);
    }
}
