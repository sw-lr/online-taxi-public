package com.example.internalcommon.dto;

import lombok.Data;

/**
 * ClassName: PriceRuleMapper
 * Package: com.example.internalcommon.dto
 * Description:
 *
 * @Author 杨思旺
 * @Create 2025/5/21 16:44
 * @Version 1.0
 */
@Data
public class PriceRule {

    private String cityCode;

    private String vehicleType;

    private Double startFare;

    private Integer startMile;

    private Double unitPricePerMile;

    private Double unitPricePerMinute;
}
