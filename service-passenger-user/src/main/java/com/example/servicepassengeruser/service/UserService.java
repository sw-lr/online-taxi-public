package com.example.servicepassengeruser.service;

import com.example.internalcommon.constant.UserStatusEnum;
import com.example.internalcommon.dto.ResponseResult;
import com.example.servicepassengeruser.dto.PassengerUser;
import com.example.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private PassengerUserMapper passengerUserMapper;

    public ResponseResult loginOrRegister(String passengerPhone){
        // 根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers.size() == 0 ? "无记录" : passengerUsers.get(0).getPassengerName());
        // 根绝手机号查询用户是否存在

        // 用户不存在就注册

        return ResponseResult.success(UserStatusEnum.LOGIN_SUCCESS);
    }
}
