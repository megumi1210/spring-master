package com.example.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@RestController
public class FormatController {

    @RequestMapping("/format") //日期和数字格式化测试,此注解也可以在POJO中使用
    public String format(@RequestParam("date1") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date date ,
          @RequestParam("amount1") @NumberFormat(pattern = "#,###.##") Double amount){

        return "date:" + date +"\t" + "amount:"+ amount;

    }
}
