package com.wjg.controller;

import com.wjg.po.role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

//@SessionAttributes与@SessionAttribute注解
@Controller
@RequestMapping("SessionAttribute")
//属性代表有向loginuser与login名称注入的model或者向model注入类型为role的,都会被自动添加到session中
@SessionAttributes(value = {"loginuser","login"},types = {role.class})
public class loginController {

    @RequestMapping("sessionLogin")
    @ResponseBody
    public String sessionLogin(role user, Model model){
        if (user.getUserName()!=null && user.getPassword()!=null && user.getUserName()!="" && user.getPassword()!=""){
            //model可以直接通过定义的loginuser与login属性，直接把值赋予给Session中
            role re=new role();
            re.setUserName("小白");
            re.setPassword("123456");
            model.addAttribute("loginuser",user);
            model.addAttribute("login",user);
            model.addAttribute("re",re);
            return "ok";
        }
        return null;
    }

    @RequestMapping("succeed")
    public String succeed(){
        return "succeed";
    }


    @RequestMapping(value = "getSession",produces = "application/json;charset=UTF-8")
    @ResponseBody
    /**
     * -@SessionAttribute从session中获取指定名称的的数据，并把数据给形参参数
     * required属性默认为true，如果获取的数据不存在——400错误
     */
    public List<role> getSession(@SessionAttribute("loginuser")role user0,@SessionAttribute("login")role user1,
                                 @SessionAttribute("re") role user2,
                                 HttpSession session){
        //一、可以直接@SessionAttribute注解中根据属性值获取在session中相应的值
        System.out.println("loginuser:"+user0);
        System.out.println("login:"+user1);
        System.out.println("re:"+user2);

        List<role> roles=new ArrayList<>();
        //二、传统办法，根据session获取值
        role r1=(role) session.getAttribute("loginuser");
        role r2=(role) session.getAttribute("login");
        roles.add(r1);
        roles.add(r2);
        return roles;
    }
}
