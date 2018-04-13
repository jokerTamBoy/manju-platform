package com.manjushirwa.service.api;


import com.manjushirwa.pojo.mobile.po.Users;

/**
 * Created by Ciaoyen on 07/04/2018.
 */
public interface UsersService {
    Users findById(Integer id);
    void insertUser(Users users);
    void updateUser(Users users);
    void deleteUser(Integer id);
    Users findById1(Integer id);
}
