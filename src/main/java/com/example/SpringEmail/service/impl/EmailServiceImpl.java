package com.example.SpringEmail.service.impl;

import com.example.SpringEmail.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender javaMailSender;



    @Override
    public String sendEmail(MultipartFile[] file, String to, String[] cc, String subject, String body) {
        try{
            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            for(int i = 0 ;i < file.length;i++){
                    mimeMessageHelper.addAttachment(

                            file[i].getOriginalFilename(),
                            new ByteArrayResource(file[i].getBytes())
                    );
            }
            javaMailSender.send(mimeMailMessage);
            return "mail send";
        }catch (Exception e){
            throw  new RuntimeException(e);
        }



    }
}
