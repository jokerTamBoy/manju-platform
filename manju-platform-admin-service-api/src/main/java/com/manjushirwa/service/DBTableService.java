package com.manjushirwa.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.manjushirwa.pojo.admin.po.DBTable;

/**
 *  数据库表单列表
 */


public interface DBTableService {
    /**
     * 分页查询
     * @param id
     */
    public Page<DBTable> selectPage(Page<DBTable> page, Wrapper<DBTable> ew);
}
