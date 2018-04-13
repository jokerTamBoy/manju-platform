package com.manjushirwa.service.impl;

import com.manjushirwa.mapper.admin.LogMapper;
import com.manjushirwa.pojo.admin.po.Log;
import com.manjushirwa.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台日志管理
 */

@Service
public class LogServiceImpl implements LogService{

    @Resource
    private LogMapper logMapper;

    /**
     * 通过ID查找角色
     * @param id
     * @return
     */
    public Log findById(String id) {
        return logMapper.selectById(id);
    }

    public List<Log> selectRoles(Log Role){ return logMapper.selectList(null);}

    /**
     * 新增角色
     * @param Log
     */
    public void insertRole(Log Log) {
        logMapper.insert(Log);
    }

    /**
     * 修改角色
     * @param Role
     */
    public void updateRole(Log Role) {
        logMapper.updateById(Role);
    }

    /**
     * 删除角色
     * @param id
     */
    public void deleteRole(String id) {
        logMapper.deleteById(id);
    }

}
