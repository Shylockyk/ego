package com.ego.mapper;

import com.ego.pojo.TbOrder;
import com.ego.pojo.TbOrderExample;
import org.springframework.stereotype.Repository;

/**
 * TbOrderMapper继承基类
 */
@Repository
public interface TbOrderMapper extends MyBatisBaseDao<TbOrder, String, TbOrderExample> {
}