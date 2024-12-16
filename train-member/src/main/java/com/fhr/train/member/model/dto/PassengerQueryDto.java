package com.fhr.train.member.model.dto;

import com.fhr.train.common.request.PageRequest;
import lombok.Data;

/**
 * @author FHR
 * @create 2024/12/16 19:15
 */
@Data
public class PassengerQueryDto extends PageRequest {

    private Long memberId;
}
