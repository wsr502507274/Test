package com.system.power.powerEarlyWarning.PowerView;

import com.system.power.powerEarlyWarning.utils.UtilDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ==================================================
 * fileName     : ViewInfo
 *
 * @author : Wang Sen Rong
 * @description :
 * @create : 2019/11/26
 * ==================================================
 */
@Getter
@Setter
@ToString
public class ViewInfo {
    /**日期*/
    private String date;
    /**用电量*/
    private String electricityConsumption;
    /**柱状图预警状态*/
    private String warningStatus;
    /**预警结果*/
    private List<PowerWarningResult> PpwerWarningResults = new ArrayList<>();

}