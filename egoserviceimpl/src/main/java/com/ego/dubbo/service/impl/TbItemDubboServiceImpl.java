package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbItemDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.mapper.TbItemMapper;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemExample;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品业务层实现，不需要交给spring处理，只提供给dubbo注册服务
 *
 * @Author: yk
 * @Date: 2019/6/4 21:41
 */

public class TbItemDubboServiceImpl implements TbItemDubboService {

    @Resource
    private TbItemMapper tbItemMapper;

    @Resource
    private TbItemDescMapper tbItemDescMapper;

    /*@Override
    public EasyUIDataGrid show(int page, int rows) {

        // 分页代码
        // 设置分页条件
        PageHelper.startPage(page, rows);
        // 查询全部
        List<TbItem> tbItemList = tbItemMapper.selectAll();


        // 放入到实体类
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemList);

        EasyUIDataGrid dataGrid = new EasyUIDataGrid();
        dataGrid.setRows(pageInfo.getList());
        dataGrid.setTotal(pageInfo.getTotal());

        return dataGrid;
    }*/

    @Override
    public List<TbItem> show(int page, int rows) {
        return tbItemMapper.selectAll();
    }

    @Override
    public int updateItemStatus(TbItem tbItem) {
        return tbItemMapper.updateByPrimaryKeySelective(tbItem);
    }

    @Override
    public int insertTbItem(TbItem tbItem) {
        return tbItemMapper.insert(tbItem);
    }

    @Override
    public int insertTbItemAndDesc(TbItem tbItem, TbItemDesc tbItemDesc) throws Exception {
        int index = 0;
        try {
            index = tbItemMapper.insertSelective(tbItem);
            index += tbItemDescMapper.insertSelective(tbItemDesc);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        if (index == 2) {
            return 1;
        } else {
            System.out.println("执行抛出");
            throw new Exception("新增失败,业务回滚");
        }
    }
}
