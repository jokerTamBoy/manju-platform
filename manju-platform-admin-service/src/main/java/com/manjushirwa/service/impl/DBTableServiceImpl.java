package com.manjushirwa.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.manjushirwa.mapper.admin.DBTableMapper;
import com.manjushirwa.pojo.admin.po.DBTable;
import com.manjushirwa.service.DBTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 后台菜单管理
 */

@Service
public class DBTableServiceImpl implements DBTableService {

    @Resource
    private DBTableMapper dbTableMapper;

    /**
     * 分页查询
     * @param id
     * @return
     */
    @Override
    public Page<DBTable> selectPage(Page<DBTable> page, Wrapper<DBTable> ew) {
        page.setRecords(dbTableMapper.selectPage(page,ew));
        return page;
    }
}
