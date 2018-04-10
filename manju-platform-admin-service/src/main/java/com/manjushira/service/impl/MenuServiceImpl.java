package com.manjushira.service.impl;

import com.manjushira.mapper.admin.MenuMapper;
import com.manjushira.pojo.admin.po.Menu;
import com.manjushira.service.MenuService;
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
     * 通过ID查找角色
     * @param id
     * @return
     */
    public Menu findById(String id) {
        return menuMapper.selectById(id);
    }

    public List<Menu> selectRoles(Menu Role){ return menuMapper.selectList(null);}

    /**
     * 新增角色
     * @param Menu
     */
    public void insertRole(Menu Menu) {
        menuMapper.insert(Menu);
    }

    /**
     * 修改角色
     * @param Role
     */
    public void updateRole(Menu Role) {
        menuMapper.updateById(Role);
    }

    /**
     * 删除角色
     * @param id
     */
    public void deleteRole(String id) {
        menuMapper.deleteById(id);
    }

}
