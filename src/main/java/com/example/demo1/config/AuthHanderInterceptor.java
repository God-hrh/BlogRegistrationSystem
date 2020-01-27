package com.example.demo1.config;

import com.example.demo1.dataobject.GiteeUser;
import com.example.demo1.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class AuthHanderInterceptor implements HandlerInterceptor {
    //复写方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果handler属于HandlerMethod
        if (handler instanceof HandlerMethod){
            //强转一下
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //取一下needLogin的注解
            NeedLogin needLogin = handlerMethod.getMethodAnnotation(NeedLogin.class);
            //判断该方法上面有没有NeedLogin注解
            if (needLogin !=null){
               GiteeUser u  = (GiteeUser) request.getSession().getAttribute("user");
               if (u==null){
                   response.sendRedirect("/Login.html");
                   return  false;
               }else {
                   //返回静态页面的LoginSuccess.html,
                   response.sendRedirect("LoginSuccess.html");
               }
            }
        }
        return true;
    }
}
