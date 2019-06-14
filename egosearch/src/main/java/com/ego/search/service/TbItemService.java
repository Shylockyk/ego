package com.ego.search.service;

import com.ego.pojo.TbItem;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.Map;

public interface TbItemService {

    /**
     * 分页查询
     * @param query
     * @param page
     * @param rows
     * @return
     */
    Map<String, Object> selectByQuery(String query, int page, int rows) throws IOException, SolrServerException;


    /**
     * 新增商品到solr索引库中
     * @param map
     * @param desc
     * @return
     */
    int addSolrTbItem(Map<String, Object> map, String desc) throws IOException, SolrServerException;
}
