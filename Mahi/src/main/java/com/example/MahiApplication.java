package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan(basePackages = {"com.control", "com.serv"})
public class MahiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MahiApplication.class, args);
        System.out.println("Start..........");
    }
}