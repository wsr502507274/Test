package com.system.power.powerEarlyWarning.PowerView;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ==================================================
 * fileName     : ElectricityConsumption
 *
 * @author : Wang Sen Rong
 * @description : 用电预警结果
 * @create : 2019/11/25
 * ==================================================
 */
@Getter
@Setter
@ToString
public class PowerWarningResult {

    /**
     * 序号
     */
    private String number;

    /**
     * 风险维度
     */
    private String riskDimension;

    /**
     * 预警总况
     */
    private String earlyWarningSituation;

    /**
     * 电户号预警
     */
    private String ElectricHouseholdOne;
}