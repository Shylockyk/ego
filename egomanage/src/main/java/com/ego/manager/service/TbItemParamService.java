package com.ego.manager.service;

import com.ego.commons.pojo.EasyUIDataGrid;

/**
 * 商品规格参数业务层
 *
 * @Author: yk
 * @Date: 2019/6/8 14:46
 */
public interface TbItemParamService {

    /**
     * 分页显示
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGrid show(int page, int rows);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int delete(String ids) throws Exception;

}
