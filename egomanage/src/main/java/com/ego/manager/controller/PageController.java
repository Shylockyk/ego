package com.ego.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面控制器
 *
 * @Author: yk
 * @Date: 2019/6/4 20:49
 */
@Controller
public class PageController {
    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

    @RequestMapping("{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }
}
