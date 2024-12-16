package com.fhr.train.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author FHR
 * @create 2024/12/16 22:24
 */
@Data
@AllArgsConstructor
public class PageResponse<T> {
    @Schema(description = "总条数")
    private Long total;
    @Schema(description = "数据列表")
    private List<T> list;

}
