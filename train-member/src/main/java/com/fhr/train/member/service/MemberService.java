package com.fhr.train.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fhr.train.member.model.dto.RegisterDto;
import com.fhr.train.member.model.entity.Member;

/**
 * @author FHR
 * @create 2024/12/15 20:13
 */
public interface MemberService extends IService<Member> {
    public Long register(RegisterDto registerDto);
}
