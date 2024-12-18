package com.fhr.train.batch.job;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * spring boot自带的定时任务
 * 1、只适合单体，不适合集群
 * 当然如果想用，可以加分布式锁
 * 2、不能实时更改定时任务状态和策略
 * 这种适合单体简单的任务
 *
 * @author FHR
 * @create 2024/12/18 10:19
 */
@Component
@EnableScheduling
public class SpringBootTestJob {

    @Scheduled(cron = "0/5 * * * * ?")
    public void scheduledJob() {
        System.out.println("SpringBootTestJob scheduled test");
    }
}
