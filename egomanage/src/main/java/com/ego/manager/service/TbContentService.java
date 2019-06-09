package com.ego.manager.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContent;

/**
 * @Author: yk
 * @Date: 2019/6/9 13:10
 */
public interface TbContentService {

    /**
     * 分页查询指定内容类目的内容，并转换为EasyUIDataGrid
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGrid selectContentByPage(long categoryId, int page, int rows);


    /**
     * 新增内容
     * @param tbContent
     * @return
     */
    public EgoResult addContent(TbContent tbContent);
}
