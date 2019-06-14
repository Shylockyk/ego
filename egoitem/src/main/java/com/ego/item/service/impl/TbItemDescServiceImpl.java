package com.ego.item.service.impl;

import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.item.service.TbItemDescService;
import com.ego.redis.dao.JedisClusterDao;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TbItemDescServiceImpl implements TbItemDescService {

    @Reference
    private TbItemDescDubboService tbItemDescDubboServiceImpl;

    @Resource
    private JedisClusterDao jedisClusterDaoImpl;

    @Value(("${redis.itemDesc.key}"))
    private String key;

    @Override
    public String showDesc(long itemId) {
        String itemKey = key + itemId + ":";
        if (jedisClusterDaoImpl.exist(itemKey)) {
            String value = jedisClusterDaoImpl.get(itemKey);
            if (value != null && !value.equals("")) {
                return value;
            }
        }

        String itemDesc = tbItemDescDubboServiceImpl.selectByItemId(itemId).getItemDesc();
        jedisClusterDaoImpl.set(itemKey, itemDesc);
        return itemDesc;
    }
}
