package com.fhr.train.common.interceptor;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fhr.train.common.context.LoginMemberContext;
import com.fhr.train.common.response.LoginVo;
import com.fhr.train.common.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author FHR
 * @create 2024/12/16 13:52
 */
@Component
public class MemberInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(MemberInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取header的token参数
        String token = request.getHeader("Authorization");
        if (StringUtils.hasLength(token)) {
            log.info("获取会员登录token：{}", token);
            JSONObject loginMember = JwtUtil.getJSONObject(token);
            log.info("当前登录会员：{}", loginMember);
            LoginVo member = JSONUtil.toBean(loginMember, LoginVo.class);
            LoginMemberContext.setMemberInfo(member);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginMemberContext.removeLoginInfo();
    }
}
