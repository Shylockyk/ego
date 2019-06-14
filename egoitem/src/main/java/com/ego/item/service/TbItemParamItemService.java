package com.ego.item.service;

public interface TbItemParamItemService {

    /**
     * 根据商品id查询具体规格参数
     * @param itemId
     * @return
     */
    String getParamItem(long itemId);
}
