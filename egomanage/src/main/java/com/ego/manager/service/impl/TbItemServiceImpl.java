package com.ego.manager.service.impl;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.manager.service.TbItemService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class TbItemServiceImpl implements TbItemService {

    @Reference
    private TbItemDubboService tbItemDubboServiceImpl;

    @Reference
    private TbItemDescDubboService tbItemDescDubboServiceImpl;

    @Override
    public EasyUIDataGrid show(int page, int rows) {
        // 分页代码
        // 设置分页条件
        PageHelper.startPage(page, rows);
        // 查询全部
        List<TbItem> tbItemList = tbItemDubboServiceImpl.show(page, rows);


        // 放入到实体类
        PageInfo<TbItem> pageInfo = new PageInfo<>(tbItemList);

        EasyUIDataGrid dataGrid = new EasyUIDataGrid();
        dataGrid.setRows(pageInfo.getList());
        dataGrid.setTotal(pageInfo.getTotal());

        return dataGrid;
    }

    @Override
    public int update(String ids, byte status) {
        int count = 0;
        TbItem tbItem = new TbItem();
        String[] idsStr = ids.split(",");
        for (String id : idsStr) {
            tbItem.setId(Long.valueOf(id));
            tbItem.setStatus(status);
            tbItemDubboServiceImpl.updateItemStatus(tbItem);
            count++;
        }
        if(count == idsStr.length)
            return 1;
        return 0;
    }

    @Override
    public int save(TbItem tbItem, String desc, String itemParams) throws Exception {
        /*不考虑事务回滚*/
        /*long id = IDUtils.genItemId();
        tbItem.setId(id);
        Date now = new Date();
        tbItem.setCreated(now);
        tbItem.setUpdated(now);
        tbItem.setStatus((byte)1);

        int index = tbItemDubboServiceImpl.insertTbItem(tbItem);
        if (index > 0) {
            TbItemDesc itemDesc = new TbItemDesc();
            itemDesc.setItemDesc(desc);
            itemDesc.setItemId(id);
            itemDesc.setCreated(now);
            itemDesc.setUpdated(now);
            index += tbItemDescDubboServiceImpl.insertDesc(itemDesc);
        }
        if (index == 2) {
            return 1;
        }*/
        /*调用dubbo中考虑事务回滚的方法*/
        long id = IDUtils.genItemId();
        tbItem.setId(id);
        Date now = new Date();
        tbItem.setCreated(now);
        tbItem.setUpdated(now);
        tbItem.setStatus((byte)1);

        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(id);
        itemDesc.setCreated(now);
        itemDesc.setUpdated(now);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setCreated(now);
        tbItemParamItem.setUpdated(now);
        tbItemParamItem.setItemId(id);
        tbItemParamItem.setParamData(itemParams);

        return tbItemDubboServiceImpl.insertTbItemAndDesc(tbItem, itemDesc, tbItemParamItem);
    }
}
