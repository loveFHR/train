package com.fhr.train.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fhr.train.common.exception.BusinessException;
import com.fhr.train.common.exception.BusinessExceptionEnum;
import com.fhr.train.common.util.JwtUtil;
import com.fhr.train.common.util.SnowUtil;
import com.fhr.train.member.model.dto.LoginDto;
import com.fhr.train.member.model.dto.RegisterDto;
import com.fhr.train.member.model.dto.SendCodeDto;
import com.fhr.train.member.model.entity.Member;
import com.fhr.train.member.mapper.MemberMapper;
import com.fhr.train.common.response.LoginVo;
import com.fhr.train.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author FHR
 * @create 2024/12/15 20:13
 */
@Service
@Slf4j
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Autowired
    private MemberMapper memberMapper;


    @Override
    public Long register(RegisterDto registerDto) {
        String mobile = registerDto.getMobile();
        Member selectMember = memberMapper.selectOne(new LambdaQueryWrapper<Member>().eq(Member::getMobile, mobile));
        if (selectMember != null) {
            //return selectMember.getId(); 这样写可以既注册也登录
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }
        Member member = new Member();
        member.setMobile(mobile);
        member.setId(SnowUtil.getSnowflake());
        memberMapper.insert(member);
        return member.getId();
    }

    @Override
    public void senCode(SendCodeDto sendCodeDto) {
//        String mobile = sendCodeDto.getMobile();
//        Member selectMember = memberMapper.selectOne(new LambdaQueryWrapper<Member>().eq(Member::getMobile, mobile));
//        //手机号没有注册过,插入数据库
//        if (selectMember == null) {
//            Member member = new Member();
//            member.setMobile(mobile);
//            member.setId(SnowUtil.getSnowflake());
//            memberMapper.insert(member);
//        }
        //生成验证码
        //int code = RandomUtil.randomInt(100000, 1000000);
        String code = "123456";
        log.info("验证码：{}", code);
        //TODO 保存到redis

        //TODO 发送短信
    }

    @Override
    public LoginVo login(LoginDto loginDto) {
        String mobile = loginDto.getMobile();
        String code = loginDto.getCode();
        //TODO 从redis查验证码
        if (!"123456".equals(code)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }
        Member selectMember = memberMapper.selectOne(new LambdaQueryWrapper<Member>().eq(Member::getMobile, mobile));
        //未注册，插入数据
        if (selectMember == null) {
            Member member = new Member();
            member.setMobile(mobile);
            member.setId(SnowUtil.getSnowflake());
            memberMapper.insert(member);
            LoginVo loginVo = new LoginVo();
            BeanUtils.copyProperties(member,loginVo);
            return loginVo;
        }else { //登录
            LoginVo loginVo = new LoginVo();
            BeanUtils.copyProperties(selectMember,loginVo);
            String token = JwtUtil.createToken(selectMember.getId(),selectMember.getMobile());
            loginVo.setToken(token);
            return loginVo;
        }
    }
}
