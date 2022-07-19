package com.spring.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.log.tasks.FlushEvent;
import com.spring.log.tasks.LogEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Vector;

@Component
public class LogHandler {

    private Vector<byte[]> cache = new Vector<>();
    private static final int bulk = 15000;
    public int bytesInCache = 0;

    @Autowired
    private LogSender logSender;

    @EventListener
    public void logTask(LogEvent event) throws JsonProcessingException {

        synchronized (cache) {
            System.out.println("Log data " + event.data + " cache size " + cache.size());
            if (bytesInCache + event.data.length > bulk) {
                System.out.println("Flush from send!!!!");
                flushTask(new FlushEvent());
            }
            bytesInCache += event.data.length;
            cache.add(event.data);
        }
    }

    @EventListener
    public void flushTask(FlushEvent flushTask) throws JsonProcessingException {

        synchronized (cache) {
            if (cache.isEmpty()) {
                return;
            }
            ArrayList<byte[]> dataToSend = new ArrayList<>(cache);
            cache.clear();
            bytesInCache = 0;
            logSender.sendData(dataToSend);
        }
    }
}
