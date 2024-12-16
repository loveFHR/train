package com.fhr.train.common.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author FHR
 * @create 2024/12/16 1:52
 */
@Data
public class LoginVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String mobile;
    private String token;
}
