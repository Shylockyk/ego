package com.ego.mapper;

import com.ego.pojo.TbOrder;
import org.springframework.stereotype.Repository;

/**
 * TbOrderMapper继承基类
 */
@Repository
public interface TbOrderMapper extends MyBatisBaseDao<TbOrder, String> {
}