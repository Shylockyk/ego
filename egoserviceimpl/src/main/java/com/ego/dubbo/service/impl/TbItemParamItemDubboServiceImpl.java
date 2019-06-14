package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbItemParamItemDubboService;
import com.ego.mapper.TbItemParamItemMapper;
import com.ego.pojo.TbItemParamItem;
import com.ego.pojo.TbItemParamItemExample;

import javax.annotation.Resource;
import java.util.List;

public class TbItemParamItemDubboServiceImpl implements TbItemParamItemDubboService {

    @Resource
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public TbItemParamItem selectByItemId(long itemId) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        example.createCriteria().andItemIdEqualTo(itemId);
        List<TbItemParamItem> tbItemParamItems = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if (tbItemParamItems != null && tbItemParamItems.size() > 0) {
            return tbItemParamItems.get(0);
        }
        return null;
    }
}
