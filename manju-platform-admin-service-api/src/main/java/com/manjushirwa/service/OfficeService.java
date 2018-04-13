package com.manjushirwa.service;

import com.manjushirwa.pojo.admin.po.Office;

import java.util.List;

/**
 * 后台组织机构管理
 */


public interface OfficeService {

    /**
     * 通过ID查找角色
     * @param id
     * @return
     */
    public Office findById(String id);

    public List<Office> selectRoles(Office Role);

    /**
     * 新增角色
     * @param Office
     */
    public void insertRole(Office Office);

    /**
     * 修改角色
     * @param Role
     */
    public void updateRole(Office Role);

    /**
     * 删除角色
     * @param id
     */
    public void deleteRole(String id);

}
