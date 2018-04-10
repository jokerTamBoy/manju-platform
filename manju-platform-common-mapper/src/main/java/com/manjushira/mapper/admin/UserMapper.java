package com.manjushira.mapper.admin;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.manjushira.pojo.admin.po.User;

import java.util.List;

/**
 * Created by Ciaoyen on 07/04/2018.
 */
public interface UserMapper  extends BaseMapper<User> {


     /**
      * 查询用户集
      * @param user
      * @return
      */
     List<User> selectList(User user);

     /**
      * 查找单个用户
      * @param user
      * @return
      */
     User selectUser(User user);

     /**
      * 根据登录名查找用户
      * @return
      */
     User selectUserByLoginName(String loginName);


     /**
      * 新增用户
      * @param user 用户
      * @return
      */
     Integer insert(User user);
     /**
      * 获取所有用户
      * @return 用户集合
      */
     List<User> getAll();

     /**
      * 根据登录名获取用户
      * @param loginName 登录名
      * @return
      */
     User getUserByLoginName(String loginName);
}
