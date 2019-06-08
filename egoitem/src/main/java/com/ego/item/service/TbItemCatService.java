package com.ego.item.service;

import com.ego.item.pojo.PortalMenu;

/**
 * @Author: yk
 * @Date: 2019/6/8 21:11
 */
public interface TbItemCatService {

    /**
     * 查询出所有分类类目并转换为特定类型
     * @return
     */
    PortalMenu showCatMenu();
}
