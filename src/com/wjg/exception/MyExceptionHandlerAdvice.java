package com.wjg.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjg.vo.JsonMsg;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

//不是全局异常，控制器外的异常和HTTP状态异常捕获不到
@ControllerAdvice(basePackages = "com.gx.controller") //扫描当前包以及子包下的Controller(可多个)被@ControllerAdvice 管理。
// @ControllerAdvice("com.gx.controller") //扫描当前包以及子包下的Controller(可多个)被@ControllerAdvice 管理。
// @ControllerAdvice(value="com.gx.controller") //扫描当前包以及子包下的Controller(可多个)被@ControllerAdvice 管理。

// @ControllerAdvice(basePackageClasses="forwardController.class") //扫描当前Controller类中的父包及子包(可多个)被@ControllerAdvice 管理。

// @ControllerAdvice(assignableTypes="forwardController.class") //扫描当前Controller类(可多个)被@ControllerAdvice 管理。
public class MyExceptionHandlerAdvice{

    //Jackson
    private final ObjectMapper objectMapper=new ObjectMapper();

    @ExceptionHandler(MaxUploadSizeExceededException.class) //只要有对应的异常抛出，就会使用 @ExceptionHandler 注解的方法进行处理。
    public ModelAndView fun(HttpServletResponse response, HttpServletRequest request,Exception e){
        ModelAndView mv=new ModelAndView();
        //1、设置异常内容
        String title="";
        String msg="";
        StringBuffer stringBuffer=new StringBuffer();
        //超出上传文件大小异常
        MaxUploadSizeExceededException ex=(MaxUploadSizeExceededException) e;
        long maxsize=ex.getMaxUploadSize()/1024/1024;
        title="上传文件超过指定的"+maxsize+"MB";
        msg=e.getMessage();
        //获取requestType和 contentType
        String requestType=request.getHeader("X-Requested-With");
        String contentType=request.getContentType()==null?"":request.getContentType().toLowerCase(Locale.ROOT);
        //判断是否是Ajax请求
        if (contentType.contains("application/json")|| (requestType!=null && "XMLHttpRequest".equalsIgnoreCase(requestType))){
            //ajax请求 返回json格式的异常信息
            //使用Response对象返回
            //设置响应类型
            response.setContentType("application/json;charset=utf-8");
            //设置jsonMsg
            JsonMsg jsonMsg=new JsonMsg();
            jsonMsg.setCode(-1);
            jsonMsg.setState(false);
            jsonMsg.setMsg(msg);
            //ajax换行
            for (StackTraceElement a:e.getStackTrace()){
                stringBuffer.append(e.toString());
                stringBuffer.append(File.separator);
            }
            jsonMsg.setData(stringBuffer.toString());
            try {
                PrintWriter writer=response.getWriter();
                writer.write(objectMapper.writeValueAsString(jsonMsg));
                writer.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }else{
            mv.addObject("title",title);
            mv.addObject("msg",msg);
            for (StackTraceElement a:e.getStackTrace()) {
                stringBuffer.append(a.toString());
                stringBuffer.append("<br>");
            }
            mv.addObject("StackTraceElement",stringBuffer);
            mv.setViewName("/ErrorML/errorOne");
        }
        return mv;
    }
}
