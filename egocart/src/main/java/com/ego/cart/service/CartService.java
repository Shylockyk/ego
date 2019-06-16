package com.ego.cart.service;

import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemChild;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/16 10:23
 */
public interface CartService {

    /**
     * 加入购物车
     * @param id
     * @param num
     */
    void addCart(long id, int num, HttpServletRequest request);


    /**
     * 根据用户查询购物车
     * @param request
     * @return
     */
    List<TbItemChild> getCart(HttpServletRequest request);


    /**
     * 修改购物车数量
     * @param request
     */
    EgoResult updateCartNum(long id, int num, HttpServletRequest request);

    /**
     * 删除商品
     * @param id
     * @param request
     */
    EgoResult deleteCartItem(long id, HttpServletRequest request);
}
