package com.wjg.controller;

import com.wjg.po.User;
import com.wjg.po.role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//@ModelAttribute注解 有最优先的执行特权
@Controller
@RequestMapping("atModelAttr")
public class ModelAttributeController {

    //无返回值
    //①在参数中获取使用Model,HttpServletRequest,HttpServletResponse等
    //②也可以正常获取请求的数据
    //③可以向Model、HttpServletRequest中添加数据
    @ModelAttribute
    public void fun(User users,String msg, Model model, HttpServletRequest request){
        model.addAttribute("msg2","来自model的请求参数"+msg);
        //model.addAttribute("users",users);  //可以获取到请求的参数
        request.setAttribute("msgr","request、ModelAttribute");
    }

    //有返回值
    //①如果@ModelAttribute没有指定value值，那么添加的key就是类型名称首字母小写
    //②如果指定了value值会在①的基础上额外添加一个，key就是指定的值
    @ModelAttribute("user02")
    public User fun2(User users,String msg, Model model, HttpServletRequest request){
        //未指定value时默认将请求的参数名（user首字母小写）添加到model中,但指定value后便会在model中再添加一个user对象名称为user02
        return users;
        //将请求的参数名（user首字母小写）添加到model中
        //return null;
    }

    @RequestMapping("/one")
    public String one(User user, Model model){
        model.addAttribute("msg","传递数据");
        model.addAttribute("user01",user);
        return "succeed";
    }

    //放在参数中
    //若没有指名value, 默认将以对象类的名称（首字母小写）添加到model中
    //若指定了value则以value的名称添加到model中
    @RequestMapping("/two")
    public ModelAndView two(@ModelAttribute("user03") User user03,@ModelAttribute role re){
        System.out.println(user03);
        System.out.println(re);
        ModelAndView mv=new ModelAndView("succeed");
        return mv;
    }

}
