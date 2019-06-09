package com.ego.manager.service.impl;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.manager.service.TbContentCategoryService;
import com.ego.pojo.TbContentCategory;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: yk
 * @Date: 2019/6/9 11:07
 */
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService {

    @Reference
    private TbContentCategoryDubboService tbContentCategoryDubboServiceImpl;

    @Override
    public List<EasyUiTree> showCategory(long id) {
        List<TbContentCategory> tbContentCategories = tbContentCategoryDubboServiceImpl.selectByPid(id);
        List<EasyUiTree> easyUiTreeList = new ArrayList<>();
        for (TbContentCategory tbContentCategory : tbContentCategories) {
            EasyUiTree tree = new EasyUiTree();
            tree.setId(tbContentCategory.getId());
            tree.setText(tbContentCategory.getName());
            tree.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            easyUiTreeList.add(tree);
        }
        return easyUiTreeList;
    }

    @Override
    public EgoResult createCategory(TbContentCategory tbContentCategory) {
        EgoResult egoResult = new EgoResult();

        // 判断当前节点名称是否已经存在

        if (nameIsExists(tbContentCategory)) {
            egoResult.setData("内容类目名称已存在");
            return egoResult;
        }

        Date now = new Date();
        tbContentCategory.setCreated(now);
        tbContentCategory.setUpdated(now);
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setIsParent(false);
        long id = IDUtils.genItemId();
        tbContentCategory.setId(id);
        int index = tbContentCategoryDubboServiceImpl.insertContentCategory(tbContentCategory);
        if (index > 0) {
            TbContentCategory parent = new TbContentCategory();
            parent.setId(tbContentCategory.getParentId());
            parent.setIsParent(true);
            tbContentCategoryDubboServiceImpl.updateContentCategoryById(parent);
        }
        egoResult.setStatus(200);
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        egoResult.setData(map);
        return egoResult;
    }

    @Override
    public EgoResult updateCategory(TbContentCategory tbContentCategory) {

        EgoResult egoResult = new EgoResult();

        tbContentCategory.setParentId(tbContentCategoryDubboServiceImpl.selectContentCategoryById(tbContentCategory.getId()).getParentId());
        // 判断当前节点名称是否已经存在
        if (nameIsExists(tbContentCategory)) {
            egoResult.setData("内容类目名称已存在");
            return egoResult;
        }

        int index = tbContentCategoryDubboServiceImpl.updateContentCategoryById(tbContentCategory);
        if (index > 0) {
            egoResult.setStatus(200);
        }
        return egoResult;
    }

    @Override
    public boolean nameIsExists(TbContentCategory tbContentCategory) {
        List<TbContentCategory> children = tbContentCategoryDubboServiceImpl.selectByPid(tbContentCategory.getParentId());
        for (TbContentCategory child : children) {
            if (child.getName().equals(tbContentCategory.getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public EgoResult deleteCategory(TbContentCategory tbContentCategory) {

        EgoResult egoResult = new EgoResult();

        tbContentCategory.setStatus(0);
        int index = tbContentCategoryDubboServiceImpl.updateContentCategoryById(tbContentCategory);
        if (index > 0) {
            TbContentCategory curr = tbContentCategoryDubboServiceImpl.selectContentCategoryById(tbContentCategory.getId());
            List<TbContentCategory> tbContentCategories = tbContentCategoryDubboServiceImpl.selectByPid(curr.getParentId());
            if (tbContentCategories == null || tbContentCategories.size() == 0) {
                TbContentCategory parent = new TbContentCategory();
                parent.setId(curr.getParentId());
                parent.setIsParent(false);
                index += tbContentCategoryDubboServiceImpl.updateContentCategoryById(parent);
                if (index == 2) {
                    egoResult.setStatus(200);
                }
            } else {
                egoResult.setStatus(200);
            }
        }

        return egoResult;
    }
}
