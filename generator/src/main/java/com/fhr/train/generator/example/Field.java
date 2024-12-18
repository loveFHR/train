package com.fhr.train.generator.example;
//属性名
public class Field {
	//字段名
    private String fieldName;

	//字段类型
    private String fieldType;
    
	//字段注解
    private String fieldRemarks;
	
	//字段名字母大写
    private String fieldNameUpperFirstLetter;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldRemarks() {
        return fieldRemarks;
    }

    public void setFieldRemarks(String fieldRemarks) {
        this.fieldRemarks = fieldRemarks;
    }

    public String getFieldNameUpperFirstLetter() {
        return fieldNameUpperFirstLetter;
    }

    public void setFieldNameUpperFirstLetter(String fieldNameUpperFirstLetter) {
        this.fieldNameUpperFirstLetter = fieldNameUpperFirstLetter;
    }

    @Override
    public String toString() {
        return "Field{" + "fieldName='" + fieldName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", fieldRemarks='" + fieldRemarks + '\'' +
                ", fieldNameUpperFirstLetter='" + fieldNameUpperFirstLetter + '\'' +
                '}';
    }
}

