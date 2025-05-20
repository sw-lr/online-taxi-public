package com.example.apipassenger.service;

import com.example.internalcommon.constant.PriceStatusEnum;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.response.ForecasePriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public ResponseResult forecasePrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        log.info("出发地经度：" + depLongitude);
        log.info("出发地纬度：" + depLatitude);
        log.info("目的地经度：" + destLongitude);
        log.info("目的地纬度：" + destLatitude);

        ForecasePriceResponse forecasePriceResponse = new ForecasePriceResponse();
        forecasePriceResponse.setPrice(21.35);


        return ResponseResult.success(PriceStatusEnum.PRICE_CALCULATED, forecasePriceResponse);
    }
}
