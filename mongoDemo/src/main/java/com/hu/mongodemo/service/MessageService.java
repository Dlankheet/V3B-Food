package com.hu.mongodemo.service;

import com.hu.mongodemo.domain.Log;
import com.hu.mongodemo.domain.LogRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements MessageInterface {
    private final LogRepository logRepository;

    public MessageService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public String showLove(String message) {
        if (message == null || message.length() == 0) {
            return "Hello my testtest!";
        }
        String result = String.format("%s my test.", message);

        Log log = new Log();
        log.setMessage(message);
        logRepository.save(log);

        return result;
    }
}
