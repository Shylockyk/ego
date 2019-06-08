package com.ego.mapper;

import com.ego.pojo.TbItemCat;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TbItemCatMapper继承基类
 */
@Repository
public interface TbItemCatMapper extends MyBatisBaseDao<TbItemCat, Long> {

    List<TbItemCat> selectByParentId(Long parenId);
}