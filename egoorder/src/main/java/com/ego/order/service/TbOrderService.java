package com.ego.order.service;

import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemChild;
import com.ego.order.pojo.OrderParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/16 14:59
 */
public interface TbOrderService {

    /**
     * 订单信息包含的商品
     * @param ids
     * @return
     */
    List<TbItemChild> showOrderCart(List<Long> ids, HttpServletRequest request);

    /**
     * 创建订单
     * @param orderParam
     * @return
     */
    EgoResult createOrder(OrderParam orderParam, HttpServletRequest request);
}
