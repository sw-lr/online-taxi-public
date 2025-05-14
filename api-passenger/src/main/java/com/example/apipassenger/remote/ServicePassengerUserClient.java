package com.example.apipassenger.remote;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.VerificationCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ClassName: ServicePassengerUserClient
 * Package: com.example.apipassenger.remote
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/13 16:05
 * @Version 1.0
 */
@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO);
}
