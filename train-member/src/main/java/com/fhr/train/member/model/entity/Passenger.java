package com.fhr.train.member.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author FHR
 * @create 2024/12/16 12:02
 */
@Data
@Schema(description = "乘客表")
public class Passenger {
    private Long id;
    private Long memberId;
    private String name;
    private String idCard;
    @Schema(description = "旅客类型")
    private String type;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
