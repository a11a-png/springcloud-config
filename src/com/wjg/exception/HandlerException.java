package com.wjg.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjg.vo.JsonMsg;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

//实现HandlerExceptionResolver  注意使用这种方式的话，需要使这个类成为bean
//是全局异常，但是HTTP状态异常捕获不到
@Component
public class HandlerException implements HandlerExceptionResolver {
      //jackson 序列化对象使用
      private final ObjectMapper objectMapper=new ObjectMapper();

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        ModelAndView mv=new ModelAndView();
        //1、设置异常内容
        //普遍异常
        String title="";
        String msg="";
        StringBuffer stringBuffer=new StringBuffer();
        //特殊异常
        if (e instanceof MaxUploadSizeExceededException){
            //超出上传文件大小异常
            MaxUploadSizeExceededException ex=(MaxUploadSizeExceededException) e;
            long maxsize=ex.getMaxUploadSize()/1024/1024;
            title="上传文件超过指定的"+maxsize+"MB";
            msg=e.getMessage();
        }else if (e instanceof java.lang.ArithmeticException){
            //算术异常
            title="算术异常";
            msg="不能除以零";
        }
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
