package com.sky.service.impl;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.mapper.UserMapper;
import com.sky.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 微信登录（办法B 简化版：跳过真实微信接口，用固定测试 openid）
     */
    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        // ★办法B：不调微信，直接用固定测试 openid
        // 正常这里要用 code 去调微信换 openid，我们跳过，写死一个测试值
        String openid = "test_openid_001";

        // 查询这个 openid 的用户是否存在
        User user = userMapper.getByOpenid(openid);

        // 如果是新用户，自动注册
        if (user == null) {
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }

        return user;
    }
}
