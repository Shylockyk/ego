package com.ego.dubbo.service.impl;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemDubboService;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @Author: yk
 * @Date: 2019/6/4 21:51
 */
public class TbItemDubboServiceImplTest {

    @Resource
    private TbItemDubboService tbItemDubboServiceImpl;

    @Test
    public void show() {
        EasyUIDataGrid dataGrid = tbItemDubboServiceImpl.show(0, 10);
        System.out.println(dataGrid.getRows());
    }
}