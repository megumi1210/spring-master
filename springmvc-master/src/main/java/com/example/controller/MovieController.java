package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class MovieController {

    @RequestMapping(value = "/play" )
    public  void play(HttpServletRequest request , HttpServletResponse response) throws IOException {
        FileInputStream fis = new FileInputStream("d:/tmp/zhejiang.mp4");
        response.setHeader("Content-Type","video/mp4;charset=UTF-8");
        ServletOutputStream  os = response.getOutputStream();
        byte[] bytes = new byte[2048];
        int len = 0;
        while ( (len=fis.read(bytes))!=-1){
            os.write(bytes,0,len);
            os.flush();
        }
        os.close();
    }
}
