package com.ego.mapper;

import com.ego.pojo.TbContentCategory;
import com.ego.pojo.TbContentCategoryExample;
import org.springframework.stereotype.Repository;

/**
 * TbContentCategoryMapper继承基类
 */
@Repository
public interface TbContentCategoryMapper extends MyBatisBaseDao<TbContentCategory, Long, TbContentCategoryExample> {
}