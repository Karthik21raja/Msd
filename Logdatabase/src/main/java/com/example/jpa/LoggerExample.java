package com.example.jpa;

import java.util.logging.Logger;

public class LoggerExample {

    private static final Logger logger = Logger.getLogger(LoggerExample.class.getName());

    public static void main(String[] args) {
       
        logger.addHandler(new DatabaseLogHandler());

        
        logger.setLevel(java.util.logging.Level.ALL);

      
        logger.severe("This is a SEVERE log message");
        logger.warning("This is a WARNING log message");
        logger.info("This is an INFO log message");
        logger.fine("This is a FINE log message");
    }
}
