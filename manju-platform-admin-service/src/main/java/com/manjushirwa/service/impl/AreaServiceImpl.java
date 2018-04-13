package com.manjushirwa.service.impl;

import com.manjushirwa.mapper.admin.AreaMapper;
import com.manjushirwa.pojo.admin.po.Area;
import com.manjushirwa.service.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台行政区域管理
 */

@Service
public class AreaServiceImpl implements AreaService{

    @Resource
    private AreaMapper areaMapper;

    /**
     * 通过ID查找角色
     * @param id
     * @return
     */
    public Area findById(String id) {
        return areaMapper.selectById(id);
    }

    public List<Area> selectRoles(Area Role){ return areaMapper.selectList(null);}

    /**
     * 新增角色
     * @param Area
     */
    public void insertRole(Area Area) {
        areaMapper.insert(Area);
    }

    /**
     * 修改角色
     * @param Role
     */
    public void updateRole(Area Role) {
        areaMapper.updateById(Role);
    }

    /**
     * 删除角色
     * @param id
     */
    public void deleteRole(String id) {
        areaMapper.deleteById(id);
    }

}
