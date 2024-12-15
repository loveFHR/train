package com.fhr.train.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author FHR
 * @create 2024/12/15 21:36
 */
@Data
@Schema(description = "统一返回")
public class Result<T> {
    @Schema(description = "返回状态码，200-成功,其它失败")
    private Integer code;
    @Schema(description = "返回消息")
    private String msg;
    @Schema(description = "返回数据")
    private T data;
    private Result(){}
    public static <T> Result<T> success(T data){
        Result<T> result = new Result<T>();
        result.setCode(200);
        result.msg = "success";
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(){
        return success(null);
    }
    public static <T> Result<T> error(Integer code, String msg){
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
    public static <T> Result<T> error(String msg){
        return error(500, msg);
    }
}
