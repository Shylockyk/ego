package com.ego.manager.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbItemParam;

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

    /**
     * 根据商品类目id查询规格模版
     * @param catId
     * @return
     */
    EgoResult findParamByCatId(long catId);


    /**
     * 新增规格模版信息
     * @param tbItemParam
     * @return
     */
    EgoResult save(TbItemParam tbItemParam);
}
