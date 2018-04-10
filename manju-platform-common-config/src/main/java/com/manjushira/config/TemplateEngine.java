package com.manjushira.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring4.SpringTemplateEngine;

/**
 * Created by Ciaoyen on 12/03/2018.
 */
public class TemplateEngine {

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }

}
