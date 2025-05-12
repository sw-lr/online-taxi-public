package com.example.servicepassengeruser.service;

import com.example.internalcommon.constant.UserStatusEnum;
import com.example.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserService
 * Package: com.example.servicepassengeruser.service
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/12 16:09
 * @Version 1.0
 */
@Service
public class UserService {
    public ResponseResult loginOrRegister(String passengerPhone){
        System.out.println("userService" + passengerPhone);
        // 根绝手机号查询用户是否存在

        // 用户不存在就注册

        return ResponseResult.success(UserStatusEnum.LOGIN_SUCCESS);
    }
}
