package com.example.demo1.controller;





import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;


@RestController

public class CalculatorController implements HttpSessionListener {
    public  List<String> ids = new ArrayList<String>();

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
    public String  count(){
        return "当前用户登陆总数是"+ids.size();
    }

}
