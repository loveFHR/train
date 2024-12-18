package com.fhr.train.batch.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * @author FHR
 * @create 2024/12/18 11:19
 */
@Data
public class CronJobDto {
     @Schema(description = "任务名称")
     private String name;
     @Schema(description = "任务分组")
     private String group;
     @Schema(description = "任务描述")
     private String description;
     @Schema(description = "cron表达式")
     private String cronExpression;
}
