package com.fhr.train.common.context;

import com.fhr.train.common.response.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author FHR
 * @create 2024/12/16 13:42
 */
public class LoginMemberContext {
    private static final Logger log = LoggerFactory.getLogger(LoginMemberContext.class);
    private static ThreadLocal<LoginVo> loginInfo = new ThreadLocal<>();
    public static Long getMemberId() {
        Long id = null;
        try {
            id = loginInfo.get().getId();
        } catch (Exception e) {
            log.error("获取会员id异常");
            throw e;
        }
        return id;
    }
    public static void setMemberInfo(LoginVo loginVo) {
        loginInfo.set(loginVo);
    }
    public static void removeLoginInfo() {
        loginInfo.remove();
    }
    public static LoginVo getLoginInfo() {
        return loginInfo.get();
    }
}
