package com.ego.mapper;

import com.ego.pojo.TbItemCat;
import org.springframework.stereotype.Repository;

/**
 * TbItemCatMapper继承基类
 */
@Repository
public interface TbItemCatMapper extends MyBatisBaseDao<TbItemCat, Long> {
}