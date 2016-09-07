package com.tesco.automation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class BatchApplication {		
    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);        
    }   
}
