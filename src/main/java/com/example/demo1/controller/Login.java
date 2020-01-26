package com.example.demo1.controller;

import com.example.demo1.config.NeedLogin;
import com.example.demo1.dao.GiteeUserMapper;
import com.example.demo1.dataobject.GiteeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class Login {
    @Autowired
    GiteeUserMapper giteeUserMapper;

    @RequestMapping(path = "/login")
    public ModelAndView login(GiteeUser giteeUser, @RequestParam(name = "autologin" ,defaultValue = "-1") String auto, HttpSession session, HttpServletResponse response){
        GiteeUser giteeUser1 = giteeUserMapper.selectByName(giteeUser.getUsername());
        if (giteeUser1!=null&&giteeUser!=null&&giteeUser1.getPassword().equals(giteeUser.getPassword())){
            session.setAttribute("user",giteeUser);

//            Cookie cookie = new Cookie("name",giteeUser.getUsername());
//            cookie.setPath("/");
//            cookie.setMaxAge(60*60);
//            response.addCookie(cookie);
            return new ModelAndView("redirect:LoginSuccess.html");
        }else {
            return new ModelAndView("redirect:Login.html");
        }
    }
    @RequestMapping(path = "/exit")
    @ResponseBody
    public String exit(HttpSession session){
        session.invalidate();

        return "退出成功！";
    }
    @RequestMapping(path = "get")
    @NeedLogin
    public String get(GiteeUser giteeUser){
        giteeUser.setUsername("小豪");
        return giteeUser.getUsername();
    }
}
