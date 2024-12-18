package com.fhr.train.member.controller;

import com.fhr.train.common.context.LoginMemberContext;
import com.fhr.train.common.response.PageResponse;
import com.fhr.train.common.response.Result;
import com.fhr.train.member.model.dto.PassengerQueryDto;
import com.fhr.train.member.model.dto.PassengerSaveDto;
import com.fhr.train.member.model.entity.Passenger;
import com.fhr.train.member.model.vo.PassengerQueryVo;
import com.fhr.train.member.service.PassengerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping
    public Result<Void> modifyPassenger(@Valid @RequestBody PassengerSaveDto passengerSaveDto) {
        passengerService.modifyPassenger(passengerSaveDto);
        return Result.success();
    }

    @GetMapping("query/list")
    public Result<PageResponse<PassengerQueryVo>> queryList(@Valid PassengerQueryDto passengerQueryDto) {
        passengerQueryDto.setMemberId(LoginMemberContext.getMemberId());
        PageResponse<PassengerQueryVo> res = passengerService.queryList(passengerQueryDto);
        return Result.success(res);
    }

    @DeleteMapping("{id}")
    public Result<Void> deletePassenger(@PathVariable("id") Long id) {
        passengerService.removeById(id);
        return Result.success();
    }
}
