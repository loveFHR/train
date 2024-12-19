package com.fhr.train.generator.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author FHR
 * @create 2024/12/18 20:22
 */
public class StartGenerator {
    private static String TEMPLATE_PATH = "generator/src/main/java/com/fhr/train/generator/ftl/";
    public static void main(String[] args) throws IOException, SQLException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_33);
        configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
        Template template = configuration.getTemplate("entity.ftl");
        System.out.println("解析entity.ftl");
        //数据
        Map<String,Object> data = new HashMap<>();
        ClassGenerator classGenerator = new ClassGenerator();
        MyClass myClass = classGenerator.generateClass("train_business","train");
        data.put("packageName","com.fhr");
        data.put("myClass",myClass);

        Writer writer = new OutputStreamWriter(new FileOutputStream("./"+myClass.getClassName()+".java"));
        template.process(data,writer);
        System.out.println("finish");

    }
}
