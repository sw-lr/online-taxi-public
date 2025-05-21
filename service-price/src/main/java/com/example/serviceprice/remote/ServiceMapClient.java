package com.example.serviceprice.remote;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.PriceDto;
import com.example.internalcommon.response.DirectionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ClassName: ServiceMapClient
 * Package: com.example.serviceprice.remote
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/21 15:26
 * @Version 1.0
 */
@FeignClient("service-map")
public interface ServiceMapClient {

    @RequestMapping(method = RequestMethod.GET, value = "/direction/driving")
    public ResponseResult<DirectionResponse> driving(@RequestBody PriceDto priceDto);
}
