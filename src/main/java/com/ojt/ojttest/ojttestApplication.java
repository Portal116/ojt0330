package com.ojt.ojttest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ojttestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ojttestApplication.class, args);
        System.out.println("run");
    }
}
