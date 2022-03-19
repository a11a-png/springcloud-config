package com.wjg.controller;

import com.wjg.po.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

//重定向
@Controller
@RequestMapping("/atRedirect")
public class redirectController {

    @RequestMapping("/result")
    public ModelAndView result(User user,String msgM,String msgR){
        ModelAndView mv=new ModelAndView("/succeed");
        mv.addObject("user",user);
        mv.addObject("msgM",msgM);
        mv.addObject("msgR",msgR);
        return mv;
    }

    //String形式
    @RequestMapping("/redirectfun")
    public String redirectfun(User user, HttpServletRequest request,Model model){
        //重定向中添加到Model中的数据会以参数的形式拼接在url后，可以用来传递数据
        model.addAttribute("msgM","来自redirectfun的model");
        //重定向时，request请求会丢失
        request.setAttribute("msgR","来自redirectfun的request");
        return "redirect:/atRedirect/result";
    }

    //ModelAndView形式
    @RequestMapping("/redirectfun2")
    public ModelAndView redirectfun2(User user, HttpServletRequest request){
        ModelAndView mv=new ModelAndView("redirect:/atRedirect/result");
        //重定向中添加到Model中的数据会以参数的形式拼接在url后，可以用来传递数据
        mv.addObject("msgM","来自redirectfun2的model");
        //重定向时，request请求会丢失
        request.setAttribute("msgR","来自redirectfun2的request");
        return mv;
    }

    //重定向传String形式参数
    @RequestMapping("/redirectfun3")
    public String redirectfun3(User user, Model model,RedirectAttributes redirectAttributes){
        //在String形式下 如果定义了redirectAttributesmodel 那么model设置的参数不会被拼接到重定向的路径中
        //也就是定义了redirectAttributesmodel后model不能通过重定向传参
        model.addAttribute("name",user.getName());
        //一、通过redirectAttributes传参
        redirectAttributes.addAttribute("age",user.getAge());
        String password="";
        try {
            //二、使用路径拼接传参
            password= URLEncoder.encode("小明123","UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //重定向时，request请求会丢失
        return "redirect:/atRedirect/result?password="+password;
    }

    //重定向传ModelAndView形式参数
    @RequestMapping("/redirectfun4")
    public ModelAndView redirectfun4(User user, RedirectAttributes redirectAttributes){
        ModelAndView mv=new ModelAndView();
        //在ModelAndView形式下 如果定义了redirectAttributesmodel 那么model设置的参数还是会被拼接到重定向的路径中
        mv.addObject("msgM","来自redirectfun4的ModelAndView");
        redirectAttributes.addAttribute("age",user.getAge());
        int tall=20;
        mv.setViewName("redirect:/atRedirect/result?tall="+tall);
        return mv;
    }


    /**
     * 使用RedirectAttributes实现重定向不在URL中显示参数
     * (但是该方法只能实现一次，再刷新页面后数据就会消失,原因在于参数是从session中获取的,因为session被移除,所以ModelAttribute获取不了数据)
     * 需要在重定向的目标方法使用@ModelAttribute配置接收参数
     2、web/WEB-INF/jsp/requestRedirect/index.jsp:
     * 其原理就是放到session中，session在跳到页面后马上移除对象。所以你刷新一下后这个值就会丢掉
     */
    @RequestMapping("/redirectAttributesNoUrlParam")
    public String redirectAttributesNoUrlParam(User user,RedirectAttributes
            redirectAttributes){
        redirectAttributes.addFlashAttribute("name",user.getName()+"_raFlash");
        redirectAttributes.addFlashAttribute("age",user.getAge());
        return "redirect:/atRedirect/result2";
    }

    /**
     * 结果显示
     * 使用ModelAttribute注解接收参数
     */
    @RequestMapping("result2")
    public ModelAndView result2(
            @ModelAttribute("name") String name,
            @ModelAttribute("age") Integer age){
        User user=new User();
        user.setName(name);
        user.setAge(age);
        ModelAndView mv=new ModelAndView("succeed");
        mv.addObject("user",user);
        mv.addObject("msgM","参数不拼接到路径中");
        return mv;
    }



}
