package com.example.SpringEmail.controller;

import com.example.SpringEmail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/send")
    public String send(@RequestParam(value = "file",required = false)MultipartFile[] file,String to,String [] cc,String subject,String body){
        return  emailService.sendEmail(file,to,cc,subject,body);
    }


}
