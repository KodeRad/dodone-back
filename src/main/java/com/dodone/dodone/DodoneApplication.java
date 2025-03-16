package com.dodone.dodone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DodoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(DodoneApplication.class, args);
    }

}
