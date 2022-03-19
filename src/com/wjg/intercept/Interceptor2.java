package com.wjg.intercept;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//拦截器 实现实现HandlerInterceptor接口
//没有注解可以使用，只能手动往Spring中注入Bean
public class Interceptor2 implements HandlerInterceptor {

    /**
     * 作用： 用于对拦截到的请求进行预处理，方法接收布尔(true,false)类型的返回值，返回
     true：放行，false：不放行。
     * 执行时机：在处理器方法执行前执行
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 拦截到的处理器方法
     * @return 布尔值 true：放行，false：不放行
     * @throws Exception 抛出的异常信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor2 preHandle \t"+handler);
        return true;  //放行
        //return false;  // 不放行,后面不在执行
    }


    /**
     * 作用： 用于对拦截到的请求进行后处理，可以在方法中对模型数据和视图进行修改
     * 执行时机： 在处理器的方法执行后，视图渲染之前
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 拦截到的处理器方法
     * @param modelAndView 处理器方法返回的模型和视图对象，可以在方法中修改模型和视图
     * @throws Exception 抛出的异常信息
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //在视图渲染前，更改modelAndView
        //modelAndView.setViewName("/login"); //更改跳转页面
        //modelAndView.addObject("msg","更改数据"); //更改数据,相同key名会覆盖

        System.out.println("MyInterceptor2 postHandle \t"+handler+"  视图："+modelAndView.getViewName()+"  Model数据："+modelAndView.getModel());
    }

    /**
     * 作用： 用于在整个流程完成之后进行最后的处理，如果请求流程中有异常，可以在方法中获取
     对象
     * 执行时机： 视图渲染完成后(整个流程结束之后)
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 拦截到的处理器方法
     * @param ex 异常信息
     * @throws Exception 抛出的异常信息
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor2 afterCompletion \t"+handler);
    }
}
