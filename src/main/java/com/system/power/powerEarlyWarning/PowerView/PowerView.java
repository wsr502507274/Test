package com.system.power.powerEarlyWarning.PowerView;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ==================================================
 * fileName     : PowerView
 *
 * @author : Wang Sen Rong
 * @description :
 * @create : 2019/11/26
 * ==================================================
 */
@Getter
@Setter
@ToString
public class PowerView {
    /**公司名称*/
    private String companyName;
    /**预警状态*/
    private String companyWarningStatus;
    /**电户号和预警结果*/
    private Map userNoMap = new HashMap<>();
    /**每天预警信息*/
    private List<ViewInfo> powerWarning ;

}