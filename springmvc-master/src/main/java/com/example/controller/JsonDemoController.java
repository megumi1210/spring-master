package com.example.controller;

import com.example.domain.DemoObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonDemoController {

    @RequestMapping("/json")
    public DemoObj  json(){ //添加依赖包测试 json 格式数据
        return new DemoObj(1L,"demo");
    }
}
