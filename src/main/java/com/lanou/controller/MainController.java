package com.lanou.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lizhongren1
 */
@Controller
public class MainController {

    @RequestMapping(value = {"/home","/"})
    public String homePage() {


        if (SecurityUtils.getSubject().hasRole("CEO")){
            return "redirect:success";
        }

        return "home";
    }

    // 登录页面的地址导航
    @RequestMapping(value = "/login")
    public String loginpage() {

        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:home";
        }

        return "login";
    }

    @RequestMapping(value = "/loginsubmit")
    public String loginsubmit(HttpServletRequest request) throws Exception {

        // 因为表单过滤器的原因, 这个方法不需要接收用户名密码,再进行login
        // 而是由过滤器直接把用户名密码交给realm来做认证

        // 我们在这个方法中, 就只处理异常就可以了

        // 过滤器先于servlet执行, 所以当在realm中处理认证时出现异常
        // 过滤器会把异常信息保存到request中发给Servlet, 即交给我们来处理

        // 1. 获取异常的名字

        String exName =
                (String) request.getAttribute("shiroLoginFailure");

        if (UnknownAccountException.class.getName().equals(exName)) {
            throw new UnknownAccountException("账号不存在");
        } else if (IncorrectCredentialsException.class.getName().equals(exName)) {
            throw new IncorrectCredentialsException("密码错误");
        } else {
            throw new Exception("我也不知道为啥不对");
        }
    }

//    @RequiresRoles(value = "CTO")
    @RequestMapping(value = "/test")
    public String test() {

        if (SecurityUtils.getSubject().hasRole("CTO")){
            return "test";
        } else {
            return "redirect:home";
        }
    }

    @RequestMapping(value = "/success")
    public String sec(){
        return "/sec";
    }



}
