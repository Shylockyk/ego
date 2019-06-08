package com.ego.dubbo.service;

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
     * 根据ids删除一个或多个商品规格参数模版
     * @param ids
     * @return
     */
    int deleteByIds(String ids) throws Exception;


    /**
     * 根据规格参数模版的商品类目id查询规格参数，其中商品类目id和规格参数模版id是一对一的
     * @param id
     * @return
     */
    TbItemParam selectByCarId(long id);
}
