package com.ego.search.service.impl;

import com.ego.search.pojo.TbItemChild;
import com.ego.search.service.TbItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TbItemServiceImpl  implements TbItemService {

    @Resource
    private CloudSolrClient solrClient;

    @Override
    public Map<String, Object> selectByQuery(String query, int page, int rows) throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        // 设置分页条件
        solrQuery.setStart(rows * (page - 1));
        solrQuery.setRows(rows);

        // 设置条件
        solrQuery.setQuery("item_keywords:"+query);

        //设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<span style='color:red'>");
        solrQuery.setHighlightSimplePost("</span>");


        QueryResponse response = solrClient.query(solrQuery);
        //未高亮内容
        SolrDocumentList results = response.getResults();
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        List<TbItemChild> listChild = new ArrayList<>();

        for (SolrDocument result : results) {
            TbItemChild child = new TbItemChild();
            // id
            child.setId(Long.valueOf(result.get("id").toString()));
            List<String> list = highlighting.get(result.get("id")).get("item_title");
            // title
            if (list != null && list.size() != 0) {
                child.setTitle(list.get(0));
            } else {
                child.setTitle(result.get("item_title").toString());
            }
            // price
            child.setPrice((Long) result.get("item_price"));
            Object image = result.get("item_image");
            // images
            child.setImages(image == null || image.equals("")? new String[1]:image.toString().split(","));
            // sellpoint
            child.setSellPoint(result.get("item_sell_point").toString());

            listChild.add(child);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("itemList", listChild);
        resultMap.put("totalPages", results.getNumFound() % rows == 0 ? results.getNumFound() / rows : results.getNumFound() / rows + 1);

        return resultMap;
    }
}
