package com.ego.search.service;

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
}
