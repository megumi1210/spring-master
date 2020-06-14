package com.example.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  请求拦截器，对一个请求进行前后相关的业务处理，可以通过两种方式实现,类似于 Filter
 *  1. 继承 HandlerInterceptorAdapter
 *  2. 实现 HandlerInterceptor 接口
 */
public class DemoInterceptor extends HandlerInterceptorAdapter  {//implements HandlerInterceptor


    //前置处理，请求完成前处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         long startTime= System.currentTimeMillis();
         request.setAttribute("startTime",startTime);
         return  true;
    }

    //后置处理,请求完成后处理
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       long startTime = (Long) request.getAttribute("startTime");
       request.removeAttribute("startTime");
       long endTime = System.currentTimeMillis();
       System.out.println("本次请求处理的时间为:" + (endTime-startTime) +"ms");
       request.setAttribute("handlingTime",endTime - startTime);

    }
}
