package com.taeyoon.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class AndalosApplication {

    public static void main(String[] args) {
        // timezone 설정
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(AndalosApplication.class, args);

        LocalDateTime now = LocalDateTime.now();
        System.out.println("현재시간 " + now);
    }

}
