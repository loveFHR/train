package com.fhr.train.generator.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassGenerator {
    public DatabaseMetaData init(String database) {
        DatabaseMetaData databaseMetaData = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://192.168.233.137:3307/" + database + "?serverTimezone=Asia/Shanghai";
            Connection connection = DriverManager.getConnection(url, "root", "123456");
            databaseMetaData = connection.getMetaData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseMetaData;
    }

    //根据表名生成类
    public MyClass generateClass(String database, String tableName) throws SQLException {
        MyClass myClass = new MyClass();
        List<Field> fieldList = new ArrayList<>();
        DatabaseMetaData databaseMetaData = init(database);
        ResultSet resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
        while (resultSet.next()) {
            String columnName = resultSet.getString("COLUMN_NAME");
            String columnType = resultSet.getString("TYPE_NAME");
            String columnRemarks = resultSet.getString("REMARKS");

            Field field = new Field();
            field.setFieldName(columnName);
            field.setFieldType(dbTypeToJavaType(columnType));
            field.setFieldRemarks(columnRemarks);
            field.setFieldNameUpperFirstLetter(firstLetterToUpperCase(columnName));
            System.out.println(field);
            fieldList.add(field);
        }
        myClass.setClassName(tableNameToClassName(tableName));
        myClass.setFieldList(fieldList);
        return myClass;
    }

    //数据库类型转java类型
    private String dbTypeToJavaType(String dbType) {
        String javaType = null;
        switch (dbType) {
            case "VARCHAR":
                javaType = "String";
                break;
            case "INT":
                javaType = "Integer";
                break;
            case "CHAR":
                javaType = "String";
                break;
            case "DATE":
                javaType = "Date";
                break;
            case "TIME":
                javaType = "Date";
                break;
            case "TIMESTAMP":
                javaType = "Date";
                break;
            case "BIGINT":
                javaType = "Long";
                break;
            case "TEXT":
                javaType = "String";
                break;
            case "DATETIME":
                javaType = "Date";
                break;
            case "DECIMAL":
                javaType = "Date";
                break;
        }
        return javaType;
    }

    //首字母变大写
    private String firstLetterToUpperCase(String src) {
        return src.substring(0, 1).toUpperCase() + src.substring(1);
    }

    //表名转类名 db_student_hello
    private String tableNameToClassName(String tableName) {
        StringBuilder sb = new StringBuilder();
        String[] items = tableName.split("_");
        for (String item : items) {
            sb.append(firstLetterToUpperCase(item));
        }
        return sb.toString();
    }
}

