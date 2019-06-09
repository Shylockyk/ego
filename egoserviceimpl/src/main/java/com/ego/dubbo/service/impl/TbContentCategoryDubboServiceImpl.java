package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.mapper.TbContentCategoryMapper;
import com.ego.pojo.TbContentCategory;
import com.ego.pojo.TbContentCategoryExample;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/9 11:03
 */

public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService {

    @Resource
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TbContentCategory> selectByPid(long pid) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        example.createCriteria().andParentIdEqualTo(pid).andStatusEqualTo(1);
        return tbContentCategoryMapper.selectByExample(example);
    }

    @Override
    public int insertContentCategory(TbContentCategory tbContentCategory) {
        return tbContentCategoryMapper.insert(tbContentCategory);
    }

    @Override
    public int updateContentCategoryById(TbContentCategory tbContentCategory) {
        return tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
    }

    @Override
    public TbContentCategory selectContentCategoryById(long id) {
        return tbContentCategoryMapper.selectByPrimaryKey(id);
    }


}
