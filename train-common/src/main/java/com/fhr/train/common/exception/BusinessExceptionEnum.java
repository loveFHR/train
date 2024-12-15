package com.fhr.train.common.exception;


/**
 * 业务异常枚举
 *
 * @author FHR
 * @create 2024/12/15 21:55
 */

public enum BusinessExceptionEnum {

    MEMBER_MOBILE_EXIST("手机号已注册");
    private String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BusinessExceptionEnum{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
