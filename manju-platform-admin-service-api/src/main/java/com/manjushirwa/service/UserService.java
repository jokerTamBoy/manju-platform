package com.manjushirwa.service;

import com.manjushirwa.pojo.admin.po.User;

import java.util.List;

/**
 * Created by Ciaoyen on 07/04/2018.
 */
public interface UserService {
     /**
      * 获取所有用户
      * @return
      */
     List<User> getAll();

     /**
      * 根据登录名获取用户
      * @param loginName 登录名
      * @return
      */
     User findByLoginName(String loginName);

     /**
      * 通过ID查找用户
      * @param id ID
      * @return
      */
      User findById(String id) ;

     /**
      *  传递用户参数查找用户
      * @param user 用户
      * @return
      */
      User findUser(User user);

     /**
      * 查询用户集
      * @param user
      * @return
      */
      List<User> selectUsers(User user);

     /**
      * 新增用户
      * @param user
      */
      void insertUser(User user);

     /**
      * 修改用户
      * @param user
      */
      void updateUser(User user) ;

     /**
      * 删除用户
      * @param id
      */
      void deleteUser(String id) ;

}
