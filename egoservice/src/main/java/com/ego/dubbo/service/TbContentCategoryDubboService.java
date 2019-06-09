package com.ego.dubbo.service;

import com.ego.pojo.TbContentCategory;

import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/9 11:02
 */
public interface TbContentCategoryDubboService {

    /**
     * 根据父id查询所有子类目
     * @param pid
     * @return
     */
    List<TbContentCategory> selectByPid(long pid);

    /**
     * 新增内容类目
     * @param tbContentCategory
     * @return
     */
    int insertContentCategory(TbContentCategory tbContentCategory);

    /**
     * 通过id修改isParent属性
     * @param tbContentCategory
     * @return
     */
    int updateContentCategoryById(TbContentCategory tbContentCategory);

    /**
     * 根据id查询内容类名
     * @param id
     * @return
     */
    TbContentCategory selectContentCategoryById(long id);
}
