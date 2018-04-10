package com.manjushira.service;

import com.manjushira.pojo.admin.po.Menu;

import java.util.List;

/**
 * 后台菜单管理
 */


public interface MenuService {

    /**
     * 通过ID查找角色
     * @param id
     * @return
     */
    public Menu findById(String id);

    public List<Menu> selectRoles(Menu Role);

    /**
     * 新增角色
     * @param Menu
     */
    public void insertRole(Menu Menu);

    /**
     * 修改角色
     * @param Role
     */
    public void updateRole(Menu Role);

    /**
     * 删除角色
     * @param id
     */
    public void deleteRole(String id);

}
