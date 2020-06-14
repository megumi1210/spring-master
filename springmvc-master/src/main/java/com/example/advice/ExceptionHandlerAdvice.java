package com.example.advice;


import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;



/**
 *  将控制器的全局配置都放在同一个位置,注解了 @Controller 的方法 可以使用此类定义的方法
 *  但只对 @RequestMapping 有效 ，类似于aop
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {


    /*
      全局处理异常,使得更人性化的将异常输出给用户
     */
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView exception(Exception exception, WebRequest request){
//        ModelAndView mv  = new ModelAndView("error"); //error 页面
//        mv.addObject("errorMessage",exception.getMessage());
//        return  mv;
//    }

    /**
     * 让全局的 @RequestMapping 获取此处设置的键值对
     */
    @ModelAttribute
    public  void addAttributes(Model model){
        model.addAttribute("msg","额外信息");
    }

    /*
      用来绑定前端请求参数到 Model中
     */
    @InitBinder
    public  void  initBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");//此处为忽略前端的参数 id
    }

}
