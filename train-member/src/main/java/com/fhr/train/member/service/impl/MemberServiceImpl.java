package com.fhr.train.member.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fhr.train.common.exception.BusinessException;
import com.fhr.train.common.exception.BusinessExceptionEnum;
import com.fhr.train.common.util.SnowUtil;
import com.fhr.train.member.model.dto.RegisterDto;
import com.fhr.train.member.model.entity.Member;
import com.fhr.train.member.mapper.MemberMapper;
import com.fhr.train.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author FHR
 * @create 2024/12/15 20:13
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService  {
    @Autowired
    private MemberMapper memberMapper;


    @Override
    public Long register(RegisterDto registerDto){
        String mobile = registerDto.getMobile();
        Member selectMember = memberMapper.selectOne(new LambdaQueryWrapper<Member>().eq(Member::getMobile, mobile));
        if(selectMember != null){
            //return selectMember.getId(); 这样写可以既注册也登录
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }
        Member member = new Member();
        member.setMobile(mobile);
        member.setId(SnowUtil.getSnowflake());
        memberMapper.insert(member);
        return member.getId();
    }
}
