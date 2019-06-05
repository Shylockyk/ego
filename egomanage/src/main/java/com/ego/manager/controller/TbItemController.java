package com.ego.manager.controller;


import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.manager.service.TbItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class TbItemController {

    @Resource
    private TbItemService tbItemServiceImpl;


    /**
     * 分页显示商品
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("item/list")
    @ResponseBody
    public EasyUIDataGrid list(int page, int rows) {
        return tbItemServiceImpl.show(page, rows);
    }
}
