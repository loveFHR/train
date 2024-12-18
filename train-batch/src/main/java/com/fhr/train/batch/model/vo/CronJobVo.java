package com.fhr.train.batch.model.vo;

/**
 * @author FHR
 * @create 2024/12/18 11:51
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CronJobVo {
    private String name;
    private String group;
    private String description;

    private String state;

    private String cronExpression;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date nextFireTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date preFireTime;
}
