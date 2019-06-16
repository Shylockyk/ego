package com.ego.order.controller;

import com.ego.commons.pojo.EgoResult;
import com.ego.order.pojo.OrderParam;
import com.ego.order.service.TbOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/16 14:38
 */
@Controller
public class OrderController {

    @Resource
    private TbOrderService tbOrderServiceImpl;

    /**
     * 显示订单信息
     * @param model
     * @param request
     * @param ids
     * @return
     */
    @RequestMapping("order/order-cart.html")
    public String showCartOrder(Model model, HttpServletRequest request,@RequestParam("id") List<Long> ids) {
        model.addAttribute("cartList", tbOrderServiceImpl.showOrderCart(ids, request));
        return "order-cart";
    }

    @RequestMapping("order/create.html")
    private String createOrder(OrderParam param,HttpServletRequest request) {
        EgoResult egoResult = tbOrderServiceImpl.createOrder(param, request);
        if (egoResult.getStatus() == 200) {
            return "my-orders";
        } else {
            request.setAttribute("message","订单创建失败");
            return "error/exception";
        }
    }
}
