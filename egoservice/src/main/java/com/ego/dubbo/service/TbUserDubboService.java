package com.ego.dubbo.service;

import com.ego.pojo.TbUser;

/**
 * @Author: yk
 * @Date: 2019/6/15 10:51
 */
public interface TbUserDubboService {

    /**
     * 根据用户名和密码查询用户
     * @param tbUser
     * @return
     */
    TbUser selectByUser(TbUser tbUser);
}
