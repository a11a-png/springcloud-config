package com.wjg.controller;

import com.wjg.po.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;

//转发
@Controller
@RequestMapping("atForward")
public class forwardController implements Serializable {

      @RequestMapping("solvefun")
      public ModelAndView solvefun(User user){
           System.out.println("解决转发方法");
           ModelAndView mv=new ModelAndView("succeed");
           mv.addObject("user",user);
           return mv;
      }

      //ModelAndView转发方法
      @RequestMapping("forwardfun")
      public ModelAndView forwardfun(User user){
          System.out.println("转发forwardfun");
          ModelAndView mv=new ModelAndView("forward:solvefun");
          mv.addObject("name","forwardfun");
          return mv;
      }

      //String转发方法
      @RequestMapping("forwardfun2")
      public String forwardfun2(User user, Model model){
          System.out.println("转发forwardfun2");
          model.addAttribute("name","forwardfun2");
          return "forward:solvefun";
      }

}
