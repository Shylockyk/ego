package com.ego.dubbo.service;

import com.ego.pojo.TbContent;

import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/9 13:04
 */
public interface TbContentDubboService {


    /**
     * 分页查询某内容类目的所有内容
     * @param categoryId
     * @return
     */
    List<TbContent> selectContentByPage(long categoryId);


    /**
     * 新增内容
     * @param tbContent
     * @return
     */
    int insertContent(TbContent tbContent);

    /**
     * 查询出最新的前n个
     * @param count
     * @return
     */
    List<TbContent> selectByCount(int count, boolean isSort);
}
