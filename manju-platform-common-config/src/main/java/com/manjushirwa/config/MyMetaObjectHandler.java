//package com.manjushirwa.config;
//
//import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
//import com.manjushirwa.pojo.admin.po.User;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.shiro.SecurityUtils;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///** mybatisplus自定义填充公共字段 ,即没有传的字段自动填充*/
//@Component
//public class MyMetaObjectHandler extends MetaObjectHandler {
//    //新增填充
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        Object createBy = metaObject.getValue("createBy");
//        Object createDate = metaObject.getValue("createDate");
//        //获取当前登录用户
//        Object object = SecurityUtils.getSubject().getPrincipal();
//        User user = object!=null?(User)object:null;
//        if (null == createBy) {
//            metaObject.setValue("createBy", user);
//        }
//        if (null == createDate) {
//            metaObject.setValue("createDate", new Date());
//        }
//    }
//
//    //更新填充
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        Object updateBy = metaObject.getValue("updateBy");
//        Object updateDate = metaObject.getValue("updateDate");
//        //获取当前登录用户
//        User user = (User)SecurityUtils.getSubject().getPrincipal();
//        if (null == updateBy) {
//            metaObject.setValue("updateBy", user);
//        }
//        if (null == updateDate) {
//            metaObject.setValue("updateDate", new Date());
//        }
//    }
//}
