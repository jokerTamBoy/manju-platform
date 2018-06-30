package com.manjushirwa;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.manjushirwa.pojo.admin.po.Dict;
import com.manjushirwa.pojo.admin.po.Menu;
import com.manjushirwa.pojo.admin.po.Role;
import com.manjushirwa.pojo.admin.po.User;
import com.manjushirwa.service.DictService;
import com.manjushirwa.service.MenuService;
import com.manjushirwa.service.RoleService;
import com.manjushirwa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @Resource
    private DictService dictService;

    @Resource
    private MenuService menuService;

    @Test
    public void dictTest(){
        Page<Dict> page = new Page<>();
        page.setCurrent(1);
        page.setSize(100);
        Page<Dict> page2 = dictService.selectPage(page,null);

        System.out.println(page2.getTotal());
        System.out.println(page2);
        List<Dict> list = page2.getRecords();
        for (Dict dict : list){
            System.out.println(dict.getLabel());
        }
    }


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

    @Test
    public void menuTest(){
        Page<Menu> page = new Page<Menu>(1,10);
        Wrapper<Menu> ew = new EntityWrapper<Menu>();
        ew.like("name","管理");
        menuService.selectPage(page,ew);
        JSONArray menuList = new JSONArray();
        menuList.addAll(page.getRecords());
        System.out.println(page.getSize());
    }

    @Test
    public void deleteMenuTest(){
        menuService.deleteById("");
    }

    @Test
    public void updateMenuTest(){
        Menu menu = menuService.getById("1");
        menu.setUpdateDate(new Date());
        menu.setUpdateBy(new User("2"));
        menu.setRemarks("单元测试");
        menu.updateById();
    }
}
