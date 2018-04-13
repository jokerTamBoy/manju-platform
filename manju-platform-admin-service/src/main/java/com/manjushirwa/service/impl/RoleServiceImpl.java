package com.manjushirwa.service.impl;


import com.baomidou.mybatisplus.plugins.Page;
import com.manjushirwa.mapper.admin.RoleMapper;
import com.manjushirwa.pojo.admin.po.Role;
import com.manjushirwa.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 后台角色管理
 */

@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    /**
     * 通过ID查找角色
     *
     * @param id
     * @return
     */
    public Role findById(String id) {
        return roleMapper.selectById(id);
    }


    public Page<Role> selectRoles(Page<Role> page, Integer state) {
        return page.setRecords(roleMapper.selectList(page, state));
    }

    /**
     * 新增角色
     *
     * @param role
     */
    public void insertRole(Role role) {
        roleMapper.insert(role);
    }

    /**
     * 修改角色
     *
     * @param role
     */
    public void updateRole(Role role) {
        roleMapper.updateById(role);
    }

    /**
     * 删除角色
     *
     * @param id
     */
    public void deleteRole(String id) {
        roleMapper.deleteById(id);
    }

}
