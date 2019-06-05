package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;

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
}
