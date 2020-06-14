package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @see org.springframework.web.servlet.ModelAndView
 * @see org.springframework.ui.Model
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
