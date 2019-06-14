package com.ego.dubbo.service;

import com.ego.pojo.TbItemParamItem;

public interface TbItemParamItemDubboService {

    /**
     * 根据商品id查询具体规格参数
     * @param itemId
     * @return
     */
    TbItemParamItem selectByItemId(long itemId);
}
