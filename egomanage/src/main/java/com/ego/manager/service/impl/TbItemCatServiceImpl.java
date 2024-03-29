package com.ego.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ego.manager.service.TbItemCatService;
import com.ego.pojo.TbItemCat;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.dubbo.service.TbItemCatDubboService;

@Service
public class TbItemCatServiceImpl implements TbItemCatService {

    @Reference
    private TbItemCatDubboService tbItemCatDubboServiceImpl;

    @Override
    public List<EasyUiTree> show(long pid) {
        List<TbItemCat> list = tbItemCatDubboServiceImpl.show(pid);
        List<EasyUiTree> listTree = new ArrayList<>();
        for (TbItemCat cat : list) {
            EasyUiTree tree = new EasyUiTree();
            tree.setId(cat.getId());
            tree.setText(cat.getName());
            tree.setState(cat.getIsParent() ? "closed" : "open");
            listTree.add(tree);
        }
        return listTree;
    }
}
