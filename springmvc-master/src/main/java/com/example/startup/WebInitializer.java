package com.example.startup;

import com.example.config.SpringMvcConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;


import javax.servlet.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;

/**
 *  实现此接口会自动被 spring 容器获取到
 *  @see org.springframework.web.SpringServletContainerInitializer
 *
 *   @see ContextLoaderListener 会自动初始化 Web 容器
 *
 */
public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new
                AnnotationConfigWebApplicationContext();
        //注册其他配置类到spring中
        ctx.register(SpringMvcConfig.class);
        ctx.setServletContext(servletContext);
        //添加字符过滤器
//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding("UTF-8");
//        filter.setForceEncoding(true);
//        FilterRegistration.Dynamic filterRegistration=servletContext.addFilter("characterEncodingFilter",filter);
//        filterRegistration.addMappingForUrlPatterns(null,false,"/*");

         //  注册 spring mvc 的 DispatcherServlet
      ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",
                                            new DispatcherServlet(ctx));

        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        //servlet.setInitParameter("spring.profiles.default","dev");
        servlet.setAsyncSupported(true);  //开启异步支持


    }
}
