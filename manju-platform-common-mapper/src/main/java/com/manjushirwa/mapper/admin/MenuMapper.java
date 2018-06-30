package com.manjushirwa.mapper.admin;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.manjushirwa.pojo.admin.po.Menu;

import java.util.List;

/**
 * 后台管理用户表 Mapper
 *
 * @author Administrator
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 获取菜单
     * @return
     */
    Menu getById(String id);

    /**
     * 获取所有菜单
     * @return
     */
    List<Menu> listAll();

    /**
     * 获取所有菜单（树状）
     * @return
     */
    List<Menu> listTree();

    /**
     * 新增菜单
     * @return
     */
    Integer insert(Menu menu);

    /**
     * 更新菜单
     * @return
     */
    Integer update(Menu menu);


    /**
     * 删除菜单
     * @return
     */
    Integer deleteById(Menu menu);
    /**
    * 分页查询菜单菜单
    * @return
    */
    Page<Menu> selectPage();
}
