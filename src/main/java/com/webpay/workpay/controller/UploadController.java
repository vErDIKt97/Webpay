package com.webpay.workpay.controller;

import com.webpay.workpay.domain.PayFile;
import com.webpay.workpay.domain.User;
import com.webpay.workpay.repository.PayFileRepo;
import com.webpay.workpay.repository.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/upload")
@PreAuthorize("hasAuthority('ADMIN')")
public class UploadController {

    @Value("${path.upload}")
    private String payFileDB;

    @Value("${file.sells}")
    private String fileSells;

    @GetMapping
    public String getUpload () {
        return "upload";
    }

    private final PayFileRepo payFileRepo;
    private final UserRepo userRepo;
    public UploadController(UserRepo userRepo, PayFileRepo payFileRepo) {
        this.userRepo = userRepo;
        this.payFileRepo = payFileRepo;
    }

    @GetMapping("/allSells")
    public String allSells (Model model) {
        model.addAttribute("list", payFileRepo.findByFileName(payFileDB).getList());
        return "allSells";
    }

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file,
                              Model model){
        String name = null;
        PayFile payFile = new PayFile();
        if (!file.isEmpty()) {
            try {
                String rootPath = payFileDB; //try also "C:\path\"
                File dir = new File(rootPath + File.separator + "loadFiles");

                if (!dir.exists()) {
                    dir.mkdirs();
                }
                name = file.getOriginalFilename();
                File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);
                file.transferTo(uploadedFile);
                payFile.setList(uploadedFile);
                payFile.setFileName(fileSells);
                payFileRepo.save(payFile);
                model.addAttribute("message","You successfully uploaded file");
                model.addAttribute("list",payFile.getList() );
                for (User user :
                        userRepo.findAll()) {
                    payFileRepo.findByFileName(fileSells).findSells(user);
                    userRepo.save(user);
                }
                return  "allSells";

            } catch (Exception e) {
                model.addAttribute("message",e.toString());
                return "upload";
            }
        } else {
            model.addAttribute("message","You failed to upload because the file was empty.");
            return "upload";
        }
    }
}
