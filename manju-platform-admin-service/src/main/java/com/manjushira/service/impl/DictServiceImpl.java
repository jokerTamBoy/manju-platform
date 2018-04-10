package com.manjushira.service.impl;

import com.manjushira.mapper.admin.DictMapper;
import com.manjushira.pojo.admin.po.Dict;
import com.manjushira.service.DictService;
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
     * 通过ID查找角色
     * @param id
     * @return
     */
    public Dict findById(String id) {
        return dictMapper.selectById(id);
    }


    public List<Dict> selectDicts(Dict dict){ return dictMapper.selectList(null);}

    /**
     * 新增角色
     * @param dict
     */
    public void insertDict(Dict dict) {
        dictMapper.insertDict(dict);
    }

    /**
     * 修改角色
     * @param dict
     */
    public void updateDict(Dict dict) {
        dictMapper.updateById(dict);
    }

    /**
     * 删除角色
     * @param id
     */
    public void deleteDict(String id) {
        dictMapper.deleteById(id);
    }

}
