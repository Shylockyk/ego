package com.ego.mapper;

import com.ego.pojo.TbOrderItem;
import org.springframework.stereotype.Repository;

/**
 * TbOrderItemMapper继承基类
 */
@Repository
public interface TbOrderItemMapper extends MyBatisBaseDao<TbOrderItem, String> {
}