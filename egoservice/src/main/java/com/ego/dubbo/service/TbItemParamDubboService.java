package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItemParam;

import java.util.List;

/**
 * 商品规格参数业务层
 *
 * @Author: yk
 * @Date: 2019/6/8 14:23
 */
public interface TbItemParamDubboService {

    /**
     * 分页查询数据
     * @param page 第几页
     * @param rows 显示条数
     * @return 包含当前页显示数据和总条数
     */
    /*EasyUIDataGrid show(int page, int rows);*/

    /**
     * 分页查询数据
     * @param page 第几页
     * @param rows 显示条数
     * @return 包含当前页显示数据和总条数
     */
    List<TbItemParam> show(int page, int rows);


    /**
     * 根据ids删除一个或多个商品规格参数实体
     * @param ids
     * @return
     */
    int deleteByIds(String ids) throws Exception;

}
