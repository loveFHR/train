package com.fhr.train.common.util;

import cn.hutool.core.util.IdUtil;

/**
 * 封装Hutool雪花算法生成id
 *
 * @author FHR
 * @create 2024/12/15 23:10
 */

public class SnowUtil {
    //数据中心（实际环境应该是机器启动时去获取一个唯一id）
    private static final long DATACENTER_ID = 1;
    //机器
    private static final long WORK_ID = 1;

    public static long getSnowflake() {
        return IdUtil.getSnowflake(WORK_ID, DATACENTER_ID).nextId();
    }

    public static String getSnowflakeStr() {
        return IdUtil.getSnowflake(WORK_ID, DATACENTER_ID).nextIdStr();
    }
}
