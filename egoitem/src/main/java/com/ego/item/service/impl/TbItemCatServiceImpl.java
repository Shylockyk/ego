package com.ego.item.service.impl;

import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.item.pojo.PortalMenu;
import com.ego.item.pojo.PortalMenuNode;
import com.ego.item.service.TbItemCatService;
import com.ego.pojo.TbItemCat;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yk
 * @Date: 2019/6/8 21:12
 */
@Service
public class TbItemCatServiceImpl implements TbItemCatService {

    @Reference
    private TbItemCatDubboService tbItemCatDubboServiceImpl;

    @Override
    public PortalMenu showCatMenu() {
        //查询出所有一级菜单
        List<TbItemCat> tbItemCats = tbItemCatDubboServiceImpl.show(0);
        PortalMenu portalMenu = new PortalMenu();
        portalMenu.setData(selectAllMenu(tbItemCats));
        return portalMenu;
    }


    /**
     * 最终返回所有查询到的结果
     * 如果是父菜单，则是递归添加子菜单PortalMenuNode对象
     * 如果是最后一层子菜单，直接循环添加字符串
     *
     * @param tbItemCats
     * @return
     */
    private List<Object> selectAllMenu(List<TbItemCat> tbItemCats) {
        List<Object> portalMenuNodeList = new ArrayList<>();
        for (TbItemCat tbItemCat : tbItemCats) {
            if (tbItemCat.getIsParent()) {
                PortalMenuNode portalMenuNode = new PortalMenuNode();
                portalMenuNode.setU("/products/" + tbItemCat.getId() + ".html");
                portalMenuNode.setN("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
                portalMenuNode.setI(selectAllMenu(tbItemCatDubboServiceImpl.show(tbItemCat.getId())));

                portalMenuNodeList.add(portalMenuNode);
            } else {
                portalMenuNodeList.add("/products/"+tbItemCat.getId()+".html|" + tbItemCat.getName());
            }
        }

        return portalMenuNodeList;
    }
}
