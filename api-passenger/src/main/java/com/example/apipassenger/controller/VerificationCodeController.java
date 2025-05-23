package com.example.apipassenger.controller;

import com.example.apipassenger.service.VerificationCodeService;
import com.example.internalcommon.constant.CommonStatusEnum;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.VerificationCodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: VerificationCodeController
 * Package: com.example.apipassenger.controller
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/4/25 16:12
 * @Version 1.0
 */
@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    /**
     * 乘客获取验证码
     * @param verificationCodeDTO 手机号
     * @return
     */
    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        // 获取验证码
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        return verificationCodeService.generatorCode(passengerPhone);
    }

    /**
     * 校验验证码
     * @param verificationCodeDTO
     * @return
     */
    @PostMapping("/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();

        return verificationCodeService.checkCode(passengerPhone, verificationCode);
    }

}
