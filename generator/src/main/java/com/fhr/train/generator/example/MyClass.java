package com.fhr.train.generator.example;

import java.util.List;

public class MyClass {
    //生成实体类的名字
    private String className;

    //字段集合
    private List<Field> fieldList;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<Field> fieldList) {
        this.fieldList = fieldList;
    }

}

