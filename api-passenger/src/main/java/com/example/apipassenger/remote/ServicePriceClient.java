package com.example.apipassenger.remote;

import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.PriceDto;
import com.example.internalcommon.response.ForecastPriceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ClassName: ServicePriceClient
 * Package: com.example.apipassenger.remote
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/21 22:57
 * @Version 1.0
 */
@FeignClient("service-price")
public interface ServicePriceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/forecast-price")
    public ResponseResult<ForecastPriceResponse> forecastPrice(@RequestBody PriceDto priceDto);
}
