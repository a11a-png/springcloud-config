package com.wjg.controller;

import com.wjg.po.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//向jsp传递数据
@Controller //相当于@Component, 往Spring容器中注入一个Bean
//@RestController 与传统的@Controller相比,多了一项功能就是以下所有除了返回值为ModelAndView的其余的都不会去访问视图解析器，直接返回类型
@RequestMapping("/twoController")
public class twoController {

    @RequestMapping("login")
    public String login(Model model){
        model.addAttribute("msg","twoController");
        return "login";
    }

    //Controller向jsp传递数据
    //1、使用request传递
    @RequestMapping("requestData")
    public String requestCs(HttpServletRequest request){
         List<User> users=new ArrayList<>();
         User user=null;
         for (int i=0;i<10;i++){
             user=new User();
             user.setName("request数据"+i);
             user.setTall(0);
             user.setAge(i);
             user.setBirthday(new Date());
             users.add(user);
         }
         request.setAttribute("users",users);
         return "sendData";
    }

    //1、使用Model传递
    @RequestMapping("ModelData")
    public String ModelData(Model model){
        List<User> users=new ArrayList<>();
        User user=null;
        for (int i=0;i<10;i++){
            user=new User();
            user.setName("model数据"+i);
            user.setTall(0);
            user.setAge(i);
            user.setBirthday(new Date());
            users.add(user);
        }
        model.addAttribute("users",users);
        return "sendData";
    }

    //1、使用request传递
    @RequestMapping("ModelAndViewData")
    public ModelAndView ModelAndViewData(){
        ModelAndView mv=new ModelAndView("sendData");
        List<User> users=new ArrayList<>();
        User user=null;
        for (int i=0;i<10;i++){
            user=new User();
            user.setName("ModelAndViewData数据"+i);
            user.setAge(i);
            user.setBirthday(new Date());
            users.add(user);
        }
        mv.addObject("users",users);
        return mv;
    }

}
