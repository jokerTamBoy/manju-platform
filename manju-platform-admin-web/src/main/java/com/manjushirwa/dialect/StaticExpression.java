package com.manjushirwa.dialect;


import com.manjushirwa.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * 全局表达式
 */
@Controller
public class StaticExpression{

    @Autowired
    private DictService dictService;

//    @Resource(name="thymeleafViewResolver")
//    ThymeleafViewResolver thymeleafViewResolver;

    @Resource
    private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        System.err.println("初始化全局表达式");
        if(viewResolver != null) {
            Map<String, Object> vars = new HashMap<>();
            vars.put("dict", dictService);
            viewResolver.setStaticVariables(vars);
        }
    }

//    private void configureThymeleafStaticVars() {
//        if (thymeleafViewResolver != null) {
//            Map<String, Object> vars = new HashMap<>();
//            vars.put("dict", dictService);
//            thymeleafViewResolver.setStaticVariables(vars);
//        }
//    }

}
