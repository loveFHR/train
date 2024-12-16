package com.fhr.train.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fhr.train.common.context.LoginMemberContext;
import com.fhr.train.common.util.SnowUtil;
import com.fhr.train.member.mapper.PassengerMapper;
import com.fhr.train.member.model.dto.PassengerSaveDto;
import com.fhr.train.member.model.entity.Passenger;
import com.fhr.train.member.service.PassengerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author FHR
 * @create 2024/12/16 12:07
 */
@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger> implements PassengerService {
    @Autowired
    private PassengerMapper passengerMapper;

    @Override
    public void addPassenger(PassengerSaveDto passengerSaveDto) {
        Passenger passenger = new Passenger();
        BeanUtils.copyProperties(passengerSaveDto, passenger);
        passenger.setId(SnowUtil.getSnowflake());
        passenger.setMemberId(LoginMemberContext.getMemberId());
        LocalDateTime now = LocalDateTime.now();
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
}
