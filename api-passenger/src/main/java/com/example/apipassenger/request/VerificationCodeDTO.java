package com.example.apipassenger.request;

/**
 * ClassName: VerificationCodeDTO
 * Package: com.example.apipassenger.request
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/4/25 16:14
 * @Version 1.0
 */
public class VerificationCodeDTO {

    // 乘客手机号
    private String passengerPhone;

    // 验证码
    private String verificationCode;

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
