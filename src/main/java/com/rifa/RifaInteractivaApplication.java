package com.rifa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RifaInteractivaApplication {
    public static void main(String[] args) {
        SpringApplication.run(RifaInteractivaApplication.class, args);
    }
}