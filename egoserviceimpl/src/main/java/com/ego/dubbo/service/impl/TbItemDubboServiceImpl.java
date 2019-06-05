package com.ego.dubbo.service.impl;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.mapper.TbItemMapper;
import com.ego.pojo.TbItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/4 21:41
 */

public class TbItemDubboServiceImpl implements TbItemDubboService {

    @Resource
    private TbItemMapper tbItemMapper;

    @Override
    public EasyUIDataGrid show(int page, int rows) {

        // 查询全部
        List<TbItem> tbItemList = tbItemMapper.selectAll();

        // 分页代码
        // 设置分页条件
        PageHelper.startPage(page, rows);

        // 放入到实体类
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemList);

        EasyUIDataGrid dataGrid = new EasyUIDataGrid();
        dataGrid.setRows(pageInfo.getList());
        dataGrid.setTotal(pageInfo.getTotal());

        return dataGrid;
    }
}
