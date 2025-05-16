package com.example.apipassenger.service;

import com.example.apipassenger.remote.ServicePassengerUserClient;
import com.example.internalcommon.constant.UserStatusEnum;
import com.example.internalcommon.dto.PassengerUser;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.dto.TokenResult;
import com.example.internalcommon.request.VerificationCodeDTO;
import com.example.internalcommon.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserService
 * Package: com.example.apipassenger.service
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/16 15:10
 * @Version 1.0
 */
@Service
public class UserService {

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    public ResponseResult getUserByAccessToken(String accessToken){

        // 解析accessToken
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();

        ResponseResult<PassengerUser> passengerUserResponse = servicePassengerUserClient.getUser(phone);

        PassengerUser passengerUser = passengerUserResponse.getData();

        return ResponseResult.success(UserStatusEnum.USER_INFO_RETRIEVED, passengerUser);
    }
}
