package com.fhr.train.batch.controller;

import com.fhr.train.batch.model.dto.CronJobDto;
import com.fhr.train.batch.model.vo.CronJobVo;
import com.fhr.train.common.response.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author FHR
 * @create 2024/12/18 11:23
 */
@RestController
@RequestMapping("admin/job")
@Tag(name = "定时任务")
public class JobController {

    private static final Logger log = LoggerFactory.getLogger(JobController.class);
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @PostMapping("add")
    @Operation(summary = "创建定时任务")
    public Result<Void> add(@RequestBody CronJobDto cronJobDto) {
        String name = cronJobDto.getName();
        String group = cronJobDto.getGroup();
        String description = cronJobDto.getDescription();
        String cronExpression = cronJobDto.getCronExpression();
        try {
            //获取调度器实例
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            //启动调度器
            scheduler.start();
            //构建job信息
            JobDetail jobDetail = JobBuilder
                    .newJob((Class<? extends Job>) Class.forName(name))
                    .withIdentity(name, group)
                    .build();
            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(name, group)
                    .withDescription(description)
                    .withSchedule(scheduleBuilder)
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.error("创建定时任务失败:", e);
            Result.error("创建定时任务失败:调度异常");
        } catch (ClassNotFoundException e) {
            log.error("创建定时任务失败:", e);
            Result.error("创建定时任务失败:任务不存在");
        }
        return Result.success();
    }

    @PostMapping(value = "pause")
    @Operation(summary = "暂停定时任务")
    public Result<Void> pause(@RequestBody CronJobDto cronJobDto) {
        String jobClassName = cronJobDto.getName();
        String jobGroupName = cronJobDto.getGroup();
        log.info("暂停定时任务开始：{}，{}", jobClassName, jobGroupName);
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            log.error("暂停定时任务失败:", e);
            Result.error("暂停定时任务失败:");
        }
        log.info("暂停定时任务结束");
        return Result.success();
    }

    @PostMapping(value = "resume")
    @Operation(summary = "重启定时任务")
    public Result<Void> resume(@RequestBody CronJobDto cronJobDto) {
        String jobClassName = cronJobDto.getName();
        String jobGroupName = cronJobDto.getGroup();
        log.info("重启定时任务开始：{}，{}", jobClassName, jobGroupName);
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            log.error("重启定时任务失败:", e);
            Result.error("重启定时任务失败");
        }
        log.info("重启定时任务结束");
        return Result.success();
    }

    @PostMapping(value = "reschedule")
    @Operation(summary = "重置定时任务")
    public Result<Void> reschedule(@RequestBody CronJobDto cronJobDto) {
        String jobClassName = cronJobDto.getName();
        String jobGroupName = cronJobDto.getGroup();
        String cronExpression = cronJobDto.getCronExpression();
        String description = cronJobDto.getDescription();
        log.info("更新定时任务开始：{}，{}，{}，{}", jobClassName, jobGroupName, cronExpression, description);
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTriggerImpl trigger1 = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
            // 重新设置开始时间
            trigger1.setStartTime(new Date());
            CronTrigger trigger = trigger1;

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withDescription(description).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (Exception e) {
            log.error("更新定时任务失败:", e);
            Result.error("更新定时任务失败:调度异常");
        }
        log.info("更新定时任务结束");
        return Result.success();
    }

    @PostMapping(value = "delete")
    @Operation(summary = "删除定时任务")
    public Result<Void> delete(@RequestBody CronJobDto cronJobDto) {
        String jobClassName = cronJobDto.getName();
        String jobGroupName = cronJobDto.getGroup();
        log.info("删除定时任务开始：{}，{}", jobClassName, jobGroupName);
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            log.error("删除定时任务失败:", e);
            Result.error("删除定时任务失败:调度异常");
        }
        log.info("删除定时任务结束");
        return Result.success();
    }

    @PostMapping(value = "run")
    @Operation(summary = "执行一次定时任务")
    public Result<Void> handle(@RequestBody CronJobDto cronJobDto) {
        String jobClassName = cronJobDto.getName();
        String jobGroupName = cronJobDto.getGroup();
        log.info("定时任务开始：{}，{}", jobClassName, jobGroupName);
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            scheduler.triggerJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            log.error("执行定时任务失败:", e);
            Result.error("执行定时任务失败:");
        }
        log.info("执行定时任务结束");
        return Result.success();
    }

    @GetMapping(value = "query")
    @Operation(summary = "查看所有定时任务开始")
    public Result<List<CronJobVo>> query() {
        log.info("查看所有定时任务开始");
        List<CronJobVo> cronJobVoList = new ArrayList<>();
        try {
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    CronJobVo cronJobVo = new CronJobVo();
                    cronJobVo.setName(jobKey.getName());
                    cronJobVo.setGroup(jobKey.getGroup());

                    //get job's trigger
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    CronTrigger cronTrigger = (CronTrigger) triggers.getFirst();
                    cronJobVo.setNextFireTime(cronTrigger.getNextFireTime());
                    cronJobVo.setPreFireTime(cronTrigger.getPreviousFireTime());
                    cronJobVo.setCronExpression(cronTrigger.getCronExpression());
                    cronJobVo.setDescription(cronTrigger.getDescription());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(cronTrigger.getKey());
                    cronJobVo.setState(triggerState.name());

                    cronJobVoList.add(cronJobVo);
                }

            }
        } catch (SchedulerException e) {
            log.error("查看定时任务失败:", e);
            Result.error("查看定时任务失败:调度异常");
        }
        log.info("查看定时任务结束");
        return Result.success(cronJobVoList);
    }

}
