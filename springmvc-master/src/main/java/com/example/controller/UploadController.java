package com.example.controller;

import  java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//处理文件上传
@Controller
public class UploadController {

    @RequestMapping(value = "/upload" ,method = RequestMethod.POST)
    public  @ResponseBody  String upload(MultipartFile file){
            try{
                File f = new File("d:/tmp/" + file.getOriginalFilename());
                FileUtils.writeByteArrayToFile(f,file.getBytes());
                return "ok";
            } catch (IOException e) {
                e.printStackTrace();
                return  "wrong";
            }
    }

}
