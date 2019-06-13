package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.pojo.TbItemDesc;

import javax.annotation.Resource;

/**
 * 商品描述业务层
 *
 * @Author: yk
 * @Date: 2019/6/8 10:04
 */
public class TbItemDescDubboServiceImpl implements TbItemDescDubboService {

    @Resource
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public int insertDesc(TbItemDesc tbItemDesc) {
        return tbItemDescMapper.insert(tbItemDesc);
    }

    @Override
    public TbItemDesc selectByItemId(long itemId) {
        return tbItemDescMapper.selectByPrimaryKey(itemId);
    }
}
