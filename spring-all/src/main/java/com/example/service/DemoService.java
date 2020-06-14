package com.example.service;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    @Scheduled(fixedRate = 2000)
    public void report(){
        System.out.println("test");
    }
}
