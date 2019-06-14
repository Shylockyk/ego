package com.ego.manager.controller;


import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manager.service.TbItemService;
import com.ego.pojo.TbItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;

/**
 * 商品控制器
 *
 * @Author: yk
 * @Date: 2019/6/5 21:26
 */

@Controller
public class TbItemController {

    @Resource
    private TbItemService tbItemServiceImpl;




    /**
     * 分页显示商品
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("item/list")
    @ResponseBody
    public EasyUIDataGrid list(int page, int rows) {
        return tbItemServiceImpl.show(page, rows);
    }


    @RequestMapping("rest/page/item-edit")
    public String showItemEdit() {
        return "item-edit";
    }

    /**
     * 商品删除
     * @param ids
     * @return
     */
    @RequestMapping("rest/item/delete")
    @ResponseBody
    public EgoResult delete(String ids) {
        EgoResult egoResult = new EgoResult();
        int result = tbItemServiceImpl.update(ids, (byte) 3);
        if(result == 1)
            egoResult.setStatus(200);
        return egoResult;
    }

    /**
     * 商品下架
     * @param ids
     * @return
     */
    @RequestMapping("rest/item/instock")
    @ResponseBody
    public EgoResult instock(String ids) {
        EgoResult egoResult = new EgoResult();
        int result = tbItemServiceImpl.update(ids, (byte) 2);
        if(result == 1)
            egoResult.setStatus(200);
        return egoResult;
    }

    /**
     * 商品上架
     * @param ids
     * @return
     */
    @RequestMapping("rest/item/reshelf")
    @ResponseBody
    public EgoResult reshelf(String ids) {
        EgoResult egoResult = new EgoResult();
        int result = tbItemServiceImpl.update(ids, (byte) 1);
        if(result == 1)
            egoResult.setStatus(200);
        return egoResult;
    }

    /**
     * 商品新增
     * @param tbItem
     * @param desc
     * @return
     */
    @RequestMapping("item/save")
    @ResponseBody
    public EgoResult insert(TbItem tbItem, String desc, String itemParams) {
        int index = 0;
        EgoResult egoResult = new EgoResult();
        try {
            index = tbItemServiceImpl.save(tbItem, desc, itemParams);
//            System.out.println(index);
            if (index == 1) {
                egoResult.setStatus(200);
            }
        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
            if(e instanceof InvocationTargetException)
                egoResult.setData(((InvocationTargetException)e).getTargetException().getMessage());
        }

        return egoResult;
    }
}
