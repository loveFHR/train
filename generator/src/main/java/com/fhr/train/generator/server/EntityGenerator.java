//package com.fhr.train.generator.server;
//
//import com.fhr.train.generator.util.DbUtil;
//import com.fhr.train.generator.util.Field1;
//import com.fhr.train.generator.util.FreemarkerUtil;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author FHR
// * @create 2024/12/18 19:55
// */
//public class EntityGenerator {
//    public static void main(String[] args) throws Exception {
//        DbUtil.url = "jdbc:mysql://192.168.233.137:3307/train_business?serverTimezone=Asia/Shanghai";
//        DbUtil.user = "root";
//        DbUtil.password = "123456";
////        List<Field> fieldList = DbUtil.getColumnByTableName("train");
////        fieldList.forEach(System.out::println);
////        FreemarkerUtil.initConfig("entity.ftl");
////        String toPath = "./";
////        new File(toPath).mkdirs();
////        Map<String,Object> map = new HashMap<>();
////        map.put("tableName","train");
////        FreemarkerUtil.generator(toPath + "train.java",map);
//    }
//}
