package com.ego.mapper;

import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemCatExample;
import org.springframework.stereotype.Repository;

/**
 * TbItemCatMapper继承基类
 */
@Repository
public interface TbItemCatMapper extends MyBatisBaseDao<TbItemCat, Long, TbItemCatExample> {
}