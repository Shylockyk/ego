package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamExample;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/8 14:26
 */
public class TbItemParamServiceImpl implements TbItemParamDubboService {

    @Resource
    private TbItemParamMapper tbItemParamMapper;

    /*@Override
    public EasyUIDataGrid show(int page, int rows) {
        // 先设置分页条件
        PageHelper.startPage(page, rows);
        // 查询全部，设置查询sql语句
        List<TbItemParam> list = tbItemParamMapper.selectAll();
        // 根据全部结果取出分页结果
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);

        // 设置方法返回结果
        EasyUIDataGrid dataGrid = new EasyUIDataGrid();
        dataGrid.setRows(pageInfo.getList());
        dataGrid.setTotal(pageInfo.getTotal());

        return dataGrid;
    }*/

    public List<TbItemParam> show(int page, int rows) {
        TbItemParamExample example = new TbItemParamExample();
        return tbItemParamMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public int deleteByIds(String ids) throws Exception {

        int index = 0;
        String[] idsStr = ids.split(",");
        for (String id : idsStr) {
            index += tbItemParamMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
        if (index == idsStr.length) {
            return 1;
        } else {
            throw new Exception("删除失败，可能是数据已经被更改过！");
        }

    }

    @Override
    public TbItemParam selectByCarId(long id) {
        return null;
    }
}
