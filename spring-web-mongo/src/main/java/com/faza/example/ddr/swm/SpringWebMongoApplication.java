package com.faza.example.ddr.swm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringWebMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebMongoApplication.class, args);
    }
}
