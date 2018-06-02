/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.manjushirwa.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manjushirwa.common.web.BaseController;
import com.manjushirwa.pojo.admin.po.Menu;
import com.manjushirwa.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 菜单Controller
 * @author ThinkGem
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {
    private static final Logger _logger = LoggerFactory.getLogger(BaseController.class);

    @Resource
    private MenuService menuService;

    /**
     * 所有菜单
     */
    @RequestMapping(value = "/management")
    public String management(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Menu> menuList = menuService.listAll();
        model.addAttribute("menuList", menuList);
        return "menu/menu_management";
    }

    /**
     * 保存菜单
     */
    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, HttpServletResponse response, Model model, Menu menu) {
        if(StringUtils.isNotBlank(menu.getId())){
            menu = menuService.getById(menu.getId());
        }
        model.addAttribute("menu",menu);
        return "menu/menu_detail";
    }

    /**
     * 保存菜单
     */
    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response, Model model, Menu menu) {
        menuService.insertMenu(menu);
        model.addAttribute("menu",menu);
        return "forward:menu/edit";
    }

    /**
     * 菜单树
    */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public String tree(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Menu> menuList = menuService.listAll();
        JSONArray menuTree = new JSONArray();
        buildMenuTree(menuTree, menuList, "", true);
        return menuTree.toJSONString();
    }

    private void buildMenuTree(JSONArray menuTree, List<Menu> sourcelist, String parentId, boolean cascade){
        for (int i = 0; i < sourcelist.size(); i++) {
            Menu e = sourcelist.get(i);
            if (e.getParentId() != null
                    && e.getParentId().equals(parentId)) {
                JSONObject menu = new JSONObject();
                menu.put("text", e.getName());
                if (cascade) {
                    // 判断是否还有子节点, 有则继续获取子节点
                    for (int j = 0; j < sourcelist.size(); j++) {
                        Menu child = sourcelist.get(j);
                        if (child.getParentId() != null
                                && child.getParentId().equals(e.getId())) {
                            JSONArray childrenList = new JSONArray();
                            buildMenuTree(childrenList, sourcelist, e.getId(), true);
                            menu.put("nodes", childrenList);
                            break;
                        }
                    }
                }
                menuTree.add(menu);
            }
        }
    }
}
