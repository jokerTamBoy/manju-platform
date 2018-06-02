package com.manjushirwa.mapper.admin;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.manjushirwa.pojo.admin.po.Menu;

import java.util.List;

/**
 * 后台管理用户表 Mapper
 *
 * @author Administrator
 */
public interface MenuMapper extends BaseMapper<Menu> {


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
}
