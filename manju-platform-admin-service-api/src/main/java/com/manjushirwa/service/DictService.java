package com.manjushirwa.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.manjushirwa.pojo.admin.po.Dict;

import java.util.List;

/**
 * 后台字典管理
 */


public interface DictService {

    /**
     * 通过ID查找角色
     * @param id
     * @return
     */
    public Dict findById(String id);


    public List<Dict> selectDicts(Dict dict);

    /**
     * 分页查询
     * @param page
     * @param dict
     * @return Page<Dict>
     */
    public Page<Dict> selectPage(Page<Dict> page, Dict dict);


    public void insertDict(Dict dict);

    /**
     * 修改
     * @param dict
     */
    public void updateDict(Dict dict);

    /**
     * 删除
     * @param id
     */
    public void deleteDict(String id);


    /**
     * 字典翻译
     * @param dict
     * @return
     */
    public String  selectDictTranslate(Dict dict);
    public String  selectDictTranslate(String type,String value);


    /**
     * 字典列表
     * @param dict
     * @return
     */
    public List<Dict> selectDictTranslates(Dict dict);



}
