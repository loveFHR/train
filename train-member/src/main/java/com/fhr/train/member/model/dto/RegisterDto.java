package com.fhr.train.member.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 注册前端提交的参数
 *
 * @author FHR
 * @create 2024/12/15 21:30
 */
@Data
public class RegisterDto {
    @NotBlank(message = "手机号不能为空")
    private String mobile;
}
