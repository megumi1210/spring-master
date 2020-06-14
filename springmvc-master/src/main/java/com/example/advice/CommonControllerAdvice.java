package com.example.advice;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.thymeleaf.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

//标识控制器通知，并且指定要拦截的控制器包或者classes
@ControllerAdvice(basePackages={"com.example.advice"})
public class CommonControllerAdvice {

    //定义 Http 对应的参数处理规则
    @InitBinder
    public  void initBinder(WebDataBinder binder){
        //针对日期类型的格式化，其中 CustomDateEditor 是客户端自定义的编辑器
        //它的 boolean 参数标识是否允许为空
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),false));
       // DateUtils.format()
    }

    // 全局的键值对,也可以在普通controller 中使用，可以用于保存登录的信息，全局有效
    @ModelAttribute
    public  void  populateModel(Model model){
           model.addAttribute("user","user");
    }

    @ExceptionHandler(Exception.class)
    public  String exception(){
        return  "exception";
    }

}
