package com.ego.manager.controller;

import java.util.List;

import javax.annotation.Resource;

import com.ego.manager.service.TbItemCatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUiTree;

@Controller
public class TbItemCatController {
	@Resource
	private TbItemCatService tbItemCatServiceImpl;
	/**
	 * 显示商品类目
	 * @param id
	 * @return
	 */
	@RequestMapping("item/cat/list")
	@ResponseBody
	public List<EasyUiTree> showCat(@RequestParam(defaultValue="0") long id){
		return tbItemCatServiceImpl.show(id);
	}
}
