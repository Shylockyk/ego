package com.ego.manager.controller;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manager.service.TbItemParamService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: yk
 * @Date: 2019/6/8 14:48
 */

@Controller
public class TbItemParamController {

    @Resource
    private TbItemParamService tbItemParamServiceImpl;

    /**
     * 规格参数的分页显示
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("item/param/list")
    @ResponseBody
    public EasyUIDataGrid list(int page, int rows) {
        return tbItemParamServiceImpl.show(page, rows);
    }

    @RequestMapping("item/param/delete")
    @ResponseBody
    public EgoResult delete(String ids) {
        EgoResult egoResult = new EgoResult();
        try {
            int index = tbItemParamServiceImpl.delete(ids);
            if (index == 1) {
                egoResult.setStatus(200);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            if(e instanceof InvocationTargetException)
                egoResult.setData(((InvocationTargetException)e).getTargetException().getMessage());
        }
        return egoResult;
    }
}
