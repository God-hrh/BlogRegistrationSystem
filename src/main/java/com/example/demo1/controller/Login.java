package com.example.demo1.controller;

import com.example.demo1.config.NeedLogin;
import com.example.demo1.dao.GiteeUserMapper;
import com.example.demo1.dataobject.GiteeUser;
<<<<<<< Updated upstream
=======
import org.apache.commons.lang3.StringUtils;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
<<<<<<< Updated upstream
=======
import java.util.List;
>>>>>>> Stashed changes


@Controller
public class Login {
    @Autowired
    GiteeUserMapper giteeUserMapper;

    @RequestMapping(path = "/login")
<<<<<<< Updated upstream
    public ModelAndView login(GiteeUser giteeUser, @RequestParam(name = "autologin" ,defaultValue = "-1") String auto, HttpSession session, HttpServletResponse response){
        GiteeUser giteeUser1 = giteeUserMapper.selectByName(giteeUser.getUsername());
        if (giteeUser1!=null&&giteeUser!=null&&giteeUser1.getPassword().equals(giteeUser.getPassword())){
            session.setAttribute("user",giteeUser);

=======
    public Object login(GiteeUser giteeUser,
//                              @RequestParam("emailcaptcha")String emailcaptcha,
                              @RequestParam(name = "autologin",defaultValue = "0") String auto,
                              HttpSession session, HttpServletResponse response
//                              @RequestParam("captcha") String captcha
    ) {
        GiteeUser giteeUser1 = giteeUserMapper.selectByName(giteeUser.getUsername());
//
//        if (captcha.equals(session.getAttribute(Constants.KAPTCHA_SESSION_KEY))&&emailcaptcha.equals(session.getAttribute("emailcode"))){

        if (giteeUser1 != null && giteeUser != null && giteeUser1.getPassword().equals(giteeUser.getPassword()) ) {
            session.removeAttribute("emailcode");
               session.setAttribute("user", giteeUser);
            //添加cookie
>>>>>>> Stashed changes
//            Cookie cookie = new Cookie("name",giteeUser.getUsername());
//            cookie.setPath("/");
//            cookie.setMaxAge(60*60);
//            response.addCookie(cookie);
<<<<<<< Updated upstream
            return new ModelAndView("redirect:LoginSuccess.html");
        }else {
            return new ModelAndView("redirect:Login.html");
        }
=======
            //auto判断是否选择了自动登录
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/count");
            return redirectView;}else {
            return new ModelAndView("redirect:Login.html");
        }
//        }
//        else {
//            return new ModelAndView("redirect:CaptchaError.html");
//        }
>>>>>>> Stashed changes
    }
    @RequestMapping(path = "/exit")
    @ResponseBody
    public String exit(HttpSession session){
        session.invalidate();

        return "退出成功！";
    }
<<<<<<< Updated upstream
    @RequestMapping(path = "get")
    @NeedLogin
    public String get(GiteeUser giteeUser){
        giteeUser.setUsername("小豪");
        return giteeUser.getUsername();
=======
    //验证过滤器
    @RequestMapping(path = "/get")
    @ResponseBody
    @NeedLogin
    public String get() {

        System.out.println("执行了get方法");
        return "123";
    }

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
        public RedirectView signup(GiteeUser giteeUser, RedirectAttributes attributes){
            RedirectView view = new RedirectView();
        if (StringUtils.isBlank(giteeUser.getUsername())||StringUtils.isBlank(giteeUser.getPassword())){
            //将值重新返回给页面，先给userform赋值，然后跳到/opensignup逻辑，
            //参数不会在url体现，相当于一个类的逻辑，addFlashAttribute参数要和@ModelAttribute里的参数一致
            attributes.addFlashAttribute("userform",giteeUser);
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
    public ModelAndView openSignup(@ModelAttribute("userform") GiteeUser user){
        //把user里面的值传到页面中
        ModelAndView view = new ModelAndView("signup");
        return view;
>>>>>>> Stashed changes
    }
}
