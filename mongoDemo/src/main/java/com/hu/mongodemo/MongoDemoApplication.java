package com.hu.mongodemo;

import com.hu.mongodemo.domain.LogRepository;
import com.hu.mongodemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MongoDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongoDemoApplication.class, args);
    }

    @Autowired
    private LogRepository logRepository;

    @Bean
    public MessageService MessageService() {
        return new MessageService(logRepository);
    }
}
