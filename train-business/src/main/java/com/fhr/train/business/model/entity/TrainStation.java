package com.fhr.train.business.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 火车车站
 * @TableName train_station
 */
@TableName(value ="train_station")
@Data
public class TrainStation implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 车次编号
     */
    private String trainCode;

    /**
     * 站序
     */
    private Integer index;

    /**
     * 站名
     */
    private String name;

    /**
     * 站名拼音
     */
    private String namePinyin;

    /**
     * 进站时间
     */
    private Date inTime;

    /**
     * 出站时间
     */
    private Date outTime;

    /**
     * 停站时长
     */
    private Date stopTime;

    /**
     * 里程（公里）|从上一站到本站的距离
     */
    private BigDecimal km;

    /**
     * 新增时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}