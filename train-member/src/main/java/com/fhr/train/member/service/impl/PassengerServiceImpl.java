package com.fhr.train.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fhr.train.member.mapper.PassengerMapper;
import com.fhr.train.member.model.entity.Passenger;
import com.fhr.train.member.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author FHR
 * @create 2024/12/16 12:07
 */
@Service
public class PassengerServiceImpl extends ServiceImpl<PassengerMapper, Passenger> implements PassengerService {
    @Autowired
    private PassengerMapper passengerMapper;
}
