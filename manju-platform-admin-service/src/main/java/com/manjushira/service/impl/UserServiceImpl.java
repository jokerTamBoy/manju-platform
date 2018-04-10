package com.manjushira.service.impl;

import com.manjushira.mapper.admin.UserMapper;
import com.manjushira.pojo.admin.po.User;
import com.manjushira.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Ciaoyen on 07/04/2018.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public User findByLoginName(String loginName) { return userMapper.selectUserByLoginName(loginName); }

    /**
     * 通过ID查找用户
     * @param id ID
     * @return
     */
    @Override
    public User findById(String id) {
        return userMapper.selectById(id);
    }

    /**
     *  传递用户参数查找用户
     * @param user 用户
     * @return
     */
    @Override
    public User findUser(User user){ return userMapper.selectUser(user); }

    /**
     * 查询用户集
     * @param user
     * @return
     */
    @Override
    public List<User> selectUsers(User user){ return userMapper.selectList(user);}

    /**
     * 新增用户
     * @param user
     */
    @Override
    public void insertUser(User user) {
        userMapper.insert(user);
    }

    /**
     * 修改用户
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void deleteUser(String id) {
        userMapper.deleteById(id);
    }
}
