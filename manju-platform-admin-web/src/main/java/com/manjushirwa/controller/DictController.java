package com.manjushirwa.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.manjushirwa.common.json.JsonUtils;
import com.manjushirwa.dialect.StaticExpression;
import com.manjushirwa.pojo.admin.po.Dict;
import com.manjushirwa.pojo.admin.po.User;
import com.manjushirwa.service.DictService;
import com.manjushirwa.service.impl.DictServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ciaoyen on 18/03/2018.
 */
@Controller
public class DictController {

    private static final Logger _logger = LoggerFactory.getLogger(DictController.class);

    @Autowired
    private DictService dictService;


    /**
     * 字典页面
     * @return
     */
    @RequestMapping({"/dict/dictionary"})
    public String dictionaryPage() {
        return "dictionary/dictionary";
    }

    @RequestMapping({"/dict/qryDictionary"})
    @ResponseBody
    public String qryDictionary(Dict dict, Page page) {
        return JsonUtils.toDataTableJson(dictService.selectPage(page,dict));
    }

    // 根据类型/value值翻译 label
    @RequestMapping({"/dict/selectDictTranslate"})
    @ResponseBody
    public String selectDictTranslate(Dict dict) {
        return dictService.selectDictTranslate(dict);
    }

    // 根据类型获取
    @RequestMapping({"/dict/selectDictTranslateList"})
    @ResponseBody
    public String selectDictTranslates(Dict dict) {
        return JsonUtils.objectToJson(dictService.selectDictTranslates(dict));
    }



    /**
     *
     * 字典添加页面
     * @param model
     * @return
     */
    @RequestMapping({"/dictionary/page"})
    public String insertDictPage(Model model,Dict dict) {
       List<Dict> dictList= dictService.selectDicts(null);
        return "insert/insertDictionary";
    }

    /**
     *
     * 新增数据
     * @param model
     * @return
     */
    @RequestMapping(value="/dictionary/insert",method = RequestMethod.POST)
    public String insert(@ModelAttribute(value = "dict") Dict dict, Model model) {

        if(StringUtils.isBlank(dict.getId())){
            dict.setCreateBy(new User("管理员"));
            dict.setCreateDate(new Date());
            dict.setUpdateBy(new User("管理员"));
            dict.setUpdateDate(new Date());
            dict.setDelFlag("0");
        }else{
            dict.setUpdateBy(new User("管理员"));
            dict.setUpdateDate(new Date());
        }

        dictService.insertDict(dict);

        return "dictionary";
    }





}
