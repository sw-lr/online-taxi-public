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

    private double startFare;

    private int startMile;

    private double unitPricePerMile;

    private double unitPricePerMinute;
}
