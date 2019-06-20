package com.webpay.workpay.service;

import com.webpay.workpay.domain.Message;
import com.webpay.workpay.repository.MessageRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public ArrayList<Message> allMessages () {
        ArrayList<Message> all = (ArrayList<Message>) messageRepo.findAll();
        Collections.reverse(all);
        return all;
    }

    public void save (Message message) {
        messageRepo.save(message);
    }

    public HashSet<String> allTags () {
        List<String> temp = new ArrayList<>();
        for (Message message:
             messageRepo.findAll()) {
            temp.add(message.getTag());
        }
        HashSet<String> set = new HashSet<>(temp);
        return set;
    }

    public void delMessage (Message message) {
        messageRepo.delete(message);
    }
}
