package com.fhr.train.common.response;

import lombok.Data;

/**
 * @author FHR
 * @create 2024/12/16 1:52
 */
@Data
public class LoginVo {
    private Long id;
    private String mobile;
    private String token;
}
