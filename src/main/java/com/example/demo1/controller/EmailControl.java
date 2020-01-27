package com.example.demo1.controller;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

@Controller
public class EmailControl {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    public DefaultKaptcha kaptcha ;
    @RequestMapping(path = "/sendmail")
    @ResponseBody
    public String send(HttpSession session, @RequestParam("email") String email){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom("2237202251@qq.com");
            helper.setTo(email);
            helper.setSubject("验证码");
            String code = kaptcha.createText();
            session.setAttribute("emailcode",code);
            helper.setText(code);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "发送邮件";
    }

}
