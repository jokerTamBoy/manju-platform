package com.manjushirwa.mapper.admin;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.manjushirwa.pojo.admin.po.Role;

import java.util.List;

/**
 * 后台管理用户表 Mapper
 *
 * @author Administrator
 */
public interface RoleMapper extends BaseMapper<Role> {

     List<Role> selectList(Pagination page, Integer state);

}
