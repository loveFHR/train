package com.fhr.train.batch.config;

import com.fhr.train.batch.job.QuartzTestJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 演示使用，实际生产环境都是控制台操作
 *
 * @author FHR
 * @create 2024/12/18 10:33
 */
//@Configuration
public class QuartzConfig {

    /**
     * 声明一个任务
     */
    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(QuartzTestJob.class)
                .withIdentity("QuartzTestJob", "test")
                .storeDurably()
                .build();
    }

    /**
     * 声明一个触发器，什么时候执行这个任务
     */
    @Bean
    public Trigger trigger() {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail())
                .withIdentity("Trigger", "trigger")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
    }
}
