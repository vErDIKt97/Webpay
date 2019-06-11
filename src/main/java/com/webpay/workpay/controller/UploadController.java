package com.webpay.workpay.controller;

import com.webpay.workpay.domain.User;
import com.webpay.workpay.repository.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequestMapping("/upload")
@PreAuthorize("hasAuthority('ADMIN')")
public class UploadController {
    private final UserRepo userRepo;

    public UploadController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    private String upload(Model model) {
            model.addAttribute("messge",userRepo.findByUsername("admin"));
        return "upload";
    }

    @PostMapping
    private String uploadFile(@RequestParam("file") MultipartFile file, Model model){
        String name = null;

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                name = file.getOriginalFilename();

                String rootPath = "WebPay"; //try also "C:\path\"
                File dir = new File("" + File.separator + rootPath + File.separator + "loadFiles");

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                User.setFile(uploadedFile);
                for (User user :
                        userRepo.findAll()) {
                    user.findSells();
                }
                model.addAttribute("message","You successfully uploaded file");
                model.addAttribute("list", User.getList());
                return  "upload";

            } catch (Exception e) {
                model.addAttribute("message","You failed to upload");
                return "upload";
            }
        } else {
            model.addAttribute("message","You failed to upload because the file was empty.");
            return "upload";
        }
    }

}
