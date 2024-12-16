package com.fhr.train.common.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

/**
 * @author FHR
 * @create 2024/12/16 19:37
 */
@Data
@Schema(description = "分页查询参数")
public class PageRequest {
    @Schema(description = "页码")
    @NotNull(message = "【页码】不能为空")
    private Long page;
    @Schema(description = "每页条数")
    @NotNull(message = "【每页条数】不能为空")
    @Max(value = 100,message = "【每页条数】不能超过100条")
    private Long size;
}
