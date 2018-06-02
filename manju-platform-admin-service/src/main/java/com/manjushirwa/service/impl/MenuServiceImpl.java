package com.manjushirwa.service.impl;

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
    public Menu findById(String id) {
        return menuMapper.selectById(id);
    }

    public List<Menu> selectMenus(Menu menu){ return menuMapper.selectList(null);}

    /**
     * 新增角色
     * @param Menu
     */
    public void insertMenu(Menu menu) {
        menuMapper.insert(menu);
    }

    /**
     * 修改角色
     * @param Menu
     */
    public void updateMenu(Menu menu) {
        menuMapper.updateById(menu);
    }

    /**
     * 删除角色
     * @param id
     */
    public void deleteMenu(String id) {
        menuMapper.deleteById(id);
    }

}
