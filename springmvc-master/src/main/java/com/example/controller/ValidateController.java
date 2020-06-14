package com.example.controller;

import com.example.domain.Transaction;
import com.example.validator.TransactionValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.validation.Valid;
import java.util.List;

//两种验证不能同时使用
@Controller
@RequestMapping(value = "/valid",method= RequestMethod.POST)
public class ValidateController {

    @RequestMapping(value = "/annotation",produces = "application/json;charset=utf-8")//通过注解和 hibernate-validator 实现表单验证
    public @ResponseBody String  valid(@Valid Transaction trans , Errors errors){
         StringBuilder sb = new StringBuilder();
        //是否存在错误
        if(errors.hasErrors()){
            //获取错误信息
            List<FieldError> errorList = errors.getFieldErrors();
            for(FieldError error : errorList){
            String s = new String("field:" +error.getField() +"\t" +
                        "msg:" + error.getDefaultMessage());
                //打印太多字段
                System.err.println(s);
                sb.append(s).append("\n");
            }
        }
        return  sb.toString();

    }

    @InitBinder
    public void initBinder(DataBinder binder){
        //数据绑定器加入验证器
        binder.setValidator(new TransactionValidator());
    }

    @RequestMapping(value = "/validator",produces = "application/json;charset=utf-8") // 通过自定义验证器实现表单验证
    public @ResponseBody String validator(@Valid Transaction transaction ,Errors errors){//这个会抛出异常被全局拦截
        StringBuilder sb = new StringBuilder();
         //是否存在错误
        if(errors.hasErrors()){
            //获取错误信息
            List<FieldError> errorList = errors.getFieldErrors();
            for(FieldError error : errorList){
                String s =  new String("field:" + error.getField() + "\t"  +
                        "msg:" + error.getDefaultMessage());
                //打印字段错误
                System.err.println(s);
                sb.append(s).append("\n");

            }
        }
        return  sb.toString();
    }

}
