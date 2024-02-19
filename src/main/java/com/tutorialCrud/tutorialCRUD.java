package com.tutorialCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tutorialCrud")
public class tutorialCRUD {
    public static void main(String[] args) {
        SpringApplication.run(tutorialCRUD.class, args);
    }
}
