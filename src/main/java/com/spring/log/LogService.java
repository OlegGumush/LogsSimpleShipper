package com.spring.log;

import com.spring.log.tasks.FlushEvent;
import com.spring.log.tasks.LogEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private boolean isClosed = false;

    public void log_data(byte[] data) throws InterruptedException {

        if (isClosed) {
            throw new RuntimeException("Log is closed");
        }
        applicationEventPublisher.publishEvent(new LogEvent(data));
    }

    public void flush() throws InterruptedException {

        applicationEventPublisher.publishEvent(new FlushEvent());
    }

    public void close() {
        isClosed = true;
    }
}