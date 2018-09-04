package com.application.job.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main Application
 * 
 * @author BharatGoel
 *
 */

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.application.job.management")
public class Application {
    public static void main( String[] args ) {
    	SpringApplication.run(Application.class, args);
    }
}
