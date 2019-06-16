package com.ego.cart.controller;

import com.ego.cart.service.CartService;
import com.ego.commons.pojo.EgoResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yk
 * @Date: 2019/6/15 22:09
 */
@Controller
public class CartController {

    @Resource
    private CartService cartServiceImpl;

    /**
     * 添加购物车
     * @param id
     * @param num
     * @return
     */
    @RequestMapping("cart/add/{id}.html")
    public String addCart(@PathVariable long id, int num, HttpServletRequest request) {
        cartServiceImpl.addCart(id, num, request);
        return "cartSuccess";
    }


    /**
     * 显示购物车
     * @return
     */
    @RequestMapping("cart/cart.html")
    public String showCart(Model model,HttpServletRequest request) {
        model.addAttribute("cartList", cartServiceImpl.getCart(request));
        return "cart";
    }

    /**
     * 修改数量
     * @param id
     * @param num
     * @param request
     * @return
     */
    @RequestMapping("cart/update/num/{id}/{num}.action")
    @ResponseBody
    public EgoResult updateCart(@PathVariable("id") long id, @PathVariable("num") int num, HttpServletRequest request) {
        return cartServiceImpl.updateCartNum(id, num, request);
    }


    /**
     * 删除一个条目
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("cart/delete/{id}.action")
    @ResponseBody
    public EgoResult deleteCartItem(@PathVariable long id, HttpServletRequest request) {
        return cartServiceImpl.deleteCartItem(id, request);
    }
}
