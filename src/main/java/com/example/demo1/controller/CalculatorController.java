package com.example.demo1.controller;





import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;


@Controller

public class CalculatorController implements HttpSessionListener {
    public static List<String> ids = new ArrayList<String>();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String id = se.getSession().getId();
        ids.add(id);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String id = se.getSession().getId();
        ids.remove(id);
    }
    @RequestMapping(path = "/count")
    public ModelAndView count(){
        ModelAndView view = new ModelAndView("LoginSuccess.html");
        view.addObject("count",ids.size());
        return view;
    }
}
