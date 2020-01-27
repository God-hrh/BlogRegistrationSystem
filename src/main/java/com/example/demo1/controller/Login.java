package com.example.demo1.controller;

import com.example.demo1.config.NeedLogin;
import com.example.demo1.dao.GiteeUserMapper;
import com.example.demo1.dataobject.GiteeUser;
//import org.apache.commons.lang3.StringUtils;
import com.google.code.kaptcha.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
public class Login {
    @Autowired
    GiteeUserMapper giteeUserMapper;

    @RequestMapping(path = "/login")
    public Object login(GiteeUser giteeUser,
                        @RequestParam("emailcaptcha") String emailcaptcha,
                        @RequestParam(name = "autologin", defaultValue = "0") String auto,
                        HttpSession session, HttpServletResponse response,
                        @RequestParam("captcha") String captcha
    ) {

        GiteeUser giteeUser1 = giteeUserMapper.selectByName(giteeUser.getUsername());
//
        if (captcha.equals(session.getAttribute(Constants.KAPTCHA_SESSION_KEY)) && emailcaptcha.equals(session.getAttribute("emailcode"))) {

            if (giteeUser1 != null && giteeUser != null && giteeUser1.getPassword().equals(giteeUser.getPassword())) {
                session.removeAttribute("emailcode");
                session.setAttribute("user", giteeUser);
                //添加cookie
//            Cookie cookie = new Cookie("name",giteeUser.getUsername());
//            cookie.setPath("/");
//            cookie.setMaxAge(60*60);
//            response.addCookie(cookie);
                //auto判断是否选择了自动登录
                RedirectView redirectView = new RedirectView();
                redirectView.setUrl("/count");
                return redirectView;
            } else {
                return new ModelAndView("redirect:Login.html");
            }
        } else {
            return new ModelAndView("redirect:CaptchaError.html");
        }
    }

    @RequestMapping(path = "/exit")
    @ResponseBody
    public String exit(HttpSession session) {
        session.invalidate();

        return "退出成功！";
    }

    //验证过滤器
    @RequestMapping(path = "/get")
    @ResponseBody
    @NeedLogin
    public String get() {

        System.out.println("执行了get方法");
        return "123";
    }


}
