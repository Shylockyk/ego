package com.ego.item.controller;

import com.ego.item.service.TbItemCatService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author: yk
 * @Date: 2019/6/8 21:39
 */

@Controller
public class TbItemCatController {

    @Resource
    private TbItemCatService tbItemCatServiceImpl;


    /**
     * 返回jsonp数据格式包含所有菜单信息
     * @param callback
     * @return
     */
    @RequestMapping("rest/itemcat/all")
    @ResponseBody
    public Object showMenu(String callback) {
        /* MappingJacksonValue 已经被替代，这个方法更简单*/
        return new JSONPObject(callback, tbItemCatServiceImpl.showCatMenu());
    }
}
