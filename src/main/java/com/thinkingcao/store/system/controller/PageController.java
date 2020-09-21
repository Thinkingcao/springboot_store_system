package com.thinkingcao.store.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @desc:  页面跳转Controller
 * @author: cao_wencao
 * @date: 2020-09-21 23:17
 */
@Controller
@RequestMapping("page")
public class PageController {
    /**
     * 跳转到登陆页面
     */
    @RequestMapping("toLogin")
    public String toLogin() {
        return "system/index/login";
    }

    /**
     * 跳转到首页
     */
    @RequestMapping("toIndex")
    public String index() {
        return "system/index/index";
    }
}
