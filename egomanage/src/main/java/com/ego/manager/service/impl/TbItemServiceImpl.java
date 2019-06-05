package com.ego.manager.service.impl;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.manager.service.TbItemService;
import com.ego.pojo.TbItem;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;


@Service
public class TbItemServiceImpl implements TbItemService {

    @Reference
    private TbItemDubboService tbItemDubboServiceImpl;

    @Override
    public EasyUIDataGrid show(int page, int rows) {
        return tbItemDubboServiceImpl.show(page, rows);
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
}
