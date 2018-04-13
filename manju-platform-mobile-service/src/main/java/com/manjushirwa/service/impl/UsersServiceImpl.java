package com.manjushirwa.service.impl;

import com.manjushirwa.config.annotion.DataSource;
import com.manjushirwa.config.datasource.DSEnum;
import com.manjushirwa.mapper.mobile.UsersMapper;
import com.manjushirwa.pojo.mobile.po.Users;
import com.manjushirwa.service.api.UsersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Ciaoyen on 07/04/2018.
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    /**
     * 通过ID查找用户
     * @param id
     * @return
     */
    public Users findById(Integer id) {
        return usersMapper.selectById(id);
    }

    /**
     * 通过ID查找用户
     * @param id
     * @return
     */
    @DataSource(name = DSEnum.DATA_SOURCE_BIZ)
    public Users findById1(Integer id) {
        return usersMapper.selectById(id);
    }

    /**
     * 新增用户
     * @param users
     */
    public void insertUser(Users users) {
        usersMapper.insert(users);
    }

    /**
     * 修改用户
     * @param users
     */
    public void updateUser(Users users) {
        usersMapper.updateById(users);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteUser(Integer id) {
        usersMapper.deleteById(id);
    }
}
