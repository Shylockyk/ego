package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;

import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/4 21:36
 */
public interface TbItemDubboService {

    /**
     * 商品分页查询
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGrid show(int page, int rows);


    /**
     * 根据id修改商品状态
     * @param tbItem 被修改的商品
     * @return
     */
    int updateItemStatus(TbItem tbItem);


    /**
     * 商品新增
     * @param tbItem
     * @return
     */
    int insertTbItem(TbItem tbItem);

    /**
     * 新增商品和商品描述
     * @param tbItem
     * @param tbItemDesc
     * @return
     */
    int insertTbItemAndDesc(TbItem tbItem, TbItemDesc tbItemDesc) throws Exception;
}
