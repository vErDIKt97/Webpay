package com.webpay.workpay.controller;

import com.webpay.workpay.domain.Message;
import com.webpay.workpay.domain.User;
import com.webpay.workpay.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/news")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Value("${path.img}")
    private String pathImg;

    @GetMapping
    public String newsViews(Model model) {
        if (messageService.allMessages() != null)
            model.addAttribute("messages", messageService.allMessages());
        return "news";
    }

    @GetMapping("/addNews")
    public String newsAddViews(Model model) {
        if (messageService.allMessages() != null)
            model.addAttribute("messages", messageService.allMessages());
        return "addNews";
    }

    @GetMapping("/delAll")
    public String deleteNews (Model model) {
        Iterable<Message> messages = messageService.allMessages();
        for (Message message:
             messages) {
            messageService.delMessage(message);
        }
        return "addNews";
    }

    @PostMapping("/addNews")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam String text,
                             Model model,
                             @RequestParam String tag,
                             @RequestParam("file") MultipartFile file) throws IOException {
        Message message = new Message(text, tag, user);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(String.format("%s%s%s", System.getProperty("user.dir"), File.separatorChar, pathImg));
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String currentPath = uploadDir.getPath();
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(currentPath + "/" + resultFileName));

            message.setFilename(resultFileName);
        }

        messageService.save(message);

        Iterable<Message> messages = messageService.allMessages();

        model.addAttribute("messages", messages);

        return "addNews";
    }
}
