package com.ego.dubbo.service;

import com.ego.pojo.TbItemDesc;

/**
 * 商品描述表（tb_item_desc）
 *
 * @Author: yk
 * @Date: 2019/6/8 10:00
 */
public interface TbItemDescDubboService {

    /**
     * 描述新增
     * @param tbItemDesc
     * @return
     */
    int insertDesc(TbItemDesc tbItemDesc);
}
