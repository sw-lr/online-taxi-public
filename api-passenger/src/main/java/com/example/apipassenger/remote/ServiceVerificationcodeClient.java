package com.example.apipassenger.remote;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.response.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ClassName: ServiceVerificationcodeClient
 * Package: com.example.apipassenger.remote
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/4/27 18:08
 * @Version 1.0
 */
@FeignClient("service-verification")
public interface ServiceVerificationcodeClient {

    @RequestMapping(method = RequestMethod.GET, value = "/numberCode/{size}")
    ResponseResult<NumberCodeResponse> getNumberCode(@PathVariable("size") int  size);
}
