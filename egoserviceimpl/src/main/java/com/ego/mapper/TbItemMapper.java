package com.ego.mapper;

import com.ego.pojo.TbItem;
import org.springframework.stereotype.Repository;

/**
 * TbItemMapper继承基类
 */
@Repository
public interface TbItemMapper extends MyBatisBaseDao<TbItem, Long> {
}