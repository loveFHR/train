package com.fhr.train.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fhr.train.member.model.dto.LoginDto;
import com.fhr.train.member.model.dto.RegisterDto;
import com.fhr.train.member.model.dto.SendCodeDto;
import com.fhr.train.member.model.entity.Member;
import com.fhr.train.common.response.LoginVo;

/**
 * @author FHR
 * @create 2024/12/15 20:13
 */
public interface MemberService extends IService<Member> {
    public Long register(RegisterDto registerDto);

    void senCode(SendCodeDto sendCodeDto);

    LoginVo login(LoginDto loginDto);
}
