package com.ego.manager.service.impl;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.manager.pojo.TbItemParamChild;
import com.ego.manager.service.TbItemParamService;
import com.ego.pojo.TbItemParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/8 14:47
 */

@Service
public class TbItemParamServiceImpl implements TbItemParamService {

    @Reference
    private TbItemParamDubboService tbItemParamDubboServiceImpl;

    @Reference
    private TbItemCatDubboService tbItemCatDubboServiceImpl;

    @Override
    public EasyUIDataGrid show(int page, int rows) {
        // 先设置分页条件
        PageHelper.startPage(page, rows);
        // 查询全部，设置查询sql语句
        List<TbItemParam> list = tbItemParamDubboServiceImpl.show(page, rows);

        List<TbItemParamChild> newList = new ArrayList<>();
        for (TbItemParam tbItemParam : list) {
            TbItemParamChild child = new TbItemParamChild();
            child.setId(tbItemParam.getId());
            child.setItemCatId(tbItemParam.getItemCatId());
            child.setCreated(tbItemParam.getCreated());
            child.setUpdated(tbItemParam.getUpdated());
            child.setParamData(tbItemParam.getParamData());
            child.setItemCatName(tbItemCatDubboServiceImpl.findById(tbItemParam.getItemCatId()).getName());

            newList.add(child);
        }

        // 根据全部结果取出分页结果
        PageInfo<TbItemParamChild> pageInfo = new PageInfo<>(newList);

        // 设置方法返回结果
        EasyUIDataGrid dataGrid = new EasyUIDataGrid();
        dataGrid.setRows(pageInfo.getList());
        dataGrid.setTotal(pageInfo.getTotal());

        return dataGrid;

    }

    @Override
    public int delete(String ids) throws Exception {
        return tbItemParamDubboServiceImpl.deleteByIds(ids);
    }
}
