package com.ego.passport.service;

import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: yk
 * @Date: 2019/6/15 10:56
 */
public interface TbUserService {

    /**
     * 用户登录
     * @return
     */
    EgoResult login(TbUser tbUser, HttpServletRequest request, HttpServletResponse response);


    /**
     * 根据token（TT_TOKEN）查询用户信息
     * @param token
     * @return
     */
    EgoResult getTbUserInfoByToken(String token);


    /**
     * 用户退出
     * @param token
     * @param request
     * @param response
     * @return
     */
    EgoResult logout(String token, HttpServletRequest request, HttpServletResponse response);
}
