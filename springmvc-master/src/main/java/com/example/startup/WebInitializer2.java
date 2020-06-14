package com.example.startup;

import com.example.config.SpringMvcConfig;
import com.example.config.WebSocketConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 *  Servlet 3.0 后允许动态加载 Servlet 需要实现 ServletContainerInitializer
 *  这个类的实现类在 META-INF 下 services 自动被加载
 *  它的注解 @HandlesTypes 代表感兴趣的参数 会在 tomcat 容器中 扫描类路径中的 参数传值调用
 *  最后完成  dispatchServlet 的初始化 ,dispatchServlet 会自动初始化 spring ioc 容器
 * @see org.springframework.web.SpringServletContainerInitializer
 */
public class WebInitializer2 {//extends AbstractAnnotationConfigDispatcherServletInitializer {

//    // Spring Ioc 容器配置
//    //Roaming
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[]{WebSocketConfig.class}; //spring 的配置
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() { //SpringMvc 配置
//        return new Class[]{SpringMvcConfig.class};
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[]{"/"};
//    }
//
//
//    @Override
//    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//        super.customizeRegistration(registration);
           // 基于 StandardServletMultipartResolver 的配置项 Commons 不用
//        //registration.setMultipartConfig(new MultipartConfigElement(filepath,singleMax,totalMax,0));
//    }

}
