package com.ego.manager.service.impl;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.manager.service.TbContentService;
import com.ego.pojo.TbContent;
import com.ego.redis.dao.JedisClusterDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yk
 * @Date: 2019/6/9 13:12
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
    public EasyUIDataGrid selectContentByPage(long categoryId, int page, int rows) {

        PageHelper.startPage(page, rows);

        PageInfo<TbContent> pageInfo = new PageInfo<>(tbContentDubboServiceImpl.selectContentByPage(categoryId));

        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setRows(pageInfo.getList());
        easyUIDataGrid.setTotal(pageInfo.getTotal());

        return easyUIDataGrid;
    }

    @Override
    public EgoResult addContent(TbContent tbContent) {
        EgoResult egoResult = new EgoResult();
        Date now = new Date();
        tbContent.setCreated(now);
        tbContent.setUpdated(now);
        int index = tbContentDubboServiceImpl.insertContent(tbContent);
        if (index > 0) {

            if (jedisClusterDaoImpl.exist(key)) {
                String value = jedisClusterDaoImpl.get(key);
                if (value != null && !value.equals("")) {
                    List<Map> mapList = JsonUtils.jsonToList(value, Map.class);

                    Map<String, Object> map = new HashMap<>();
                    map.put("src", tbContent.getPic());
                    map.put("srcB", tbContent.getPic2());
                    map.put("height", 240);
                    map.put("width", 670);
                    map.put("alt", "对不起，加载图片失败");
                    map.put("widthB", 550);
                    map.put("heightB", 240);
                    map.put("href", tbContent.getUrl());

                    if (mapList.size() == 6) {
                        mapList.remove(5);
                    }
                    mapList.add(0, map);

                    jedisClusterDaoImpl.set(key, JsonUtils.objectToJson(mapList));
                }
            }

            egoResult.setStatus(200);
            return egoResult;
        }
        return egoResult;
    }
}
