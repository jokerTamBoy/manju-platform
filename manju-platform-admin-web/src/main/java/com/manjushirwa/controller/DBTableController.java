/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.manjushirwa.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.manjushirwa.common.web.BaseController;
import com.manjushirwa.pojo.admin.po.DBTable;
import com.manjushirwa.service.DBTableService;
import com.manjushirwa.util.GeneratorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author Alex_yjl
 * @version 2018-6-29
 */
@Controller
@RequestMapping(value = "/dbTable")
public class DBTableController extends BaseController {

    private static final Logger _logger = LoggerFactory.getLogger(BaseController.class);

    @Resource
    private DBTableService dbTableService;
    @Autowired
    private GeneratorConfig generatorConfig;

    /**
     * 跳转 列表页面
     */
    @RequestMapping(value = "/list")
    public String list() {
        return "dbTable/dbTable_list";
    }
    /**
     * 数据库表单 列表
     */
    @RequestMapping(value = "/listData")
    @ResponseBody
    public String listData(@RequestParam Integer start, @RequestParam Integer length) {
        JSONObject resultObj = new JSONObject();
        Integer pageIndex = start/length + 1;
        Page<DBTable> page = new Page<DBTable>(pageIndex, length);
        EntityWrapper<DBTable> ew = new EntityWrapper<DBTable>();
        ew.where("table_schema='manju' and table_type='base table'");
        ew.orderBy("create_time", false);
        page = dbTableService.selectPage(page, ew);
        resultObj.put("data", page.getRecords());
        resultObj.put("recordsTotal", page.getTotal());
        resultObj.put("recordsFiltered", page.getTotal());
        return resultObj.toJSONString();
    }

    /**
     * 执行代码生成
     */
    @RequestMapping(value = "/doGenerator")
    @ResponseBody
    public String doGenerator(@RequestParam String tableName) {
        JSONObject resultObj = new JSONObject();

        //判断实体类是否已经存在
        StringBuffer nameBuff = new StringBuffer(generatorConfig.getBaseEntityPath());
        String nameArr[] = tableName.split("_");
        for(int i=1;i<nameArr.length;i++){
            nameBuff.append(nameArr[i].substring(0,1).toUpperCase());
            nameBuff.append(nameArr[i].substring(1));
        }
        try{
            Class<?> object = Class.forName(nameBuff.toString());
            resultObj.put("result", false);
            resultObj.put("msg", "实体已存在");
            return resultObj.toJSONString();
        }catch (Exception e){

        }

        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Veloctiy
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(generatorConfig.getDirPath());
        gc.setAuthor("Alex_yjl");
        gc.setFileOverride(true); //是否覆盖
        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sMapper");
        // gc.setServiceName("MP%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
                return super.processTypeConvert(fieldType);
            }
        });
        dsc.setDriverName(generatorConfig.getDriverName());
        dsc.setUsername(generatorConfig.getUserName());
        dsc.setPassword(generatorConfig.getPassword());
        dsc.setUrl(generatorConfig.getDatasourceURL());
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setTablePrefix(new String[] { "test_","sys_"});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[] { "test_table" }); // 需要生成的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        // 自定义实体父类
        strategy.setSuperEntityClass("com.manjushirwa.pojo.base.DataEntity");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        strategy.setSuperMapperClass("com.baomidou.mybatisplus.mapper.BaseMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        strategy.setSuperControllerClass("com.manjushirwa.common.web.BaseController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        strategy.setEntityBuilderModel(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com");
        pc.setModuleName("manjushirwa");
        pc.setController("controller");
        pc.setEntity("pojo.admin.po");
        pc.setMapper("mapper.admin");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setXml("mapperXml");

        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("superEntityFields", "id,remarks,create_by,create_date,update_by,update_date,del_flag");
                map.put("entityName", "Test");
                this.setMap(map);
            }
        };

        // 自定义 页面模板
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
/*        focList.add(new FileOutConfig("/template/list.html.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
               return dirPath + htmlPath + tableInfo.getEntityName() + "/" + tableInfo.getEntityName() + "_list.html";
            }
        });
        focList.add(new FileOutConfig("/template/detail.html.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
               return dirPath + htmlPath + tableInfo.getEntityName() + "/" + tableInfo.getEntityName() + "_detail.html";
            }
        });*/

        // 自定义mapper.xml模板
        focList.add(new FileOutConfig("/templates/velocity/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return generatorConfig.getDirPath() + generatorConfig.getMapperXmlPath() + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        // 自定义mapper.java模板
        focList.add(new FileOutConfig("/templates/velocity/mapper.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return generatorConfig.getDirPath() + generatorConfig.getMapperPath() + tableInfo.getEntityName() + "Mapper.java";
            }
        });
        // 自定义entity.java模板
        focList.add(new FileOutConfig("/templates/velocity/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return generatorConfig.getDirPath() + generatorConfig.getEntityPath() + tableInfo.getEntityName() + ".java";
            }
        });
        // 自定义service.java模板
        focList.add(new FileOutConfig("/templates/velocity/service.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return generatorConfig.getDirPath() + generatorConfig.getServicePath() + "I" + tableInfo.getEntityName() + "Service.java";
            }
        });
        // 自定义serviceImpl.java模板
        focList.add(new FileOutConfig("/templates/velocity/serviceImpl.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return generatorConfig.getDirPath() + generatorConfig.getServiceImplPath() + tableInfo.getEntityName() + "ServiceImpl.java";
            }
        });
        // 自定义controller.java模板
        focList.add(new FileOutConfig("/templates/velocity/controller.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return generatorConfig.getDirPath() + generatorConfig.getControllerPath() + tableInfo.getEntityName() + "Controller.java";
            }
        });
        // 加入模板
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 关闭默认 文件 生成
        TemplateConfig tc = new TemplateConfig();
        tc.setController(null);
        tc.setEntity(null);
        tc.setMapper(null);
        tc.setXml(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();

        // 打印注入设置【可无】
        System.err.println(mpg.getCfg().getMap().get("superEntityFields"));
        resultObj.put("result", false);
        return resultObj.toJSONString();
    }
}
