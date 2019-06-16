package com.ego.passport.controller;

import com.ego.commons.pojo.EgoResult;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户控制层
 *
 * @Author: yk
 * @Date: 2019/6/15 10:03
 */

@Controller
public class TbUserController {

    @Resource
    private TbUserService tbUserServiceImpl;

    /**
     * 显示登录页面
     * @return
     */
    @RequestMapping("user/showLogin")
    public String showLogin(@RequestHeader(value = "Referer", defaultValue = "") String redirectUrl, Model model,
                            Integer isCart, String interurl, HttpServletRequest request) {
        if (isCart != null && isCart == 1) {
            redirectUrl = redirectUrl.substring(redirectUrl.lastIndexOf("/") + 1);
            redirectUrl = "http://localhost:8085/cart/add/" + redirectUrl + "?num="+request.getParameter("num");
        }
        model.addAttribute("redirect", redirectUrl);
        /* 如果请求头没有referer使用下面的代码
        if (redirectUrl.equals("")) {
            model.addAttribute("redirect", interurl);
        } else {
            model.addAttribute("redirect", redirectUrl);
        } */
        return "login";
    }

    /**
     * 登录请求
     * @param tbUser
     * @return
     */
    @RequestMapping("user/login")
    @ResponseBody
    public EgoResult login(TbUser tbUser, HttpServletResponse response, HttpServletRequest request) {
        return tbUserServiceImpl.login(tbUser, request, response);
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @param callback
     * @return
     */
    @RequestMapping("user/token/{token}")
    @ResponseBody
    public Object getTbUserInfo(@PathVariable("token") String token, String callback) {
        EgoResult egoResult = tbUserServiceImpl.getTbUserInfoByToken(token);
        if (callback != null && !callback.equals("")) {
            return new JSONPObject(callback, egoResult);
        }
        return egoResult;
    }

    /**
     * 退出
     * @param token
     * @param callback
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("user/logout/{token}")
    @ResponseBody
    public Object logout(@PathVariable("token") String token,String callback, HttpServletRequest request, HttpServletResponse response) {
        EgoResult egoResult = tbUserServiceImpl.logout(token, request, response);
        if (callback != null && !callback.equals("")) {
            return new JSONPObject(callback, egoResult);
        }
        return egoResult;
    }
}
