package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.BeanResolver;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;



@Configuration
public class MultiViewConfig   {




    /**
     *  jsp视图解析器
     */
    @Bean
    public ViewResolver jspResolver(){
        InternalResourceViewResolver viewResolver = new
                InternalResourceViewResolver();
        //设置前缀
        viewResolver.setPrefix("/WEB-INF/jsp/");
        //设置后缀
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setOrder(2);
        return viewResolver;
    }

}
