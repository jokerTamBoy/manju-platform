package com.manjushira.controller;

import com.manjushira.pojo.admin.po.User;
import com.manjushira.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.manjushira.common.web.BaseController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Ciaoyen on 18/03/2018.
 */
@Controller
public class UserController extends BaseController {

    private static final Logger _logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;




    /**
     * 用户
     *
     * @param model
     * @return
     */
    @RequestMapping({"/user"})
    public String user(Model model) {
        List<User> users = userService.selectUsers(null);
        for (User user : users) {
            System.out.println("用户: --------->" + user.getName());
        }
        model.addAttribute("users", users);
        return "user";
    }

    /**
     * 用户
     *
     * @param model
     * @return
     */
    @RequestMapping({"/users/insert"})
    public String userInsert(Model model) {
        return "insert/insertUser";
    }


}
