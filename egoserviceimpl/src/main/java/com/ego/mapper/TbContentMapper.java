package com.ego.mapper;

import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentExample;
import org.springframework.stereotype.Repository;

/**
 * TbContentMapper继承基类
 */
@Repository
public interface TbContentMapper extends MyBatisBaseDao<TbContent, Long, TbContentExample> {
}