package com.example.controller;

import com.example.domain.DemoObj;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class GetParameterController {

    /*
       从路径中获取参数，使用 @PathVariable接收
     */
    @RequestMapping("/pathvar/{str}")
    public  String  demoPathVar(@PathVariable String str , HttpServletRequest request){

        return  "url:" + request.getRequestURL() +" can access ,str:" + str;

    }


    /*
        获取  ****?id=xx  给定路径中参数的值，自动映射
     */
    @RequestMapping("/requestParam")
    public String  demoRequestParam(Long id ,HttpServletRequest request){
         return   "url:" + request.getRequestURL() +" can access ,id:" + id;
    }


    /*
       获取 ****?id=xx&name=xx ,自动映射参数为 对象
     */
    @RequestMapping("/obj")
    public  String demoObjDemo(DemoObj demoObj,HttpServletRequest request){
        return   "url:" + request.getRequestURL() +" can access ,obj id:" + demoObj.getId() +
                                    "obj name" + demoObj.getName();
    }



}
