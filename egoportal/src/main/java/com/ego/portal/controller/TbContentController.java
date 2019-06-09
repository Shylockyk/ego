package com.ego.portal.controller;

import com.ego.portal.service.TbContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 内容控制
 *
 * @Author: yk
 * @Date: 2019/6/9 14:27
 */
@Controller
public class TbContentController {

    @Resource
    private TbContentService tbContentServiceImpl;


    @RequestMapping("showBigPic")
    public String showBigPic(Model model) {
        model.addAttribute("ad1", tbContentServiceImpl.showBigPic());
        return "index";
    }

}
