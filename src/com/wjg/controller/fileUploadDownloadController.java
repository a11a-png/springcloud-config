package com.wjg.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//文件上传下载
@Controller
@RequestMapping("/fileUpload")
public class fileUploadDownloadController {
     //文件上传目录
     private static final String UPLOAD_Path="F:\\SptingFileUpload";

     //局部异常 （局限性比较大。对于Controller之外出现的异常无法处理）
     //Controller层里面使用@ExceptionHandler注解
    /**
     * 配置当前Controller的异常处理
     * -@ExceptionHandler[(可以指定处理的指定异常类.class)]
     * @param e 异常信息
     * @return ModelAndView对象
     */

    //@ExceptionHandler(Exception.class)
    //也可以返回json格式
    public ModelAndView handleException(Exception e){
       ModelAndView mv=new ModelAndView("/ErrorML/errorOne");
       /*
       * 对于文件上传的异常，默认这里是无法处理的 出现异常的地方不在本Controller内
       * 可以在multipartResolver 中配置<property name="resolveLazily" value="true"/>后捕获
       */
       //instanceof判断一个对象是否为一个类（或接口、抽象类、父类）的实例
       //MaxUploadSizeExceededException 最大上传大小超过异常
//       MaxUploadSizeExceededException ex=(MaxUploadSizeExceededException) e; //获取异常对象
//       long maxsize=ex.getMaxUploadSize()/1024/1024; //获取最大文件大小 MB为单位
       mv.addObject("title",e.getClass().getName()); //异常头
       mv.addObject("msg",e.getMessage()); //异常消息
       StringBuffer stringBuffer=new StringBuffer();
       for (StackTraceElement st:e.getStackTrace()) {
            stringBuffer.append(st.toString());
            stringBuffer.append(" <br> ");
       }
       mv.addObject("StackTraceElement",stringBuffer);
       return mv;
    }

     //单文件上传
     @RequestMapping("Upload")
     public ModelAndView Upload(MultipartFile file,String name) throws IOException {
          ModelAndView mv=new ModelAndView("succeed");
          if ("0".equalsIgnoreCase(name)){
              int a=0;
              int b=1/a;
          }else if ("throw".equalsIgnoreCase(name)){
              //抛出运行异常信息  "throw new RuntimeException"
              throw new RuntimeException("throw new RuntimeException");
          }
           //1、判断文件目录是否存在
          File file1=new File(UPLOAD_Path);
          if (!file1.exists()){
               //若不存在则创建
               file1.mkdirs();
          }
          //2、判断请求文件是否为空
          if (!file.isEmpty()){
              //获取文件属性
              System.out.println("文件大小："+file.getSize());
              System.out.println("文件类型："+file.getContentType());
              System.out.println("文件名称："+file.getName());
              System.out.println("文件原名："+file.getOriginalFilename());
              //获取文件扩展名
              String filename=file.getOriginalFilename();
              //substring从指定位置开始截取字符串,默认到末尾。
              //lastIndexOf返回指定字符在此字符串中最后一次出现处的索引。
              String ex=filename.substring(filename.lastIndexOf(".")); //文件扩展名
              //拼接文件名
              String newfilename=System.currentTimeMillis()+ex; //当前时间(毫秒)+扩展名
              File saveFile=new File(UPLOAD_Path,newfilename); //创建保存文件的File类
               //保存文件
              file.transferTo(saveFile);
              //FileUtils.writeByteArrayToFile(saveFile, file.getBytes());//方法二
               mv.addObject("msgM","上传成功");
               mv.addObject("singleFileName",newfilename);
          }else{
               mv.addObject("msgM","请求文件不能为空");
          }
          return mv;
     }

     //多文件上传
     @RequestMapping("Uploads")
     public ModelAndView Uploads(MultipartFile[] files,String name) throws IOException {
          ModelAndView mv=new ModelAndView("succeed");
          //1、判断文件目录
          File file=new File(UPLOAD_Path);
          if (!file.exists()){
              file.mkdirs();
          }
          //判断文件请求
          if (files!=null && files.length>0){
              List<String> filenamelist=new ArrayList<>();
              //获取文件扩展名
              for (int i=0;i<files.length;i++){
                  String filename=files[i].getOriginalFilename();
                  String ex=filename.substring(filename.lastIndexOf("."));
                  String newfilename=System.currentTimeMillis()+ex;
                  filenamelist.add(newfilename);
                  File savefile=new File(UPLOAD_Path,newfilename);
                  files[i].transferTo(savefile);
              }
              if (filenamelist.size()==files.length){
                  mv.addObject("msgMs","上传成功");
                  mv.addObject("singleFileNames",filenamelist);
              }else{
                  mv.addObject("msgMs","上传失败");
              }
          }else{
              mv.addObject("msgMs","上传文将不能为空");
          }
          return mv;
     }


     //文件下载
     //@RequestMapping("/download/{filename}")
     //使用restful 风格文件下载比如 http://localhost:8080/springmvc/fileUpload/download/1597388581345.jpg
     //文件后缀会被忽略掉,导致下载失败
     //修改
     @RequestMapping("download/{filename:.+}")
     public ResponseEntity<byte[]> download(@PathVariable("filename")String filename) throws IOException {
          //1、在目录中获取文件
          File file=new File(UPLOAD_Path,filename);
          HttpHeaders headers=new HttpHeaders();
          //application/octet-stream二进制流数据（最常见的文件下载）
          headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
          //通知浏览器以attachment（下载方式） 下载文件，文件名称为指定名称
          headers.setContentDispositionFormData("attachment",filename);
          //使用commons-io包读取文件的二进制数据
          byte[] bytes= FileUtils.readFileToByteArray(file);
          //返回文件
          return new ResponseEntity<byte[]>(bytes,headers, HttpStatus.CREATED);
     }


}
