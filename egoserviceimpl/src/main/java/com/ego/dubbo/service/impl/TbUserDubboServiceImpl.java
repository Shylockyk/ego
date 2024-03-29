package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbUserDubboService;
import com.ego.mapper.TbUserMapper;
import com.ego.pojo.TbUser;
import com.ego.pojo.TbUserExample;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/15 10:52
 */
public class TbUserDubboServiceImpl implements TbUserDubboService {

    @Resource
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser selectByUser(TbUser tbUser) {
        TbUserExample example = new TbUserExample();
        example.createCriteria().andUsernameEqualTo(tbUser.getUsername()).andPasswordEqualTo(tbUser.getPassword());
        List<TbUser> list = tbUserMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
