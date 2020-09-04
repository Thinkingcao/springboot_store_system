package com.thinkingcao.store.system.controller;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @desc:
 * @author: cao_wencao
 * @date: 2020-09-04 17:32
 */
@Controller
public class LoginController {

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录验证
     *
     * @param username
     * @param password
     * @param model
     * @return
     */
    @PostMapping("/")
    public String postlogin(@Param("username") String username, @Param("password") String password, Model model) {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(username, password));
        } catch (UnknownAccountException e) {
            model.addAttribute("error", "用户名不存在！");
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("error", "密码错误！");
        }
        return "login";
    }


    /**
     * 登出账户
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
}
