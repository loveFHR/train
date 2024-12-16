package com.fhr.train.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fhr.train.member.model.dto.PassengerSaveDto;
import com.fhr.train.member.model.entity.Passenger;

/**
 * @author FHR
 * @create 2024/12/16 12:07
 */
public interface PassengerService extends IService<Passenger> {
    void addPassenger(PassengerSaveDto passengerSaveDto);
}
