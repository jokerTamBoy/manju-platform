package com.manjushira.service;

import com.manjushira.pojo.admin.po.Area;

import java.util.List;

/**
 * 后台行政区域管理
 */

public interface AreaService {

    /**
     * 通过ID查找角色
     * @param id
     * @return
     */
     Area findById(String id);

     List<Area> selectRoles(Area Role);

    /**
     * 新增角色
     * @param Area
     */
     void insertRole(Area Area);

    /**
     * 修改角色
     * @param Role
     */
     void updateRole(Area Role);

    /**
     * 删除角色
     * @param id
     */
     void deleteRole(String id);

}
