package com.ego.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: yk
 * @Date: 2019/6/8 20:34
 */

@Controller
public class PageController {

    @RequestMapping("/")
    public String welcome() {
        return "forward:/showBigPic";
    }

    @RequestMapping("{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

}
