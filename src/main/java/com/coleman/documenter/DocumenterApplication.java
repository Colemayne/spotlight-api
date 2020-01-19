package com.coleman.documenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.coleman.documenter")
@ComponentScan("com.coleman.documenter")
public class DocumenterApplication{
    public static void main(String[] args) {
        SpringApplication.run(DocumenterApplication.class, args);
    }
}
