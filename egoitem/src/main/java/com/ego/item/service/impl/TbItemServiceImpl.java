package com.ego.item.service.impl;

import com.ego.commons.pojo.TbItemChild;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.item.service.TbItemService;
import com.ego.pojo.TbItem;
import com.ego.redis.dao.JedisClusterDao;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class TbItemServiceImpl implements TbItemService {

    @Reference
    private TbItemDubboService tbItemDubboServiceImpl;

    @Resource
    private JedisClusterDao jedisClusterDaoImpl;

    @Value(("${redis.itemChild.key}"))
    private String key;

    @Override
    public TbItemChild showTbItemDetail(long id) {

        String itemKey = key + id + ":";
        if (jedisClusterDaoImpl.exist(itemKey)) {
            String value = jedisClusterDaoImpl.get(itemKey);
            if (value != null && !value.equals("")) {
                return JsonUtils.jsonToPojo(value, TbItemChild.class);
            }
        }



        TbItem tbItem = tbItemDubboServiceImpl.selectById(id);
        TbItemChild child = new TbItemChild();
        child.setId(tbItem.getId());
        child.setTitle(tbItem.getTitle());
        child.setPrice(tbItem.getPrice());
        child.setSellPoint(tbItem.getSellPoint());
        child.setImages(tbItem.getImage() == null || tbItem.getImage().equals("")?new String[1]:tbItem.getImage().split(","));

        jedisClusterDaoImpl.set(itemKey, JsonUtils.objectToJson(child));

        return child;
    }
}
