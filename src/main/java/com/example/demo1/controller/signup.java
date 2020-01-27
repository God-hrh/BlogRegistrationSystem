package com.example.demo1.controller;

import com.example.demo1.dao.GiteeUserMapper;
import com.example.demo1.dataobject.GiteeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Heerh
 * @version 1.0
 * @date 2020/1/27 20:44
 */

@Controller

public class signup {
    @Autowired
    GiteeUserMapper giteeUserMapper;
    //返回所有查询到的user
    @RequestMapping(path = "/getusers")
    public ModelAndView getusers(){
        ModelAndView view = new ModelAndView("user");
        List<GiteeUser> list = giteeUserMapper.selectAll();
        view.addObject("users",list);
//        view.setViewName("user");
        return view;
    }

    //使用该方法和上面的返回ModelAndView效果等同，根据个人喜好选择
//    @RequestMapping(path = "getusers")
//    public String getusers(Model model){
//        List<GiteeUser> list = giteeUserMapper.selectAll();
//        model.addAttribute("users",list);
//        return "user";
//    }
    //signup.html网页内容提交到这里
    @RequestMapping(path = "/signup")
    public RedirectView signup(@Valid GiteeUser giteeUser, BindingResult result, RedirectAttributes attributes){
        RedirectView view = new RedirectView();
        if (result.hasErrors()){
            //将值重新返回给页面，先给userform赋值，然后跳到/opensignup逻辑，
            //参数不会在url体现，相当于一个类的逻辑，addFlashAttribute参数要和@ModelAttribute里的参数一致
            attributes.addFlashAttribute("userform",giteeUser);
            attributes.addFlashAttribute("error",result);
            view.setUrl("/opensignup");
        }else {
            giteeUserMapper.insert(giteeUser);
            view.setUrl("/getusers");
        }
        return view;
    }
    //打开templates/signup.html文件
    @RequestMapping(path = "/opensignup")
    //@ModelAttribute("userform") GiteeUser user定义一个实例
    public ModelAndView openSignup(@ModelAttribute("userform") GiteeUser user, Model model){
        //把user里面的值传到页面中
        ModelAndView view = new ModelAndView();
        //把错误信息传递到前端
        //BindingResult.class.getName()+".userform"  为key  ".userform"里的userform和该方法参数注解里的userform一致
        //model.asMap().get("error")  为value
        view.addObject(BindingResult.class.getName()+".userform",model.asMap().get("error"));
        view.setViewName("signup");
        return view;
    }
}
