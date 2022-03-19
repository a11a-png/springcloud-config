package com.wjg.controller;

import com.wjg.po.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//跳转页面与六种接收参数
@Controller //向Spring容器注册控制器
@RequestMapping("/indexController")
public class indexcontroller {
    //返回字符串
    //处理请求路径地址映射的注解
    @RequestMapping("/login")
    //将返回值直接转换为指定格式后响应到对应的视图层，不会经过视图解析器,除非返回的类型是ModelAndView
    //@ResponseBody
    public String login(Model model){
        model.addAttribute("msg","登录");
        //根据配置的视图解析器  prefix+返回值+suffix
        // 指向 /WEB-INF/jsp/login.jsp
        return "login";
    }

    @RequestMapping("/PathVariable")
    //@ResponseBody //将返回值直接转换为指定格式后响应到对应的视图层，不会经过视图解析器,除非返回的类型是ModelAndView
    public String PathVariable(){
        //根据配置的视图解析器  prefix+返回值+suffix
        // 指向 /WEB-INF/jsp/login.jsp
        return "PathVariabl";
    }

    @RequestMapping("FileUpload")
    public String FileUpload(Model model){
        model.addAttribute("msg","indexController");
        //根据配置的视图解析器  prefix+返回值+suffix
        // 指向 /WEB-INF/jsp/login.jsp
        return "FileUpload";
    }

    //返回ModelAndView
    @RequestMapping("/MAV")
    @ResponseBody
    public ModelAndView MAV(){
        //构造函数
        //ModelAndView mv=new ModelAndView("/indexController/MAV");

       ModelAndView mv=new ModelAndView();
       mv.setViewName("ModelAndView");

       //编写参数
       mv.addObject("msg","返回ModelAndView类型与返回String类型基本一致，也是通过“springMVC-servlet.xml”中的配置的视图解析器，解析并响应 prefix+ViewName+suffix 对应的视图 相对于String的返回类型的优势，可以更方便的携带参数传递到页面");
       mv.addObject("name","返回ModelAndView参数类型");
       return mv;
    }

    //返回ModelAndView
    @RequestMapping("/ajaxfun")
    @ResponseBody
    public ModelAndView ajaxfun(){
        //构造函数
        ModelAndView mv=new ModelAndView("ajaxTj");
        return mv;
    }

    //无返回值会出现中文乱码
    @RequestMapping("/Voidfun")
    //不加@ResponseBody注解 会经过视图解析器,若没有指定的页面路径默认会以/indexController/Voidfun作为返回的页面路径
    public void fun(PrintWriter writer, HttpServletResponse response){
        //设置响应类型，解决中文乱码
        response.setContentType("text/html;charset=utf-8");
        writer.write("Hello，返回void类型");
    }

    //JQuery的异步请求返回Json格式
    //value是指请求路径   produces指定返回类型
    //value处理路径的值要与方法名相同
    @RequestMapping(value = "/returnJson",produces = "application/json;charset=UTF-8")
    //如果不需要经过视图解析器则要加上@ResponseBody 否则会以/indexController控制器路径/returnJson方法名为页面的路径
    @ResponseBody
    public User returnJson(){
        User user=new User();
        user.setName("小巫");
        user.setAge(22);
        user.setTall(168);
        user.setBirthday(new Date());
        return user;
    }

    //JQuery的异步请求返回Json格式
    @RequestMapping(value = "/returnJson2",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<User> returnJson2(){
        List<User> userList=new ArrayList<>();
        for (int i=0;i<=10;i++){
            //实例化user对象要在循环内实例化，目的在于防止放在循环外，导致内存地址相同从而改变列表已经添加进去的user对象的值
            User user=new User();
            user.setName("小"+i);
            user.setAge(i);
            user.setTall(168+i);
            user.setBirthday(new Date());
            userList.add(user);
        }
        return userList;
    }

    //SpringMVC获取请求参数
    //1、自动类型匹配 （针对少量参数）
    @RequestMapping("/autoGetParam")
    public ModelAndView autoGetParam(String userName,String password,
                                     //将获取到的时间差自动转换为指定的字符
                                     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date Birthday){
        System.out.println(userName+"--->"+password);
        ModelAndView mv=new ModelAndView("/getCS");
        mv.addObject("userName",userName);
        mv.addObject("password",password);
        return mv;
    }

    //2、自动封装pojo (针对大量参数)
    @RequestMapping("/pojoGetParam")
    public ModelAndView pojoGetParam(User user){
        System.out.println(user.getName()+"--->"+user.getAge());
        ModelAndView mv=new ModelAndView("/getCS");
        mv.addObject("Name",user.getName());
        mv.addObject("Age",user.getAge());
        return mv;
    }

    //3、使用request获取请求参数
    @RequestMapping("/requestGetParam")
    public ModelAndView requestGetParam(HttpServletRequest request){
        String car=request.getParameter("car");
        String color=request.getParameter("color");
        System.out.println(car+"--->"+color);
        ModelAndView mv=new ModelAndView("/getCS");
        mv.addObject("car",car);
        mv.addObject("color",color);
        return mv;
    }

    //4、使用@RequestParam获取参数 （允许请求参数名与方法参数名不一致时）
    //value：参数名字  required：是否必须，默认是true，表示请求中一定要有相应的参数 defaultValue：默认值，
    @RequestMapping("/atRequestParam")
    public ModelAndView atRequestParam(@RequestParam(value = "money")String money,
      @RequestParam(value = "zy",required = false,defaultValue = "4")Integer zy){
        System.out.println(money+"--->"+zy);
        ModelAndView mv=new ModelAndView("getCS");
        mv.addObject("money",money);
        mv.addObject("zy",zy);
        return mv;
    }

    //5、@PathVariable注解 RestFull风格的URL请求
    @RequestMapping("/PathVariabl/{name}/{age}") //≈≈ /PathVariabl?name=username&age=userage
    //{name}{age}要与value的值对应
    public ModelAndView PathVariabl(@PathVariable(value = "name",required = true)String name,
                                    @PathVariable(value = "age")Integer age){
        System.out.println(name+"--->"+age);
        ModelAndView mv=new ModelAndView("getCS");
        mv.addObject("name",name);
        mv.addObject("age",age);
        return mv;
    }


    //6、ajax接收复杂参数
    @RequestMapping(value = "/ajaxjsData",produces = "application/json;charset=UTF-8")
    @ResponseBody //将返回的数据转换为指定的格式并写入到Response对象的body数据区,除了ModelAndView返回值，其余的返回值都不会经过视图解析器
    // (现在主要用于json返回)

    //@RequestBody 从body中读取请求的数据,将数据进行相应的解析
    // （现在更多的是把一个 json 字符串转换成一个定义好映射关系的对象）
    //  将json字符串反序列化
    public List<User> ajaxjsData(@RequestBody List<User> users){
        for (int i=0;i<users.size();i++){
            String name="ajax测试"+users.get(i).getName();
            users.get(i).setName(name);
        }
        return users; //将对象序列化为json对象
    }

}
