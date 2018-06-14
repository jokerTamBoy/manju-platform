package com.manjushirwa.service;

import com.manjushirwa.pojo.admin.po.Menu;

import java.util.List;

/**
 * 后台菜单管理
 */


public interface MenuService {
    /**
     * 获取所有菜单
     * @return 菜单集合
     */
    public List<Menu> listAll();

    /**
     * 获取所有菜单
     * @return 菜单集合
     */
    public List<Menu> listTree();

    /**
     * 通过ID查找菜单
     * @param id
     * @return
     */
    public Menu getById(String id);

    public List<Menu> selectMenus(Menu Menu);

    /**
     * 新增菜单
     * @param Menu
     */
    public void insert(Menu menu);

    /**
     * 修改菜单
     * @param Menu
     */
    public void update(Menu menu);

    /**
     * 删除菜单
     * @param id
     */
    public void deleteMenu(String id);

}
