package com.spring.log.user.actions;

import com.spring.log.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogWriter {

    @Autowired
    private LogService logService;

    @Scheduled(fixedDelay = 10)
    public void scheduleFixedDelayTask() throws InterruptedException {

        LocalDateTime time = LocalDateTime.now();
        String log = String.format("%s [main] Log log log log log log log log\n", time);
        logService.log_data(log.getBytes());
    }
}