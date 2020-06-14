package com.example.controller;

import com.example.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * @see org.springframework.web.servlet.View
 */

@Controller
public class ModelController {

    @RequestMapping("/modelMap")
    public ModelAndView getByModelMap(ModelMap modelMap){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("model");
        DemoObj obj = new DemoObj();
        obj.setId(1L);
        obj.setName("megumi");
        modelMap.addAttribute("obj",obj);
        return  mv;
    }


    @RequestMapping("/model")
    public ModelAndView getByModel(Model model){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("model");
        DemoObj obj = new DemoObj();
        obj.setId(2L);
        obj.setName("megumi2");
        model.addAttribute("obj",obj);
        return  mv;
    }
}
