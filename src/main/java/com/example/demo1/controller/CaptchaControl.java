package com.example.demo1.controller;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;


@Controller
public class CaptchaControl {
    @Autowired
    public DefaultKaptcha kaptcha ;


    @RequestMapping(path = "/getCaptcha")
    public void getCapacha(HttpSession session, HttpServletResponse response) throws IOException {
        // Set to expire far in the past.
        response.setDateHeader(" Expires", 0);
// Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate" );
// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
// Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        //设置图片以png格式输出
// return a jpeg
        response.setContentType("image/png");
// create the text for the image
        String capText =kaptcha.createText();
        //把内容写进session中
// store the text in the session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY,capText);
        //输出图片
// create the image with the text
        BufferedImage bi = kaptcha.createImage(capText);
// write the data out
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "png", out);
        try{
            out.flush();
        } finally{
            out.close();
        }
    }
    @Bean
    public DefaultKaptcha initKaptcha() {
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        return kaptcha;
    }

}
