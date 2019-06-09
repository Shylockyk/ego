package com.ego.manager.controller;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manager.service.TbContentService;
import com.ego.pojo.TbContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 商品内容
 *
 * @Author: yk
 * @Date: 2019/6/9 13:16
 */

@Controller
public class TbContentController {

    @Resource
    private TbContentService tbContentServiceImpl;

    @RequestMapping("content/query/list")
    @ResponseBody
    public EasyUIDataGrid query(long categoryId, int page, int rows) {
        return tbContentServiceImpl.selectContentByPage(categoryId, page, rows);
    }

    @RequestMapping("content/save")
    @ResponseBody
    public EgoResult save(TbContent tbContent) {
        return tbContentServiceImpl.addContent(tbContent);
    }
}
