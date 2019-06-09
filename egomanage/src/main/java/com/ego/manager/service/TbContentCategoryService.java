package com.ego.manager.service;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContentCategory;

import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/9 11:06
 */
public interface TbContentCategoryService {

    /**
     * 查询内容类目并转换为easyui tree类型
     * @return
     */
    List<EasyUiTree> showCategory(long id);

    /**
     * 新增内容类目
     * @param tbContentCategory
     * @return
     */
    EgoResult createCategory(TbContentCategory tbContentCategory);

    /**
     * 修改内容类目
     * @param tbContentCategory
     * @return
     */
    EgoResult updateCategory(TbContentCategory tbContentCategory);


    /**
     * 查看名字是否存在
     * @param tbContentCategory
     * @return
     */
    boolean nameIsExists(TbContentCategory tbContentCategory);

    /**
     * 删除内容类目
     * @param tbContentCategory
     * @return
     */
    EgoResult deleteCategory(TbContentCategory tbContentCategory);
}
