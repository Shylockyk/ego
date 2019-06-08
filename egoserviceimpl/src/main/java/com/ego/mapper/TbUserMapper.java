package com.ego.mapper;

import com.ego.pojo.TbUser;
import com.ego.pojo.TbUserExample;
import org.springframework.stereotype.Repository;

/**
 * TbUserMapper继承基类
 */
@Repository
public interface TbUserMapper extends MyBatisBaseDao<TbUser, Long, TbUserExample> {
}