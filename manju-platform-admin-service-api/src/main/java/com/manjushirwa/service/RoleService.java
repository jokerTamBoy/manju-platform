package com.manjushirwa.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.manjushirwa.pojo.admin.po.Role;

/**
 * 后台角色管理
 */


public interface RoleService {

    /**
     * 通过ID查找角色
     *
     * @param id
     * @return
     */
    public Role findById(String id);


    public Page<Role> selectRoles(Page<Role> page, Integer state);

    /**
     * 新增角色
     *
     * @param role
     */
    public void insertRole(Role role);

    /**
     * 修改角色
     *
     * @param role
     */
    public void updateRole(Role role);

    /**
     * 删除角色
     *
     * @param id
     */
    public void deleteRole(String id);

}
