package com.ego.manager.service;

import com.ego.commons.pojo.EasyUIDataGrid;

public interface TbItemService {


    /**
     * 显示商品
     * @param page 第几页
     * @param rows 一页显示条数
     * @return
     */
    EasyUIDataGrid show(int page, int rows);

    /**
     * 批量修改商品状态
     * @param ids 一个或多个id
     * @param status
     * @return
     */
    int update(String ids, byte status);
}
