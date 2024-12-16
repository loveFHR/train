package com.fhr.train.member.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author FHR
 * @create 2024/12/16 12:02
 */
@Data
@Schema(description = "乘客新增表")
public class PassengerSaveDto {
    private Long id;
    @NotNull(message = "【会员id】不能为空")
    private Long memberId;
    @NotBlank(message = "【姓名】不能为空")
    private String name;
    @NotBlank(message = "【身份证号】不能为空")
    private String idCard;
    @Schema(description = "旅客类型")
    @NotBlank(message = "【旅客类型】不能为空")
    private String type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private LocalDateTime updateTime;
}
