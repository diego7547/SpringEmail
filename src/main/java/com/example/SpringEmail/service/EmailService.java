package com.example.SpringEmail.service;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface EmailService {
    String sendEmail(MultipartFile[] file, String to, String[] cc, String subject, String body);
}
