package com.example.apipassenger.service;

import com.example.apipassenger.remote.ServicePriceClient;
import com.example.internalcommon.constant.PriceStatusEnum;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.request.PriceDto;
import com.example.internalcommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: PriceService
 * Package: com.example.apipassenger.service
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/20 15:43
 * @Version 1.0
 */
@Service
@Slf4j
public class PriceService {

    @Autowired
    private ServicePriceClient servicePriceClient;

    public ResponseResult forecastPrice(@RequestBody PriceDto priceDto){

        log.info("调用计价服务，获取价格");
        return servicePriceClient.forecastPrice(priceDto);
    }
}
