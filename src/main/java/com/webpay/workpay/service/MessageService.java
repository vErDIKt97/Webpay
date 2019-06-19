package com.webpay.workpay.service;

import com.webpay.workpay.domain.Message;
import com.webpay.workpay.repository.MessageRepo;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Iterable<Message> allMessages () {
        return messageRepo.findAll();
    }

    public void save (Message message) {
        messageRepo.save(message);
    }

    public void delMessage (Message message) {
        messageRepo.delete(message);
    }
}
