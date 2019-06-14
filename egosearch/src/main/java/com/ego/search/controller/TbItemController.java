package com.ego.search.controller;

import com.ego.search.service.TbItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: yk
 * @Date: 2019/6/13 21:50
 */

@Controller
public class TbItemController {

    @Resource
    private TbItemService tbItemServiceImpl;


    @RequestMapping("search.html")
    private String search(Model model ,String q, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "12") int rows) {
//        System.out.println(q);
        try {
            q = new String(q.getBytes(StandardCharsets.ISO_8859_1));
//            System.out.println(q);
            Map<String, Object> map = tbItemServiceImpl.selectByQuery(q, page, rows);
            model.addAttribute("query", q);
            model.addAttribute("itemList", map.get("itemList"));
            model.addAttribute("totalPages", map.get("totalPages"));
            model.addAttribute("page", page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "search";
    }

    @RequestMapping("solr/add")
    @ResponseBody
    public int addSolrTbItem(@RequestBody Map<String, Object> map) {
        try {
            return tbItemServiceImpl.addSolrTbItem((LinkedHashMap) map.get("tbItem"), map.get("desc").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
