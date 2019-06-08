package com.ego.mapper;

import com.ego.pojo.TbItemParamItem;
import com.ego.pojo.TbItemParamItemExample;
import org.springframework.stereotype.Repository;

/**
 * TbItemParamItemMapper继承基类
 */
@Repository
public interface TbItemParamItemMapper extends MyBatisBaseDao<TbItemParamItem, Long, TbItemParamItemExample> {
}