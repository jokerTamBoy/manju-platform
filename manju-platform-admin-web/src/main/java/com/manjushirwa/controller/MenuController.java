/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.manjushirwa.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manjushirwa.common.web.BaseController;
import com.manjushirwa.pojo.admin.po.Menu;
import com.manjushirwa.pojo.admin.po.User;
import com.manjushirwa.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 菜单Controller
 *
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
        return "menu/menu_management";
    }

    /**
     * 所有菜单
     */
    @RequestMapping(value = "/list")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Menu> menuList = menuService.listAll();
        model.addAttribute("menuList", menuList);
        return "menu/menu_list";
    }

    /**
     * 编辑菜单
     */
    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, HttpServletResponse response, Model model, Menu menu) {
        if (StringUtils.isNotBlank(menu.getId())) {
            menu = menuService.getById(menu.getId());
        }
        model.addAttribute("menu", menu);
        return "menu/menu_detail";
    }

    /**
     * 保存菜单
     */
    @RequestMapping(value = "/submit")
    @ResponseBody
    public String save(HttpServletRequest request, HttpServletResponse response, Model model,
                       @RequestBody Menu menu) {
        //Menu menu = new Menu();
        JSONObject resultJson = new JSONObject();
        resultJson.put("result", true);
        String resultId = "";
        try {
            User logon = (User) request.getSession().getAttribute("user");
            /*判断执行保存或更新*/
            if (StringUtils.isNotBlank(menu.getId())) {
                Menu oldMenu = menuService.getById(menu.getId());
                menu.setCreateBy(oldMenu.getCreateBy());
                menu.setCreateDate(oldMenu.getCreateDate());
                menu.setUpdateBy(logon);
                menu.setUpdateDate(new Date());
                menuService.update(menu);
                resultId = menu.getId();
            } else {
                menu.setCreateBy(logon);
                menu.setCreateDate(new Date());
                menu.setUpdateBy(logon);
                menu.setUpdateDate(new Date());
                menuService.insert(menu);
                resultId = menu.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultJson.put("msg", e.getMessage());
        }
        resultJson.put("id", resultId);
        return resultJson.toJSONString();
    }

    /**
     * 菜单树
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public String tree(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Menu> menuList = menuService.listAll();
        JSONArray menuTree = new JSONArray();
        //封装树状返回数据 json
        buildMenuTree(menuTree, menuList, "", true);
        return menuTree.toJSONString();
    }

    private void buildMenuTree(JSONArray menuTree, List<Menu> sourcelist, String parentId, boolean cascade) {
        for (int i = 0; i < sourcelist.size(); i++) {
            Menu e = sourcelist.get(i);
            if (e.getParentId() != null
                    && e.getParentId().equals(parentId)) {
                JSONObject menu = new JSONObject();
                menu.put("text", e.getName());
                /*加入点击事件*/
                StringBuffer hrefBuff = new StringBuffer();
                hrefBuff.append("javaScript:manju.OpenPageLoad2('menu/edit',{id:'");
                hrefBuff.append(e.getId());
                hrefBuff.append("'}, 'menuBox')");
                menu.put("href", hrefBuff.toString());
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
