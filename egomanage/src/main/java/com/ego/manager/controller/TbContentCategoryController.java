package com.ego.manager.controller;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.commons.pojo.EgoResult;
import com.ego.manager.service.TbContentCategoryService;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/9 11:12
 */

@Controller
public class TbContentCategoryController {

    @Resource
    private TbContentCategoryService tbContentCategoryServiceImpl;

    /**
     * 查询内容类目
     * @param id
     * @return
     */
    @RequestMapping("content/category/list")
    @ResponseBody
    public List<EasyUiTree> showCateagory(@RequestParam(defaultValue = "0") long id) {
        return tbContentCategoryServiceImpl.showCategory(id);
    }


    /**
     * 新增内容类目
     * @param tbContentCategory
     * @return
     */
    @RequestMapping("content/category/create")
    @ResponseBody
    public EgoResult createCategory(TbContentCategory tbContentCategory) {
        return tbContentCategoryServiceImpl.createCategory(tbContentCategory);
    }

    /**
     * 重命名
     * @param tbContentCategory
     * @return
     */
    @RequestMapping("content/category/update")
    @ResponseBody
    public EgoResult updateCategory(TbContentCategory tbContentCategory) {
        return tbContentCategoryServiceImpl.updateCategory(tbContentCategory);
    }

    /**
     * 删除内容类名
     * @param tbContentCategory
     * @return
     */
    @RequestMapping("content/category/delete")
    @ResponseBody
    public EgoResult deleteCategory(TbContentCategory tbContentCategory) {
        return tbContentCategoryServiceImpl.deleteCategory(tbContentCategory);
    }
}

