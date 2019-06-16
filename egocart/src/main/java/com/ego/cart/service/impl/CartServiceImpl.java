package com.ego.cart.service.impl;

import com.ego.cart.service.CartService;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemChild;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItem;
import com.ego.redis.dao.JedisClusterDao;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Author: yk
 * @Date: 2019/6/16 10:25
 */
@Service
public class CartServiceImpl implements CartService {

    @Resource
    private JedisClusterDao jedisClusterDaoImpl;

    @Reference
    private TbItemDubboService tbItemDubboServiceImpl;

    @Value("${passport.url}")
    private String passportUrl;

    @Value("${redis.cart.key}")
    private String key;

    private final static String cookieName = "TT_TOKEN";

    @Override
    public void addCart(long id, int num, HttpServletRequest request) {

        String token = CookieUtils.getCookieValue(request, cookieName);
        String result = HttpClientUtil.doPost(passportUrl + token);
        EgoResult egoResult = JsonUtils.jsonToPojo(result, EgoResult.class);

        TbItem tbItem = tbItemDubboServiceImpl.selectById(id);
        TbItemChild child = new TbItemChild();
        child.setId(tbItem.getId());
        child.setTitle(tbItem.getTitle());
        child.setImages(tbItem.getImage() == null || tbItem.getImage().equals("")?new String[1]:tbItem.getImage().split(","));
        child.setNum(num);
        child.setPrice(tbItem.getPrice());
        child.setSellPoint(tbItem.getSellPoint());

        String userKey = key + ((LinkedHashMap) Objects.requireNonNull(egoResult).getData()).get("username");
        boolean isExists = false;
        if (jedisClusterDaoImpl.exist(userKey)) {
            String value = jedisClusterDaoImpl.get(userKey);
            if (value != null && !value.equals("")) {
                List<TbItemChild> list = JsonUtils.jsonToList(value, TbItemChild.class);
                for (TbItemChild tbItemChild : list) {
                    if (tbItemChild.getId() == id) {
                        isExists = true;
                        tbItemChild.setNum(tbItemChild.getNum() + num);
                    }
                }
                if (!isExists) {
                    list.add(child);
                }
                jedisClusterDaoImpl.set(userKey, JsonUtils.objectToJson(list));
            }
        } else {
            List<TbItemChild> list = new ArrayList<>();
            list.add(child);
            jedisClusterDaoImpl.set(userKey, JsonUtils.objectToJson(list));
        }

    }

    @Override
    public List<TbItemChild> getCart(HttpServletRequest request) {
        String token = CookieUtils.getCookieValue(request, cookieName);
        String result = HttpClientUtil.doPost(passportUrl + token);
        EgoResult egoResult = JsonUtils.jsonToPojo(result, EgoResult.class);
        return JsonUtils.jsonToList(jedisClusterDaoImpl.get(key + ((LinkedHashMap) Objects.requireNonNull(egoResult).getData()).get("username")), TbItemChild.class);
    }

    @Override
    public EgoResult updateCartNum(long id, int num, HttpServletRequest request) {
        String token = CookieUtils.getCookieValue(request, cookieName);
        String result = HttpClientUtil.doPost(passportUrl + token);
        EgoResult egoResult = JsonUtils.jsonToPojo(result, EgoResult.class);
        String userKey = key + ((LinkedHashMap) Objects.requireNonNull(egoResult).getData()).get("username");
        List<TbItemChild> list = JsonUtils.jsonToList(jedisClusterDaoImpl.get(userKey), TbItemChild.class);
        for (TbItemChild child : list) {
            if (child.getId() == id) {
                child.setNum(num);
                jedisClusterDaoImpl.set(userKey, JsonUtils.objectToJson(list));
            }
        }
        return egoResult;
    }

    @Override
    public EgoResult deleteCartItem(long id, HttpServletRequest request) {
        String token = CookieUtils.getCookieValue(request, cookieName);
        String result = HttpClientUtil.doPost(passportUrl + token);
        EgoResult egoResult = JsonUtils.jsonToPojo(result, EgoResult.class);
        String userKey = key + ((LinkedHashMap) Objects.requireNonNull(egoResult).getData()).get("username");
        List<TbItemChild> list = JsonUtils.jsonToList(jedisClusterDaoImpl.get(userKey), TbItemChild.class);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(i);
                break;
            }
        }
        jedisClusterDaoImpl.set(userKey, JsonUtils.objectToJson(list));
        return egoResult;
    }


}
