package com.manjushirwa.service;

import com.manjushirwa.pojo.admin.po.Log;

import java.util.List;

/**
 * 后台日志管理
 */


public interface LogService {

    /**
     * 通过ID查找角色
     * @param id
     * @return
     */
    public Log findById(String id);

    public List<Log> selectRoles(Log Role);

    /**
     * 新增角色
     * @param Log
     */
    public void insertRole(Log Log);

    /**
     * 修改角色
     * @param Role
     */
    public void updateRole(Log Role);

    /**
     * 删除角色
     * @param id
     */
    public void deleteRole(String id);

}
