package com.ego.mapper;

import com.ego.pojo.TbContentCategory;
import org.springframework.stereotype.Repository;

/**
 * TbContentCategoryMapper继承基类
 */
@Repository
public interface TbContentCategoryMapper extends MyBatisBaseDao<TbContentCategory, Long> {
}