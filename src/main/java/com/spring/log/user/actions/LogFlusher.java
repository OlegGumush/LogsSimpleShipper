package com.spring.log.user.actions;

import com.spring.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LogFlusher {

    @Autowired
    private LogService logService;

    @Scheduled(fixedDelay = 10000)
    public void flush() throws InterruptedException {

        System.out.println("Flush!!!!!!");
        logService.flush();
    }
}