package com.manjushirwa.controller;


import com.manjushirwa.pojo.admin.po.Dict;
import com.manjushirwa.service.DictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author : Ciaoyen
 */

@Controller
public class IndexController {

    private static final Logger _logger = LoggerFactory.getLogger(IndexController.class);


    @Resource
    private DictService dictService;


    /**
     * 平台入口
     *
     * @param model
     * @return
     */
    @RequestMapping({"/", "/index"})
    public String index(HttpSession session, Model model) {
        return "index2";
    }

    @RequestMapping({"/index2"})
    public String index2(HttpSession session, Model model) {
        return "index2";
    }


    /**
     * 主页
     *
     * @param model
     * @return
     */
    @RequestMapping({"/page"})
    public String page(Model model) {
        return "page";
    }

    /**
     * 权限页面
     *
     * @param model
     * @return
     */
    @RequestMapping({"/rule"})
    public String rule(Model model) {
        return "rule";
    }


    /**
     * 日志页面
     *
     * @param model
     * @return
     */
    @RequestMapping({"/logs"})
    public String logs(Model model) {
        return "logs";
    }

    /**
     * 字典页面
     *
     * @param model
     * @return
     */
    @RequestMapping({"/dictionary"})
    public String dictionary(Model model) {
        List<Dict> dicts = dictService.selectDicts(null);
        model.addAttribute("dicts",dicts);
        return "dictionary";
    }

    /**
     * 菜单页面
     *
     * @param model
     * @return
     */
    @RequestMapping({"/menu"})
    public String menu(Model model) {
        return "menu";
    }

    /**
     * 文件页面
     *
     * @param model
     * @return
     */
    @RequestMapping({"/file"})
    public String file(Model model) {
        return "file";
    }

    /**
     * 接口页面
     *
     * @param model
     * @return
     */
    @RequestMapping({"/interface"})
    public String interfaces(Model model) {
        return "interface";
    }

    /**
     * 知识库页面
     *
     * @param model
     * @return
     */
    @RequestMapping({"/information"})
    public String information(Model model) {
        return "information";
    }

}
