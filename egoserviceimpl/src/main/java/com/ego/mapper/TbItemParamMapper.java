package com.ego.mapper;

import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamExample;
import org.springframework.stereotype.Repository;

/**
 * TbItemParamMapper继承基类
 */
@Repository
public interface TbItemParamMapper extends MyBatisBaseDao<TbItemParam, Long, TbItemParamExample> {
}