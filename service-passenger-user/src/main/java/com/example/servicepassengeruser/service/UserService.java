package com.example.servicepassengeruser.service;

import com.example.internalcommon.constant.UserStatusEnum;
import com.example.internalcommon.dto.PassengerUser;
import com.example.internalcommon.dto.ResponseResult;
import com.example.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

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

    /**
     * 随机生成乘客的称谓
     * @return  示例：伴途乘清风#A3B9 （最长24字符）
     */
    private String generatePassengerName(){
        String[] NATURE_WORDS = {"清风", "星海", "云影", "晨光"};
        // 随机自然词
        String nature = NATURE_WORDS[ThreadLocalRandom.current().nextInt(NATURE_WORDS.length)];

        // 生成四位紧凑编码
        long count = new AtomicLong(System.currentTimeMillis() % 100000).getAndIncrement();
        String code = String.format("%s%02d",
                (char)('A' + count % 26),   // 首字母
                count % 100                 // 两位数字
        );

        String name = "伴途乘#" + nature + code;

        return name.length() <= 24 ? name : name.substring(0,24);
    }

    public ResponseResult loginOrRegister(String passengerPhone){
        // 根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        // 根绝手机号查询用户是否存在
        if (passengerUsers.size() == 0){
            // 用户不存在就注册
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName(generatePassengerName());
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setPassengerGender((byte) -1);
            passengerUser.setState((byte) 0);

            LocalDateTime now = LocalDateTime.now();
            passengerUser.setRegisterDate(now);
            passengerUser.setGmtModified(now);

            int affectedRows = passengerUserMapper.insert(passengerUser);
            if (affectedRows == 1 && passengerUser.getId() != null){
                return ResponseResult.success(UserStatusEnum.USER_CREATED);
            }else{
                return ResponseResult.fail(UserStatusEnum.USER_REGISTRATION_FAILED);
            }
        }


        return ResponseResult.success(UserStatusEnum.LOGIN_SUCCESS);
    }

    /**
     * 根据手机号查询用户信息
     * @param passengerPhone
     * @return
     */
    public ResponseResult getUser(String passengerPhone){
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);

        if (passengerUsers.size() == 0){
            return ResponseResult.fail(UserStatusEnum.USER_NOT_FOUND);
        }

        PassengerUser passengerUser = passengerUsers.get(0);

        return ResponseResult.success(UserStatusEnum.USER_INFO_RETRIEVED, passengerUser);
    }
}
