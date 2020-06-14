package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ThymeleafController {
    @RequestMapping(value = "/leaf")
    public  String  test(HttpServletRequest request , HttpServletResponse response){
         response.setCharacterEncoding("UTF-8");
         response.setContentType("application/json;charset=utf-8");
        //return "thymeleaf";
        return "你好";
    }
}
