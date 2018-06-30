package com.manjushirwa.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.manjushirwa.mapper.admin.MenuMapper;
import com.manjushirwa.pojo.admin.po.Menu;
import com.manjushirwa.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台菜单管理
 */

@Service
public class MenuServiceImpl implements MenuService{

    @Resource
    private MenuMapper menuMapper;
    /**
     * 获取所有菜单
     * @return 菜单集合
     */
    public List<Menu> listAll(){ return menuMapper.listAll();}
    /**
     * 获取所有菜单（树状）
     * @return 菜单集合
     */
    public List<Menu> listTree(){ return menuMapper.listTree();}
    /**
     * 通过ID查找菜单
     * @param id
     * @return
     */
    public Menu getById(String id) {
        return menuMapper.getById(id);
    }

    /**
     * 新增菜单
     * @param Menu
     */
    public Integer insert(Menu menu) {
        return menuMapper.insert(menu);
    }

    /**
     * 修改菜单
     * @param Menu
     */
    public Integer update(Menu menu) {
        return menuMapper.update(menu);
    }

    /**
     * 删除菜单
     * @param id
     */
    public Integer deleteById(String id) {
        return menuMapper.deleteById(id);
    }

    /**
     * 分页查询
     * @param id
     * @return
     */
    @Override
    public Page<Menu> selectPage(Page<Menu> page, Wrapper<Menu> ew) {
        page.setRecords(menuMapper.selectPage(page,ew));
        return page;
    }
}
