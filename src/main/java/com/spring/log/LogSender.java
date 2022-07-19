package com.spring.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class LogSender {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Async
    public void sendData(ArrayList<byte[]> dataToSend) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<String>(objectMapper.writeValueAsString(dataToSend), headers);

        ResponseEntity<Void> responseEntityStr =
                restTemplate.exchange("https://webhook.site/a3dccd95-2a48-4303-9cd7-6f497c828172", HttpMethod.POST, request, Void.class);

        if (responseEntityStr.getStatusCode().is2xxSuccessful()) {
            System.out.println("Sent succeeded " + dataToSend.size());
        } else {
            System.out.println("Sent failed");
        }
    }
}