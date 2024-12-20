package com.example.Msd;







import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class MsdApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsdApplication.class, args);
		Logger logger = Logger.getLogger(MsdApplication.class.getName());
		logger.info("Starting.............");
	}

}

