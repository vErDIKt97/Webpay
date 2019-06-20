package com.webpay.workpay.controller;

import com.webpay.workpay.domain.Message;
import com.webpay.workpay.domain.User;
import com.webpay.workpay.service.MessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        if (messageService.allMessages() != null) {
            model.addAttribute("messages", messageService.allMessages());
            model.addAttribute("tags", messageService.allTags());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            model.addAttribute("time",format.format(getCurTime()));
        }
        return "news";
    }

    @PostMapping
    public String filterNews (@RequestParam (required = false,value = "tag")String tag,
                              @RequestParam ("date") String date,
                              Model model) {
        Iterable<Message> messages = messageService.allMessages();
        ArrayList<Message> temp = new ArrayList<>();
        if (!tag.isEmpty()&&!date.isEmpty()) {
            for (Message message :
                    messages) {
                if (message.getTag().equals(tag) && message.getDate().equals(date))
                    temp.add(message);
            }
            model.addAttribute("messages", temp);
        } else {
            if (!date.isEmpty()) {
                for (Message message :
                        messages) {
                    if (message.getDate().equals(date))
                        temp.add(message);
                }
                model.addAttribute("messages", temp);
            } else {
                if (!tag.isEmpty()) {
                    for (Message message :
                            messages) {
                        if (message.getTag().equals(tag))
                            temp.add(message);
                    }
                    model.addAttribute("messages", temp);
                } else {
                    model.addAttribute("messages",messages);
                }
            }
        }
        model.addAttribute("tags", messageService.allTags());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("time",format.format(getCurTime()));
        return "news";
    }

    @GetMapping("/del{message}")
    public String delMessage (@PathVariable Message message,
                              Model model) {
        if (messageService.allMessages().contains(message)) {
            messageService.delMessage(message);
        }
        model.addAttribute("messages", messageService.allMessages());
        model.addAttribute("tags", messageService.allTags());
        model.addAttribute("message",null);


        return "addNews";
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
        model.addAttribute("messages",messageService.allMessages());
        return "addNews";
    }

    @PostMapping("/addNews")
    public String addMessage(@AuthenticationPrincipal User user,
                             @Valid Message message,
                             BindingResult bindingResult,
                             Model model,
                             @RequestParam("file") MultipartFile file) throws IOException {
        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            if (errorsMap.size()==1&&errorsMap.containsKey("idError")){
                addNewMessage(message, model, file, user);
            } else {
                model.mergeAttributes(errorsMap);
                model.addAttribute("message", message);
            }
        } else {
            addNewMessage(message, model, file, user);
        }
        return "addNews";
    }

    private void addNewMessage(@Valid Message message, Model model, @RequestParam("file") MultipartFile file, User author) throws IOException {
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        message.setDate(format.format(getCurTime()));
        message.setAuthor(author);
        messageService.save(message);

        Iterable<Message> messages = messageService.allMessages();
        model.addAttribute("message",null);
        model.addAttribute("messages", messages);
    }

    private Date getCurTime () {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
}
