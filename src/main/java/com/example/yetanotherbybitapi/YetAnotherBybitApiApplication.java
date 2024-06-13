package com.example.yetanotherbybitapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class YetAnotherBybitApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YetAnotherBybitApiApplication.class, args);
        log.info("work");
    }

}
