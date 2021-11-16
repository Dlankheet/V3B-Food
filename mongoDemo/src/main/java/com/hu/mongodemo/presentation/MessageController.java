package com.hu.mongodemo.presentation;

import com.hu.mongodemo.domain.LogRepository;
import com.hu.mongodemo.domain.MessageInput;
import com.hu.mongodemo.domain.MessageOutput;
import com.hu.mongodemo.service.MessageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageInterface messageService;

    public MessageController(MessageInterface messageService) {
        this.messageService = messageService;
    }

    @GetMapping("index")
    public MessageOutput showLove(MessageInput input){
        MessageOutput result = new MessageOutput();
        String outputMessage = messageService.showLove(input.getMessage());
        result.setMessage(outputMessage);
        return result;
    }
}
