package com.example.tasklet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TaskletApplication {

    public static void main(String[] args) {
    	ApplicationContext context = new ClassPathXmlApplicationContext("batch-config.xml");
        System.out.println("Tasklet Application started");
    }
}
