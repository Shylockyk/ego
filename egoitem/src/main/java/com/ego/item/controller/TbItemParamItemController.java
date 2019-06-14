package com.ego.item.controller;


import com.ego.item.service.TbItemParamItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TbItemParamItemController {

    @Resource
    private TbItemParamItemService tbItemParamItemServiceImpl;


    /**
     * 显示规格参数
     * @param itemId
     * @return
     */
    @RequestMapping(value = "item/param/{itemId}.html", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String getParamItem(@PathVariable long itemId) {
        return tbItemParamItemServiceImpl.getParamItem(itemId);
    }
}
