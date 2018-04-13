package com.manjushirwa.service;

import com.manjushirwa.pojo.admin.po.Rule;

import java.util.List;

/**
 * 后台权限管理
 */


public interface RuleService {

    /**
     * 通过ID查找角色
     * @param id
     * @return
     */
    public Rule findById(String id);


    /**
     * 获取角色
     * @param Role
     * @return
     */
    public List<Rule> selectRoles(Rule Role);

    /**
     * 新增角色
     * @param Rule
     */
    public void insertRole(Rule Rule);

    /**
     * 修改角色
     * @param Role
     */
    public void updateRole(Rule Role);

    /**
     * 删除角色
     * @param id
     */
    public void deleteRole(String id);

}
