package com.spring;

import com.spring.log.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class SpringLogShipperApplication {

    @Autowired
    private LogService logService;

    public static void main(String[] args) {
        SpringApplication.run(SpringLogShipperApplication.class, args);
    }
}
