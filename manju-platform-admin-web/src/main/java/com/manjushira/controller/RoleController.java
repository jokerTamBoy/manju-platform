/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.manjushira.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.manjushira.pojo.admin.po.Role;
import com.manjushira.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.manjushira.common.web.BaseController;

import javax.annotation.Resource;

/**
 * 角色Controller
 *
 * @author ThinkGem
 * @version 2013-12-05
 */
@Controller
@RequestMapping(value = "${adminPath}/role")
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;

    /**
     *
     * @param model
     * @param page
     * @param current
     * @param size
     * @return
     */
    @RequestMapping({"list"})
    public String role(Model model, Page<Role> page, @RequestParam(required = false) Integer current, @RequestParam(required = false)  Integer size) {
        page.setCurrent(current==null?1:current);
        page.setSize(size==null?3:size);
        page = roleService.selectRoles(page, 5);
        for (Role r : page.getRecords()
                ) {
            System.out.println("------->:" + r.getName());
        }
        System.out.println(page.getTotal());
        model.addAttribute("now", page.getCurrent());
        model.addAttribute("page", page);
        model.addAttribute("roles", page.getRecords());
        return "role";
    }


}
