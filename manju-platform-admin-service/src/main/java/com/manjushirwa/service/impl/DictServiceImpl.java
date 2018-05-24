package com.manjushirwa.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.manjushirwa.mapper.admin.DictMapper;
import com.manjushirwa.pojo.admin.po.Dict;
import com.manjushirwa.service.DictService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台字典管理
 */

@Service
public class DictServiceImpl implements DictService{

    @Resource
    private DictMapper dictMapper;

    /**
     * 通过ID查找字典
     * @param id
     * @return
     */
    public Dict findById(String id) {
        return dictMapper.selectById(id);
    }


	public List<Dict> selectDicts(Dict dict) {
        EntityWrapper<Dict> entityWrapper = new EntityWrapper();

        if (dict != null) {
            if (!StringUtils.isBlank(dict.getValue()) && !StringUtils.isBlank(dict.getType())){

            }
        }

        return dictMapper.selectList(null);
	}

    @Cacheable(key = "#dict.type + '+' + dict.value")
	public String selectDictTranslate (Dict dict){
        String result = null;
        if (dict != null) {

            EntityWrapper<Dict> entityWrapper = new EntityWrapper();
            if (!StringUtils.isBlank(dict.getValue()) && !StringUtils.isBlank(dict.getType())){
                entityWrapper.setSqlSelect(" label ");
                entityWrapper.where("type = {0} ", dict.getType()).and("value = {0}", dict.getValue());
                List<Dict> list =  dictMapper.selectList(entityWrapper);
                if (!list.isEmpty()){
                    result = list.get(0).getLabel();
                }

            }
        }
        return result;
    }


    @Cacheable(key = "#dict.type")
    public List<Dict> selectDictTranslates (Dict dict){
        List<Dict>  result = null;
        if (dict != null) {
            EntityWrapper<Dict> entityWrapper = new EntityWrapper();
            if (!StringUtils.isBlank(dict.getValue()) && !StringUtils.isBlank(dict.getType())){
                entityWrapper.setSqlSelect(" label, value, type, sort");
                entityWrapper.where("type = {0} ", dict.getType());
                entityWrapper.orderBy(" sort asc");
                result =  dictMapper.selectList(entityWrapper);
            }
        }
        return result;
    }


	public Page<Dict> selectPage(Page<Dict> page, Dict dict){
        EntityWrapper<Dict> entityWrapper = new EntityWrapper();

        if (null != entityWrapper) {

        }
        page.setRecords(dictMapper.selectPage(page, entityWrapper));

        return page;
    }
    /**
     * 新建字典
     * @param dict
     */
    public void insertDict(Dict dict) {
        dictMapper.insertDict(dict);
    }

    /**
     * 修改字典
     * @param dict
     */
    public void updateDict(Dict dict) {
        dictMapper.updateById(dict);
    }

    /**
     * 删除字典
     * @param id
     */
    public void deleteDict(String id) {
        dictMapper.deleteById(id);
    }

    @Override
    public String selectDictTranslate(String type, String value) {
        Dict dict = new Dict();
        dict.setType(type);
        dict.setValue(value);
        return  selectDictTranslate(dict);
    }

}
