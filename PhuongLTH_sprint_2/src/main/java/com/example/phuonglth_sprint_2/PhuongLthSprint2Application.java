package com.example.phuonglth_sprint_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class PhuongLthSprint2Application {

    public static void main(String[] args) {
        SpringApplication.run(PhuongLthSprint2Application.class, args);
    }

}
