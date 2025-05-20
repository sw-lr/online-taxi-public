package com.example.serviceprice.service;

import com.example.internalcommon.constant.PriceStatusEnum;
import com.example.internalcommon.dto.ResponseResult;
import com.example.internalcommon.response.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ClassName: PriceService
 * Package: com.example.serviceprice.service
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/20 19:22
 * @Version 1.0
 */
@Service
@Slf4j
public class PriceService {

    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude){

        log.info("出发地经度：" + depLongitude);
        log.info("出发地纬度：" + depLatitude);
        log.info("目的地经度：" + destLongitude);
        log.info("目的地纬度：" + destLatitude);

        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(21.35);

        log.info("调用地图服务，查询距离和价格");

        log.info("读取计价规则");

        log.info("根据距离、时长、计价规则，计算价格");

        return ResponseResult.success(PriceStatusEnum.PRICE_CALCULATED, forecastPriceResponse);
    }
}
