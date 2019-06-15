package com.ego.passport.service.impl;

import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbUserDubboService;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;
import com.ego.redis.dao.JedisClusterDao;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author: yk
 * @Date: 2019/6/15 10:56
 */

@Service
public class TbUserServiceImpl implements TbUserService {

    @Reference
    private TbUserDubboService tbUserDubboServiceImpl;

    @Resource
    private JedisClusterDao jedisClusterDaoImpl;

    private final static String cookieName = "TT_TOKEN";


    @Override
    public EgoResult login(TbUser tbUser, HttpServletRequest request, HttpServletResponse response) {
        EgoResult egoResult = new EgoResult();
        TbUser user = tbUserDubboServiceImpl.selectByUser(tbUser);
        if (user != null) {
            egoResult.setStatus(200);
            // 当用户登录成功后把用户信息放入到redis中
            String key = UUID.randomUUID().toString();
            int expiredTime = 60 * 60 * 24 * 7;//7天
            jedisClusterDaoImpl.setExpire(key, expiredTime, JsonUtils.objectToJson(user));

            // 产生cookie
            CookieUtils.setCookie(request, response, cookieName, key, expiredTime, true);
        } else {
            egoResult.setMsg("用户名或密码错误！");
        }
        return egoResult;
    }

    @Override
    public EgoResult getTbUserInfoByToken(String token) {
        String userInfoJson = jedisClusterDaoImpl.get(token);
        EgoResult egoResult = new EgoResult();
        if (userInfoJson != null && !userInfoJson.equals("")) {
            TbUser tbUser = JsonUtils.jsonToPojo(userInfoJson, TbUser.class);
            if(tbUser != null) {
                tbUser.setPassword(null);
                egoResult.setStatus(200);
                egoResult.setMsg("OK");
                egoResult.setData(tbUser);
            }
        } else {
            egoResult.setMsg("获取失败");
        }

        return egoResult;
    }

    @Override
    public EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response) {
        Long index = jedisClusterDaoImpl.delete(token);
        CookieUtils.deleteCookie(request, response, cookieName);
        EgoResult egoResult = new EgoResult();
        if (index > 0) {
            egoResult.setStatus(200);
            egoResult.setMsg("OK");
        }
        return egoResult;
    }
}
