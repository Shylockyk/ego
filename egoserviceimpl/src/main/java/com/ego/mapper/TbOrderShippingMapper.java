package com.ego.mapper;

import com.ego.pojo.TbOrderShipping;
import com.ego.pojo.TbOrderShippingExample;
import org.springframework.stereotype.Repository;

/**
 * TbOrderShippingMapper继承基类
 */
@Repository
public interface TbOrderShippingMapper extends MyBatisBaseDao<TbOrderShipping, String, TbOrderShippingExample> {
}