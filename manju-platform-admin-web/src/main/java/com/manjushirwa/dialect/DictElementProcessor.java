package com.manjushirwa.dialect;

import com.manjushirwa.pojo.admin.po.Dict;
import com.manjushirwa.service.DictService;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring4.context.SpringContextUtils;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DictElementProcessor extends AbstractElementTagProcessor {

    private static final String TAG_NAME  = "select";//标签名
    private static final int PRECEDENCE = 10000;//优先级

    public DictElementProcessor(String dialectPrefix) {

        super(
                TemplateMode.HTML, // 此处理器将仅应用于HTML模式
                dialectPrefix,     // 要应用于名称的匹配前缀
                TAG_NAME,          // 标签名称：匹配此名称的特定标签
                true,              // 将标签前缀应用于标签名称
                null,              // 无属性名称：将通过标签名称匹配
                false,             // 没有要应用于属性名称的前缀
                PRECEDENCE);       // 优先(内部方言自己的优先)
    }

    @Override
    protected void doProcess(
            final ITemplateContext context,
            final IProcessableElementTag tag,
            final IElementTagStructureHandler structureHandler) {
        /**
         * 获取应用程序上下文。
         */
        ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);

        DictService dictService = appCtx.getBean(DictService.class);


        final String  type= tag.getAttributeValue("type");
        final String  cls= tag.getAttributeValue("class");
        final String  id= tag.getAttributeValue("id");
        final String  name= tag.getAttributeValue("name");
        final String  option= tag.getAttributeValue("option");

        Dict dict = new Dict();
        dict.setType(type);
        dict.setValue("0");

        List<Dict> list = null;
        try {
            list = dictService.selectDictTranslates(dict);;

        }catch (Exception ex){
            ex.printStackTrace();

        }


        final IModelFactory modelFactory = context.getModelFactory();

        final IModel model = modelFactory.createModel();

        if(list == null){
            model.add(modelFactory.createText(""));
        }else{
            StringBuilder sb = new StringBuilder();
            if (option != null && option.equals("true")){
                sb.append("<select");
                if (cls != null)
                    sb.append(" class='"+cls+"'");
                if (id != null)
                    sb.append(" id='"+id+"'");
                if (name != null)
                    sb.append(" name='"+name+"'");
                sb.append(">");
            }
            for (Dict item:list) {
                sb.append("<option value='"+item.getValue()+"'>"+item.getLabel()+"</option>");
            }
            if (option != null && option.equals("true")){
                sb.append("</select>");
            }
            model.add(modelFactory.createText(sb.toString()));
        }


//        model.add(modelFactory.createOpenElementTag("div"));
//        model.add(modelFactory.createText(logoimgText));
//        model.add(modelFactory.createCloseElementTag("div"));
        structureHandler.replaceWith(model, false);





    }


}
