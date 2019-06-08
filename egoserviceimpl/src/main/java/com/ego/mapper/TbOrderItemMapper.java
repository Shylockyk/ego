package com.ego.mapper;

import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderItemExample;
import org.springframework.stereotype.Repository;

/**
 * TbOrderItemMapper继承基类
 */
@Repository
public interface TbOrderItemMapper extends MyBatisBaseDao<TbOrderItem, String, TbOrderItemExample> {
}