package com.wjg.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private String name;

    private Integer age;

    private Integer tall;

    private String password;

    //时间格式在ajax提交数据时要添加格式化注解，以便让SpringMVC好处理
    //用于接收参数的 自动封装
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //用于以json格式的请求  timezone时间区
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date Birthday;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getTall() {
        return tall;
    }

    public void setTall(Integer tall) {
        this.tall = tall;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", tall=" + tall +
                ", password='" + password + '\'' +
                ", Birthday=" + Birthday +
                '}';
    }
}
