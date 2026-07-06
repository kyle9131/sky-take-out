package com.sky.interceptor;

import com.sky.constant.JwtClaimsConstant;
import com.sky.context.BaseContext;
import com.sky.properties.JwtProperties;
import com.sky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户端 jwt 令牌校验拦截器
 */
@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // 不是 Controller 方法直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 1、从请求头拿令牌
        String token = request.getHeader(jwtProperties.getUserTokenName());

        // 2、校验令牌
        try {
            log.info("用户端 jwt 校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            log.info("当前用户的id：{}", userId);
            BaseContext.setCurrentId(userId);
            // 3、通过，放行
            return true;
        } catch (Exception ex) {
            // 4、不通过，响应 401
            response.setStatus(401);
            return false;
        }
    }
}
