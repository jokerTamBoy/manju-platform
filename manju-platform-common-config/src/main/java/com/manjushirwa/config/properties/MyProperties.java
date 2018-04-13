package com.manjushirwa.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * 本项目自定义配置
 *
 * @author Ciaoyen
 * @since 2018/03/19
 */
@Data
@Component
@ConfigurationProperties(prefix = "myConfig")
public class MyProperties {
    /**
     * excel模板文件路径
     */
    private String excelPath = "";
    /**
     * 文件保存路径
     */
    private String filesPath = "";
    /**
     * 图片保存路径
     */
    private String picsPath = "";
    /**
     * 图片访问URL前缀
     */
    private String picsUrlPrefix = "";
    /**
     * 文件访问URL前缀
     */
    private String filesUrlPrefix = "";
    /**
     * POS API接口前缀
     */
    private String posapiUrlPrefix = "";
    /**
     * 是否验证码
     */
    private Boolean kaptchaOpen = false;
    /**
     * 是否开启Swaggr
     */
    private Boolean swaggerOpen = false;
    /**
     * session 失效时间（默认为30分钟 单位：秒）
     */
    private Integer sessionInvalidateTime = 30 * 60;
    /**
     * session 验证失效时间（默认为15分钟 单位：秒）
     */
    private Integer sessionValidationInterval = 15 * 60;
    /**
     * 机具心跳报告超时时间 单位：分钟
     */
    private Integer heartbeatTimeout;
}
