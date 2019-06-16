package com.ego.order.service.impl;

import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemChild;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.IDUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.dubbo.service.TbOrderDubboService;
import com.ego.order.pojo.OrderParam;
import com.ego.order.service.TbOrderService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbOrder;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderShipping;
import com.ego.redis.dao.JedisClusterDao;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: yk
 * @Date: 2019/6/16 15:03
 */

@Service
public class TbOrderServiceImpl implements TbOrderService {

    @Resource
    private JedisClusterDao jedisClusterDaoImpl;

    @Reference
    private TbItemDubboService tbItemDubboServiceImpl;

    @Reference
    private TbOrderDubboService tbOrderDubboServiceImpl;

    @Value("${passport.url}")
    private String passportUrl;

    @Value("${redis.cart.key}")
    private String key;

    private final static String cookieName = "TT_TOKEN";

    @Override
    public List<TbItemChild> showOrderCart(List<Long> ids, HttpServletRequest request) {
        String token = CookieUtils.getCookieValue(request, cookieName);
        String result = HttpClientUtil.doPost(passportUrl + token);
        EgoResult egoResult = JsonUtils.jsonToPojo(result, EgoResult.class);
        String userKey = key + ((LinkedHashMap) Objects.requireNonNull(egoResult).getData()).get("username");
        List<TbItemChild> list = JsonUtils.jsonToList(jedisClusterDaoImpl.get(userKey), TbItemChild.class);

        List<TbItemChild> resultList = new ArrayList<>();
        for (TbItemChild child : list) {
            for (Long id : ids) {
                if (id.equals(child.getId())) {
                    TbItem tbItem = tbItemDubboServiceImpl.selectById(id);
                    child.setEnough(tbItem.getNum() >= child.getNum());
                    resultList.add(child);
                }
            }
        }

        return resultList;
    }

    @Override
    public EgoResult createOrder(OrderParam orderParam, HttpServletRequest request) {
        // 订单表
        TbOrder tbOrder = new TbOrder();
        tbOrder.setPayment(orderParam.getPayment());
        tbOrder.setPaymentType(orderParam.getPaymentType());
        long id = IDUtils.genItemId();
        tbOrder.setOrderId(id+"");
        Date now = new Date();
        tbOrder.setCreateTime(now);
        tbOrder.setUpdateTime(now);
        String token = CookieUtils.getCookieValue(request, cookieName);
        String result = HttpClientUtil.doPost(passportUrl + token);
        EgoResult egoResult = JsonUtils.jsonToPojo(result, EgoResult.class);
        Map user = ((LinkedHashMap) Objects.requireNonNull(egoResult).getData());
        tbOrder.setUserId(Long.parseLong(user.get("id").toString()));
        tbOrder.setBuyerNick((String) user.get("username"));
        tbOrder.setBuyerRate(0);

        // 订单-商品表
        for (TbOrderItem tbOrderItem : orderParam.getOrderItems()) {
            tbOrderItem.setId(IDUtils.genItemId()+"" + Math.random() * 10);
            tbOrderItem.setOrderId(id+"");
        }
        //收货人信息
        TbOrderShipping orderShipping = orderParam.getOrderShipping();
        orderShipping.setOrderId(id + "");
        orderShipping.setUpdated(now);
        orderShipping.setCreated(now);

        EgoResult egoResult1 = new EgoResult();
        try {
            int index = tbOrderDubboServiceImpl.insertOrder(tbOrder, orderParam.getOrderItems(), orderShipping);
            if (index > 0) {
                egoResult1.setStatus(200);
                String userKey = key + user.get("username");
                // 删除购买成功的商品
                List<TbItemChild> list = JsonUtils.jsonToList(jedisClusterDaoImpl.get(userKey), TbItemChild.class);
                for (int i = 0; i < Objects.requireNonNull(list).size(); i ++) {
                    for (TbOrderItem tbOrderItem : orderParam.getOrderItems()) {
                        if (list.get(i).getId().toString().equals(tbOrderItem.getItemId())) {
                            list.remove(i);
                        }
                    }
                }
                jedisClusterDaoImpl.set(userKey, JsonUtils.objectToJson(list));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return egoResult1;
    }
}
