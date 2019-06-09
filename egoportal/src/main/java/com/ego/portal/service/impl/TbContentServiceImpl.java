package com.ego.portal.service.impl;

import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.pojo.TbContent;
import com.ego.portal.service.TbContentService;
import com.ego.redis.dao.JedisClusterDao;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yk
 * @Date: 2019/6/9 14:18
 */

@Service
public class TbContentServiceImpl implements TbContentService {

    @Reference
    private TbContentDubboService tbContentDubboServiceImpl;

    @Resource
    private JedisClusterDao jedisClusterDaoImpl;

    @Value("${redis.bigPic.key}")
    private String key;

    @Override
    public String showBigPic() {

        if (jedisClusterDaoImpl.exist(key)) {
            String value = jedisClusterDaoImpl.get(key);
            if (value != null && !value.equals("")) {
                return value;
            }
        }


        List<TbContent> tbContents = tbContentDubboServiceImpl.selectByCount(6, true);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (TbContent tbContent : tbContents) {
            Map<String, Object> map = new HashMap<>();
            map.put("src", tbContent.getPic());
            map.put("srcB", tbContent.getPic2());
            map.put("height", 240);
            map.put("width", 670);
            map.put("alt", "对不起，加载图片失败");
            map.put("widthB", 550);
            map.put("heightB", 240);
            map.put("href", tbContent.getUrl());

            mapList.add(map);
        }
        String listJson = JsonUtils.objectToJson(mapList);
        jedisClusterDaoImpl.set(key, listJson);
        return listJson;
    }
}
