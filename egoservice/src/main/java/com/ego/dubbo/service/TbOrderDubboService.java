package com.ego.dubbo.service;

import com.ego.pojo.TbOrder;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderShipping;

import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/16 16:05
 */
public interface TbOrderDubboService {

    /**
     * 创建订单
     * @param tbOrder
     * @param list
     * @param tbOrderShipping
     * @return
     */
    int insertOrder(TbOrder tbOrder, List<TbOrderItem> list, TbOrderShipping tbOrderShipping) throws Exception;
}
