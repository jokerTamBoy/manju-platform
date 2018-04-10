/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.manjushira.controller;

import com.manjushira.pojo.admin.po.Area;
import com.manjushira.service.AreaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.manjushira.common.web.BaseController;

import javax.annotation.Resource;

/**
 * 区域Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/area")
public class AreaController extends BaseController {

	@Resource
	private AreaService areaService;

	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = {"list", ""})
	public String list(Area area, Model model) {
		model.addAttribute("list", areaService.selectRoles(null));
		return "modules/sys/areaList";
	}

	@RequiresPermissions("sys:area:view")
	@RequestMapping(value = "form")
	public String form(Area area, Model model) {
		model.addAttribute("area", area);
		return "modules/sys/areaForm";
	}


	@RequiresPermissions("sys:area:edit")
	@RequestMapping(value = "delete")
	public String delete(Area area, RedirectAttributes redirectAttributes) {
		return "redirect:" + adminPath + "/sys/area/";
	}

}
