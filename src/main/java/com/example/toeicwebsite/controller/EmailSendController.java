package com.example.toeicwebsite.controller;

import com.example.toeicwebsite.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;


@RestController
@RequestMapping("/mail")
public class EmailSendController {

    private EmailService emailService;

    public EmailSendController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendMail(@RequestParam String to, String subject, String text) throws MessagingException {
        return emailService.sendMail(to, subject, text);
    }

}
