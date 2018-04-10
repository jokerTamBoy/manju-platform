package com.manjushira.service.impl;

import com.manjushira.mapper.admin.OfficeMapper;
import com.manjushira.pojo.admin.po.Office;
import com.manjushira.service.OfficeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台组织机构管理
 */

@Service
public class OfficeServiceImpl implements OfficeService{

    @Resource
    private OfficeMapper officeMapper;

    /**
     * 通过ID查找角色
     * @param id
     * @return
     */
    public Office findById(String id) {
        return officeMapper.selectById(id);
    }

    public List<Office> selectRoles(Office Role){ return officeMapper.selectList(null);}

    /**
     * 新增角色
     * @param Office
     */
    public void insertRole(Office Office) {
        officeMapper.insert(Office);
    }

    /**
     * 修改角色
     * @param Role
     */
    public void updateRole(Office Role) {
        officeMapper.updateById(Role);
    }

    /**
     * 删除角色
     * @param id
     */
    public void deleteRole(String id) {
        officeMapper.deleteById(id);
    }

}
