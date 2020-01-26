package com.example.demo1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class EmailControl {

    @Autowired
    private JavaMailSender javaMailSender;
    @RequestMapping(path = "/sendmail")
    @ResponseBody
    public String send(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2237202251@qq.com");
        message.setTo("2237202251@qq.com");
        message.setSubject("验证码");
        message.setText("<h1>邮箱验证码</h1>");
        javaMailSender.send(message);
        return "发送邮件";
    }

}
