package com.fhr.train.member.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fhr.train.common.context.LoginMemberContext;
import com.fhr.train.common.response.PageResponse;
import com.fhr.train.common.util.SnowUtil;
import com.fhr.train.member.mapper.PassengerMapper;
import com.fhr.train.member.model.dto.PassengerQueryDto;
import com.fhr.train.member.model.dto.PassengerSaveDto;
import com.fhr.train.member.model.entity.Passenger;
import com.fhr.train.member.model.vo.PassengerQueryVo;
import com.fhr.train.member.service.PassengerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author FHR
 * @create 2024/12/16 12:07
 */
@Service
@Slf4j
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

    @Override
    public PageResponse<PassengerQueryVo> queryList(PassengerQueryDto passengerQueryDto) {

        LambdaQueryWrapper<Passenger> wrapper = new LambdaQueryWrapper<>();
        if (passengerQueryDto.getMemberId() != null) {
            wrapper.eq(Passenger::getMemberId, passengerQueryDto.getMemberId())
                    .orderByDesc(Passenger::getId);
        }
        log.info("查询页码：{}", passengerQueryDto.getPage());
        log.info("查询每页数：{}", passengerQueryDto.getSize());
        Page<Passenger> pageParam = new Page<>(passengerQueryDto.getPage(),passengerQueryDto.getSize());
        Page<Passenger> pageList = passengerMapper.selectPage(pageParam, wrapper);
        long total = pageList.getTotal();
        List<PassengerQueryVo> passengerList = BeanUtil.copyToList(pageList.getRecords(), PassengerQueryVo.class);

        return new PageResponse<>(total,passengerList);
    }
}
