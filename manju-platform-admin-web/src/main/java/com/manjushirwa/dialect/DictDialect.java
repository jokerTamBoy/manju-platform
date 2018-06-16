package com.manjushirwa.dialect;

import java.util.HashSet;
import java.util.Set;

import com.manjushirwa.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;
import org.thymeleaf.standard.processor.StandardXmlNsTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;

@Component
public class DictDialect extends AbstractProcessorDialect{

    private static final String NAME = "Dict Dialect";
    private static final String PREFIX = "dict";


    public DictDialect() {
        // 我们将设置此方言与“方言处理器”优先级相同
        // 标准方言, 以便处理器执行交错。
        super(NAME, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }
    /*
     *
     * 元素处理器：“dict”标签。
     */
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new DictElementProcessor(dialectPrefix));//添加我们定义的标签


        processors.add(new StandardXmlNsTagProcessor(TemplateMode.HTML, dialectPrefix));
        return processors;
    }
}