package com.fhr.train.batch.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author FHR
 * @create 2024/12/18 10:31
 */
//禁止任务并发执行
//@DisallowConcurrentExecution
public class QuartzTestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("QuartzTestJob test");
    }
}
