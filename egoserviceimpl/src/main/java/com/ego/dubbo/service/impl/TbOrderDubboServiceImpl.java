package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbOrderDubboService;
import com.ego.mapper.TbOrderItemMapper;
import com.ego.mapper.TbOrderMapper;
import com.ego.mapper.TbOrderShippingMapper;
import com.ego.pojo.TbOrder;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderShipping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/16 16:06
 */
public class TbOrderDubboServiceImpl implements TbOrderDubboService {

    @Resource
    private TbOrderMapper tbOrderMapper;

    @Resource
    private TbOrderItemMapper tbOrderItemMapper;

    @Resource
    private TbOrderShippingMapper tborderShippingMapper;

    @Override
    public int insertOrder(TbOrder tbOrder, List<TbOrderItem> list, TbOrderShipping tbOrderShipping) throws Exception {
        int index = tbOrderMapper.insertSelective(tbOrder);
        for (TbOrderItem tbOrderItem : list) {
            index += tbOrderItemMapper.insertSelective(tbOrderItem);
        }
        index += tborderShippingMapper.insertSelective(tbOrderShipping);

        if (index == 2 + list.size()) {
            return 1;
        } else {
            throw new Exception("创建订单失败");
        }
    }
}
