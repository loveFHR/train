package com.fhr.train.member.controller;

import com.fhr.train.common.response.Result;
import com.fhr.train.member.model.dto.LoginDto;
import com.fhr.train.member.model.dto.RegisterDto;
import com.fhr.train.member.model.dto.SendCodeDto;
import com.fhr.train.member.model.vo.LoginVo;
import com.fhr.train.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FHR
 * @create 2024/12/15 20:14
 */
@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("register")
    public Result<Long> register(@RequestBody @Valid RegisterDto registerDto) {
        Long id = memberService.register(registerDto);
        return Result.success(id);
    }

    @PostMapping("send/code")
    public Result<Void> sendCode(@RequestBody @Valid SendCodeDto sendCodeDto){
        memberService.senCode(sendCodeDto);
        return Result.success();
    }

    @PostMapping("login")
    public Result<LoginVo> login(@RequestBody @Valid LoginDto loginDto){
        LoginVo loginVo = memberService.login(loginDto);
        return Result.success(loginVo);
    }
}
