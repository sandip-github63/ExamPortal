package com.exam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamPortalApplication {

	private static final Logger logger = LogManager.getLogger(ExamPortalApplication.class);

	public static void main(String[] args) {

		logger.info("application started successfully....");

		SpringApplication.run(ExamPortalApplication.class, args);
	}
}
