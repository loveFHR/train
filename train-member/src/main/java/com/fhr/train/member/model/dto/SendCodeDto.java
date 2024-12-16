package com.fhr.train.member.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author FHR
 * @create 2024/12/16 1:17
 */
@Data
public class SendCodeDto {
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1\\d{10}$",message = "手机号格式错误")
    private String mobile;
}
