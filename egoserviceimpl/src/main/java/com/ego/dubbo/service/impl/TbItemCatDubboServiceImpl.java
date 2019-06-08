package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.mapper.TbItemCatMapper;
import com.ego.pojo.TbItemCat;

/**
 * 商品类名业务层
 *
 * @Author: yk
 * @Date: 2019/6/8 10:04
 */
public class TbItemCatDubboServiceImpl implements TbItemCatDubboService{
	@Resource
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public List<TbItemCat> show(long pid) {
		return tbItemCatMapper.selectByParentId(pid);
	}

}
