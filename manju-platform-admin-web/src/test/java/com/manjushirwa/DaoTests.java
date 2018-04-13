package com.manjushirwa;

import com.manjushirwa.pojo.admin.po.Role;
import com.manjushirwa.pojo.admin.po.User;
import com.manjushirwa.service.RoleService;
import com.manjushirwa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTests {
    private static final Logger log = LoggerFactory.getLogger(DaoTests.class);

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    /**
     * 测试增删改查
     */
    @Test
    public void test() {
        User user = new User();
//        user.setName("小星星");
//        user.setPassword("222222");
//        user.setPhone("13890907676");
        user.setLoginName("sd_admin");
//        user.setCreateBy(new User("123"));
//        userService.insertUser(user);



        User user1 = userService.findByLoginName(user.getLoginName());
        assertThat(user1.getName(), is("管理员"));
//
//        user1.setPassword("888888");
//        userService.updateUser(user1);
//        User user2 = userService.findById(user.getId());
//        assertThat(user2.getPassword(), is("888888"));
//
//        userService.deleteUser(user.getId());
//
//        User user3 = userService.findById(user.getId());
//        assertThat(user3, nullValue());

    }

    /**
     * 角色部分增删改查
     */
    @Test
    public void roletest(){

        Role role = new Role();
        role.setName("超级管理员");
        role.setEnname("super");
        role.setDataScope("5");
        role.setRemarks("123");
        role.setUseable("1");
        role.setDelFlag("2");
        role.setCreateBy(new User("123"));

        roleService.insertRole(role);
    }
}
