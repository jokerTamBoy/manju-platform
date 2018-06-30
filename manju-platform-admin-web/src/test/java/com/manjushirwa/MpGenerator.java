package com.manjushirwa;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成器演示
 * </p>
 */
public class MpGenerator {

    //@Value("${dirPath.base}")
    //protected static String  dirPath = "C:/Users/ALEX/IdeaProjects/manju-platform";
    protected static String  dirPath = "C:/Users/ALEX/test/";

    //@Value("${dirPath.entity}")
    //protected static String  entityPath = "/manju-platform-common-pojo/src/main/java/com/manjushirwa/pojo/admin/po/";

    //@Value("${dirPath.mapperXml}")
    //protected static String  mapperXmlPath = "/manju-platform-common-mapper/src/main/resources/mapper/admin/";

    //@Value("${dirPath.mapper}")
    //protected static String  mapperPath = "/manju-platform-common-mapper/src/main/java/com/manjushirwa/mapper/admin/";

    //@Value("${dirPath.service}")
    //protected static String  servicePath = "/manju-platform-admin-service-api/src/main/java/com/manjushirwa/service/";

    //@Value("${dirPath.serviceImpl}")
    //protected static String  serviceImplPath = "/manju-platform-admin-service/src/main/java/com/manjushirwa/service/impl/";

    //@Value("${dirPath.controller}")
    //protected static String  controllerPath = "/manju-platform-admin-web/src/main/java/com/manjushirwa/controller/";

    //@Value("${dirPath.html}")
    //protected static String  htmlPath = "/manju-platform-admin-web/src/main/resources/templates/";

    /**
     * <p>
     * MySQL 生成演示
     * </p>
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Veloctiy
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(dirPath);
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
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://120.79.72.80:3306/manju?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        strategy.setTablePrefix(new String[] { "test_"});// 此处可以修改为您的表前缀
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
        pc.setController("controler");
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
                return dirPath + tableInfo.getEntityName() + "Mapper.xml";
                //return dirPath + mapperXmlPath + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        // 自定义mapper.java模板
        focList.add(new FileOutConfig("/templates/velocity/mapper.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return dirPath + tableInfo.getEntityName() + "Mapper.java";
                //return dirPath + mapperPath + tableInfo.getEntityName() + "Mapper.java";
            }
        });
        // 自定义entity.java模板
        focList.add(new FileOutConfig("/templates/velocity/entity.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return dirPath + tableInfo.getEntityName() + ".java";
                //return dirPath + entityPath + tableInfo.getEntityName() + ".java";
            }
        });
        // 自定义service.java模板
        focList.add(new FileOutConfig("/templates/velocity/service.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return dirPath + "I" + tableInfo.getEntityName() + "Service.java";
                //return dirPath + servicePath + "I" + tableInfo.getEntityName() + "Service.java";
            }
        });
        // 自定义serviceImpl.java模板
        focList.add(new FileOutConfig("/templates/velocity/serviceImpl.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return dirPath + tableInfo.getEntityName() + "ServiceImpl.java";
                //return dirPath + serviceImplPath + tableInfo.getEntityName() + "ServiceImpl.java";
            }
        });
        // 自定义controller.java模板
        focList.add(new FileOutConfig("/templates/velocity/controller.java.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return dirPath + tableInfo.getEntityName() + "Controller.java";
                //return dirPath + controllerPath + tableInfo.getEntityName() + "Controller.java";
            }
        });
        // 加入模板
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 关闭默认 文件 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setController(null);
        tc.setEntity(null);
        tc.setMapper(null);
        tc.setXml(null);
        tc.setService(null);
        tc.setServiceImpl(null);
        mpg.setTemplate(tc);
        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        // TemplateConfig tc = new TemplateConfig();
        // tc.setController("/templates/velocity/controller.java.vm");
        // tc.setEntity("/templates/velocity/entity.java.vm");
        // tc.setMapper("/templates/velocity/mapper.java.vm");
        // tc.setXml("/templates/velocity/mapper.xml.vm");
        // tc.setService("/templates/velocity/service.java.vm");
        // tc.setServiceImpl("/templates/velocity/serviceImpl.java.vm");
        // mpg.setTemplate(tc);

        // 执行生成
        mpg.execute();

        // 打印注入设置【可无】
        System.err.println(mpg.getCfg().getMap().get("superEntityFields"));
    }

}