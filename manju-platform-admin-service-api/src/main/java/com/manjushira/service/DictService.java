package com.manjushira.service;

import com.manjushira.pojo.admin.po.Dict;

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
     * 新增角色
     * @param dict
     */
    public void insertDict(Dict dict);

    /**
     * 修改角色
     * @param dict
     */
    public void updateDict(Dict dict);

    /**
     * 删除角色
     * @param id
     */
    public void deleteDict(String id);

}
