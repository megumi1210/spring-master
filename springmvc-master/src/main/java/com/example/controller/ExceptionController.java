package com.example.controller;


import com.example.domain.DemoObj;
import com.example.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @RequestMapping
    public DemoObj notFound(DemoObj obj){
        if(obj == null){
            throw new CustomException();
        }
        return  obj;
    }

    @ExceptionHandler
    public  String  handleException(CustomException e){
        //可避免页面不友好
        return "e";
    }

}
