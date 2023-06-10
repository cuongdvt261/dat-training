package com.example.restfulapi.api;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.restfulapi.models.UserInput;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import com.example.restfulapi.models.UserInfo;

@Controller
public class APIUser {
    @Autowired
    public JavaMailSender sendMail;
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserInfo> createUser(@RequestBody UserInput userInput) {
        try {
            String name = UserHelper.normalizeName(userInput.getName());
            int age = UserHelper.calculateAge(userInput.getDateOfBirth());
            String email = UserHelper.validateEmail(userInput.getEmail());
            String phoneRegion = UserHelper.phoneRegion(userInput.getPhoneNumber());
            String address = userInput.getAddress();
            UserInfo userInfo = new UserInfo(name, age, email, phoneRegion, address);
            UserHelper.createTxtFile(name, age, email, phoneRegion, address);
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @RequestMapping("/sendAttachmentEmail")
    public void sendAttachmentEmail() {
        try {
            MimeMessage message = sendMail.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("maithanhdat102@gmail.com");
            helper.setSubject("Test email with attachments");
            FileSystemResource file = new FileSystemResource(new File("/Users/maidat/Desktop/restful-api/account_info.txt"));
            helper.addAttachment("account_info.txt", file);
            sendMail.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
