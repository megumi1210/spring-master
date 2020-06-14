package com.example.config;

import com.example.interceptor.CharsetEncodingInterceptor;
import com.example.interceptor.DemoInterceptor;
import com.example.msgconverter.CustomMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafView;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;



@Configuration
@EnableWebMvc
@EnableScheduling //开启计划任务
@ComponentScan("com.example.*")
@Import({WebSocketConfig.class, MultiViewConfig.class})
public class SpringMvcConfig implements WebMvcConfigurer {

    @Autowired
    ApplicationContext applicationContext;




    @Bean  //配置生成模板解析器
    public ITemplateResolver templateResolver(){
         SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
         templateResolver.setApplicationContext(applicationContext);
         templateResolver.setPrefix("/WEB-INF/template/");
         templateResolver.setSuffix(".html");
         templateResolver.setTemplateMode(TemplateMode.HTML);
         templateResolver.setCacheable(false);
         templateResolver.setCharacterEncoding("UTF-8");
         return  templateResolver;
    }




    @Bean  //设置模板引擎
    public SpringTemplateEngine templateEngine(){
      SpringTemplateEngine templateEngine = new SpringTemplateEngine();
      templateEngine.setTemplateResolver(templateResolver());
      return  templateEngine;
    }

    @Bean  //thymeleaf 模板引擎
    public ViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setContentType("text/html;charset=utf-8"); //这个不配置中文乱码
       // viewResolver.setViewClass(ThymeleafView.class);
        viewResolver.setOrder(1);
        return viewResolver;
    }






    /*
      静态资源配映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/static/**"). //对外暴露的访问路径
                             addResourceLocations("classpath:/static/"); //文件放置的目录
    }

    /*
       配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(demoInterceptor());
        registry.addInterceptor(encodingInterceptor()).addPathPatterns("/**");
    }

    /*
       简易配置路径映射
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
         registry.addViewController("/index").setViewName("/index");
         registry.addViewController("/toUpload").setViewName("/upload");
         registry.addViewController("/converter").setViewName("/converter");
         registry.addViewController("/sse").setViewName("/sse");
         registry.addViewController("/async").setViewName("/async");
         registry.addViewController("/hello").setViewName("/hello");
         registry.addViewController("/toValid").setViewName("/valid");
         registry.addViewController("/toFormat").setViewName("/format");

    }

    //使得路径参数中的包含 . 不被忽略
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
         configurer.setUseSuffixPatternMatch(false);
    }

    @Bean
    public DemoInterceptor demoInterceptor(){
        return  new DemoInterceptor();
    }

    @Bean
    public CharsetEncodingInterceptor  encodingInterceptor(){return  new CharsetEncodingInterceptor();}

    /*
           文件上传的配置 依赖 commons-fileupload
     */
    @Bean
    public MultipartResolver multipartResolver(){
        // servlet 3.0以上可以使用自带的标准文件解析
        //return new StandardServletMultipartResolver();
        CommonsMultipartResolver multipartResolver = new
                       CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return  multipartResolver;
    }

    //添加自定义的消息转换器不覆盖默认的
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    //自定义消息转化器
    @Bean
    public CustomMessageConverter converter(){
        return new CustomMessageConverter();
    }

    @Bean
    @Nullable
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(8);
        taskScheduler.setThreadNamePrefix("SockJS-");
        taskScheduler.setRemoveOnCancelPolicy(true);
        taskScheduler.initialize();
        return  taskScheduler;
    }

//    //设置默认的编码值为 UTF-8
//    private static  final Charset   default_charset = StandardCharsets.UTF_8;
//
//    @Bean
//    public StringHttpMessageConverter  messageConverter(){
//       StringHttpMessageConverter converter = new StringHttpMessageConverter(default_charset);
//      converter.setSupportedMediaTypes(buildDefaultMediaType());
//      return  converter;
//    }
//
//    private   static List<MediaType> buildDefaultMediaType(){
//           List<MediaType> mediaTypes = new ArrayList<>();
//           mediaTypes.add(MediaType.TEXT_HTML); //这个必须放在第一位
//           mediaTypes.add(MediaType.APPLICATION_JSON) ;
//           return  mediaTypes;
//    }
//
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(messageConverter()) ;
//    }
}
