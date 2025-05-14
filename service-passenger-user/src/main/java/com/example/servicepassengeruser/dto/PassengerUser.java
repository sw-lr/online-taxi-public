package com.example.servicepassengeruser.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * ClassName: PassengerUser
 * Package: com.example.servicepassengeruser.dto
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/12 17:33
 * @Version 1.0
 */
@Data
public class PassengerUser {
    private Long id;

    private LocalDateTime registerDate;

    private LocalDateTime gmtModified;

    private String passengerPhone;

    private String passengerName;

    // 性别 0：女 1：男 -1：未知
    private byte passengerGender;

    // 状态 0：有效 1：失效
    private byte state;

}
