/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.manjushirwa.controller;

import com.manjushirwa.common.web.BaseController;
import com.manjushirwa.config.properties.MyProperties;
import com.manjushirwa.kits.shiro.ShiroKit;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 登录Controller
 *
 * @author Ciaoyen
 * @since 18/2/18
 */
@Controller
public class LoginController extends BaseController {

//	@Autowired
//	private SessionDAO sessionDAO;

    @Resource
    private MyProperties myProperties;

    private static final Logger _logger = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(value = "/login2", method = RequestMethod.GET)
    public String login(Model model) {
        if (ShiroKit.isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login2(Model model) {
        if (ShiroKit.isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }

    // 登录提交地址和applicationontext-shiro.xml配置的loginurl一致。 (配置文件方式的说法)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Map<String, Object> map) {
        _logger.info("登录方法start.........");
        // 登录失败从request中获取shiro处理的异常信息。shiroLoginFailure:就是shiro异常类的全类名.

        Object exception = request.getAttribute("shiroLoginFailure");
        String msg;
        if (exception != null) {
            if (UnknownAccountException.class.isInstance(exception)) {
                msg = "用户名不正确，请重新输入";
            } else if (IncorrectCredentialsException.class.isInstance(exception)) {
                msg = "密码错误，请重新输入";
            } else {
                msg = "发生未知错误，请联系管理员。";
            }
            System.out.println(msg);
            map.put("username", request.getParameter("username"));
            map.put("password", request.getParameter("password"));
            map.put("msg", msg);
            return "login";
        }
        //如果已经登录，直接跳转主页面
        return "index";
    }


    /**
     * 管理登录
     */
    @RequestMapping(value = "${adminPath}/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "modules/sys/sysLogin";
    }

    /**
     * 登录失败，真正登录的POST请求由Filter完成
     */
    @RequestMapping(value = "${adminPath}/login", method = RequestMethod.POST)
    public String loginFail(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "modules/sys/sysIndex";
    }

    /**
     * 登录成功，进入管理首页
     */
    @RequiresPermissions("user")
    @RequestMapping(value = "${adminPath}")
    public String index(HttpServletRequest request, HttpServletResponse response) {

        return "modules/sys/sysIndex";
    }

    /**
     * 获取主题方案
     */
    @RequestMapping(value = "/theme/{theme}")
    public String getThemeInCookie(@PathVariable String theme, HttpServletRequest request, HttpServletResponse response) {

        return "modules/sys/sysIndex";
    }

    /**
     * 是否是验证码登录
     *
     * @param useruame 用户名
     * @param isFail   计数加1
     * @param clean    计数清零
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean) {
        return false;
    }
}
