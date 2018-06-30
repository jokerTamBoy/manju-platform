package com.manjushirwa.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
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

    /**
     * 新增菜单
     * @param Menu
     */
    public Integer insert(Menu menu);

    /**
     * 修改菜单
     * @param Menu
     */
    public Integer update(Menu menu);

    /**
     * 删除菜单
     * @param id
     */
    public Integer deleteById(String id);

    /**
     * 分页查询
     * @param id
     */
    public Page<Menu> selectPage(Page<Menu> page, Wrapper<Menu> ew);
}
