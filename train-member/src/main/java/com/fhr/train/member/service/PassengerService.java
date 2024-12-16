package com.fhr.train.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fhr.train.common.response.PageResponse;
import com.fhr.train.member.model.dto.PassengerQueryDto;
import com.fhr.train.member.model.dto.PassengerSaveDto;
import com.fhr.train.member.model.entity.Passenger;
import com.fhr.train.member.model.vo.PassengerQueryVo;

import java.util.List;

/**
 * @author FHR
 * @create 2024/12/16 12:07
 */
public interface PassengerService extends IService<Passenger> {
    void addPassenger(PassengerSaveDto passengerSaveDto);

    PageResponse<PassengerQueryVo> queryList(PassengerQueryDto passengerQueryDto);
}
