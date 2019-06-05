package com.ego.mapper;

import com.ego.pojo.TbItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    TbItem selectByPrimaryKey(Long id);

    List<TbItem> selectAll();

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);
}