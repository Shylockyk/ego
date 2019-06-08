package com.ego.mapper;

import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemDescExample;
import org.springframework.stereotype.Repository;

/**
 * TbItemDescMapper继承基类
 */
@Repository
public interface TbItemDescMapper extends MyBatisBaseDao<TbItemDesc, Long, TbItemDescExample> {
}