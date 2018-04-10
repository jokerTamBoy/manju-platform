package com.manjushira.common.web;

import org.springframework.beans.factory.annotation.Value;

/**
 * @Author: Ciaoyen
 */
public abstract class BaseController {

    /**
     * 管理基础路径
     */
    @Value("${adminPath}")
    protected String adminPath;
}
