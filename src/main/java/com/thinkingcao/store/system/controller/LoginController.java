package com.thinkingcao.store.system.controller;

import com.thinkingcao.store.common.utils.ResultObj;
import com.thinkingcao.store.common.utils.WebUtils;
import com.thinkingcao.store.system.common.ActiverUser;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @desc:  登录Controller
 * @author: cao_wencao
 * @date: 2020-09-04 17:32
 */
@Controller
@RequestMapping("api")
public class LoginController {

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

    @RequestMapping("login")
    public ResultObj login(String loginname, String pwd) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token=new UsernamePasswordToken(loginname, pwd);
        try {
            subject.login(token);
            ActiverUser activerUser=(ActiverUser) subject.getPrincipal();
            WebUtils.getSession().setAttribute("user", activerUser.getUser());
            return ResultObj.LOGIN_SUCCESS;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResultObj.LOGIN_ERROR_PASS;
        }
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
