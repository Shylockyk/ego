package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbContentDubboService;
import com.ego.mapper.TbContentMapper;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/9 13:06
 */
public class TbContentDubboServiceImpl implements TbContentDubboService {

    @Resource
    private TbContentMapper tbContentMapper;

    @Override
    public List<TbContent> selectContentByPage(long categoryId) {
        TbContentExample example = new TbContentExample();
        if (categoryId != 0) {
            example.createCriteria().andCategoryIdEqualTo(categoryId);
        }
        return tbContentMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public int insertContent(TbContent tbContent) {
        return tbContentMapper.insertSelective(tbContent);
    }

    @Override
    public List<TbContent> selectByCount(int count, boolean isSort) {
        TbContentExample example = new TbContentExample();
        // 排序
        if (isSort) {
            example.setOrderByClause("updated desc");
        }
        if (count != 0) {
            PageHelper.startPage(1, count);
            List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(example);
            PageInfo<TbContent> pageInfo = new PageInfo<>(tbContents);
            return pageInfo.getList();
        } else {
            return tbContentMapper.selectByExampleWithBLOBs(example);
        }
    }
}
