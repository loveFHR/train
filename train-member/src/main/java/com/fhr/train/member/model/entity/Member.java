package com.fhr.train.member.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * @author FHR
 * @create 2024/12/15 20:43
 */
@Data
@ToString
@TableName("train_member")
public class Member {
    @TableId(type = IdType.AUTO)
    @Schema(description = "")
    private Long id;
    private String mobile;
}
