package com.fhr.train.member.controller;

import com.fhr.train.common.response.Result;
import com.fhr.train.member.model.dto.PassengerSaveDto;
import com.fhr.train.member.model.entity.Passenger;
import com.fhr.train.member.service.PassengerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author FHR
 * @create 2024/12/16 12:05
 */
@RestController
@RequestMapping("passenger")
@Tag(name = "乘客接口")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @PostMapping
    public Result<Void> addPassenger(@Valid @RequestBody PassengerSaveDto passengerSaveDto) {
        passengerService.addPassenger(passengerSaveDto);
        return Result.success();
    }
}
