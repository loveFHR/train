package com.fhr.train.member.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fhr.train.common.response.PageResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author FHR
 * @create 2024/12/16 19:17
 */
@Data
public class PassengerQueryVo {
    private Long id;
    private Long memberId;
    private String name;
    private String idCard;
    @Schema(description = "旅客类型")
    private String type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private LocalDateTime updateTime;
}