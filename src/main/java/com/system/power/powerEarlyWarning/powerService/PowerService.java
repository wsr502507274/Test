package com.system.power.powerEarlyWarning.powerService;

import com.system.power.powerEarlyWarning.PowerView.PowerWarningResult;
import com.system.power.powerEarlyWarning.PowerView.ViewResult;
import com.system.power.powerEarlyWarning.powerInfo.PowerWarningInfo;
import com.system.power.powerEarlyWarning.utils.UtilDate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ==================================================
 * fileName     : PowerService
 *
 * @author : Wang Sen Rong
 * @description : 电力服务类
 * @create : 2019/11/27
 * ==================================================
 */
@Service
public class PowerService {


    /**
     * ========================================
     *
     * @Author: Wang Sen Rong
     * @Description: 获取60天用电信息
     * @create: 2019/11/26 18:06
     * ========================================
     */
    public ViewResult getPowerInfoResult(String end, Map<String, PowerWarningInfo> powerWarningInfos, ViewResult viewResult) {
        List<Object> list = new ArrayList<>();
        Date endDate = DateUtils.addDays(UtilDate.parseDate2(end), -59);
        //结束时间
        String stringKey = "";
        for (int i = 0; i < 60; i++) {
            Map<String, Object> map = new HashMap<>();
            Date date = DateUtils.addDays(endDate, i);
            stringKey = UtilDate.parseString(date);
            //根据日期获取当天所有数据
            PowerWarningInfo powerWarningInfo = powerWarningInfos.get(stringKey);
            if (powerWarningInfo != null) {
                //时间
                map.put("day", stringKey);
                //日用电量
                String freezeElecDay = powerWarningInfo.getFREEZE_ELEC_DAY();
                if (StringUtils.isBlank(freezeElecDay) || "nan".equals(freezeElecDay)) {
                    //如果为空显示0
                    freezeElecDay = "0";
                }
                map.put("electricityConsumption", freezeElecDay);
                //预警状态
                map.put("warningSignal", powerWarningInfo.getWARNING_SIGNAL());
                //当天预警异常结果信息
                List<PowerWarningResult> warningInfoList = getWarningInfoList(powerWarningInfo);
                map.put("dayWarningInfoList", warningInfoList);
                list.add(map);
            }
        }
        viewResult.setWarningList(list);
        return viewResult;
    }

    /**
     * ========================================
     *
     * @Author: Wang Sen Rong
     * @Description: 获取预警结果
     * @create: 2019/11/27 10:07
     * ========================================
     */
    public List<PowerWarningResult> getWarningInfoList(PowerWarningInfo powerWarningInfo) {
        List<PowerWarningResult> list = new ArrayList<>();
        PowerWarningResult result1 = new PowerWarningResult();
        result1.setNumber("1");
        result1.setRiskDimension("日冻结电量数据异常变动");
        result1.setEarlyWarningSituation(powerWarningInfo.getDQ_30D_ABNOMAL_RATIO_CHEAK());
        result1.setElectricHouseholdOne(powerWarningInfo.getDQ_30D_ABNOMAL_RATIO_CHEAK());
        list.add(result1);
        PowerWarningResult result2 = new PowerWarningResult();
        result2.setNumber("2");
        result2.setRiskDimension("近5日用电量的异常变动");
        result2.setEarlyWarningSituation(powerWarningInfo.getPD_5D_POWER_ABNORMAL_CHEAK());
        result2.setElectricHouseholdOne(powerWarningInfo.getPD_5D_POWER_ABNORMAL_CHEAK());
        list.add(result2);
        PowerWarningResult result3 = new PowerWarningResult();
        result3.setNumber("3");
        result3.setRiskDimension("近10日用电量的异常变动");
        result3.setEarlyWarningSituation(powerWarningInfo.getPD_10D_POWER_ABNORMAL_CHEAK());
        result3.setElectricHouseholdOne(powerWarningInfo.getPD_10D_POWER_ABNORMAL_CHEAK());
        list.add(result3);
        PowerWarningResult result4 = new PowerWarningResult();
        result4.setNumber("4");
        result4.setRiskDimension("近15日用电量的异常变动");
        result4.setEarlyWarningSituation(powerWarningInfo.getPD_15D_POWER_ABNORMAL_CHEAK());
        result4.setElectricHouseholdOne(powerWarningInfo.getPD_15D_POWER_ABNORMAL_CHEAK());
        list.add(result4);
        PowerWarningResult result5 = new PowerWarningResult();
        result5.setNumber("5");
        result5.setRiskDimension("当日用电量的异常变动");
        result5.setEarlyWarningSituation(powerWarningInfo.getPD_1D_POWER_ABNORMAL_CHEAK());
        result5.setElectricHouseholdOne(powerWarningInfo.getPD_1D_POWER_ABNORMAL_CHEAK());
        list.add(result5);
        PowerWarningResult result6 = new PowerWarningResult();
        result6.setNumber("6");
        result6.setRiskDimension("月用电量异常变动");
        result6.setEarlyWarningSituation(powerWarningInfo.getPD_1M_POWER_ABNORMAL_CHEAK());
        result6.setElectricHouseholdOne(powerWarningInfo.getPD_1M_POWER_ABNORMAL_CHEAK());
        list.add(result6);
        PowerWarningResult result7 = new PowerWarningResult();
        result7.setNumber("7");
        result7.setRiskDimension("季度用电量异常变动");
        result7.setEarlyWarningSituation(powerWarningInfo.getPD_3M_POWER_ABNORMAL_CHEAK());
        result7.setElectricHouseholdOne(powerWarningInfo.getPD_3M_POWER_ABNORMAL_CHEAK());
        list.add(result7);
        PowerWarningResult result8 = new PowerWarningResult();
        result8.setNumber("8");
        result8.setRiskDimension("半年度用电量异常变动");
        result8.setEarlyWarningSituation(powerWarningInfo.getPD_6M_POWER_ABNORMAL_CHEAK());
        result8.setElectricHouseholdOne(powerWarningInfo.getPD_6M_POWER_ABNORMAL_CHEAK());
        list.add(result8);
        PowerWarningResult result9 = new PowerWarningResult();
        result9.setNumber("9");
        result9.setRiskDimension("用户状态是否异常");
        result9.setEarlyWarningSituation(powerWarningInfo.getEB_USER_STATUS_CHEAK());
        result9.setElectricHouseholdOne(powerWarningInfo.getEB_USER_STATUS_CHEAK());
        list.add(result9);
        PowerWarningResult result10 = new PowerWarningResult();
        result10.setNumber("10");
        result10.setRiskDimension("近1个月违法窃电次数");
        result10.setEarlyWarningSituation(powerWarningInfo.getEB_1M_ILLEGAL_STEALING_CHEAK());
        result10.setElectricHouseholdOne(powerWarningInfo.getEB_1M_ILLEGAL_STEALING_CHEAK());
        list.add(result10);
        PowerWarningResult result11 = new PowerWarningResult();
        result11.setNumber("11");
        result11.setRiskDimension("近3个月违法窃电次数");
        result11.setEarlyWarningSituation(powerWarningInfo.getEB_3M_ILLEGAL_STEALING_CHEAK());
        result11.setElectricHouseholdOne(powerWarningInfo.getEB_3M_ILLEGAL_STEALING_CHEAK());
        list.add(result11);
        PowerWarningResult result12 = new PowerWarningResult();
        result12.setNumber("12");
        result12.setRiskDimension("近1个月违法用电次数");
        result12.setEarlyWarningSituation(powerWarningInfo.getEB_1M_ILLEGAL_USE_CHEAK());
        result12.setElectricHouseholdOne(powerWarningInfo.getEB_1M_ILLEGAL_USE_CHEAK());
        list.add(result12);
        PowerWarningResult result13 = new PowerWarningResult();
        result13.setNumber("13");
        result13.setRiskDimension("近3个月违法用电次数");
        result13.setEarlyWarningSituation(powerWarningInfo.getEB_3M_ILLEGAL_USE_CHEAK());
        result13.setElectricHouseholdOne(powerWarningInfo.getEB_3M_ILLEGAL_USE_CHEAK());
        list.add(result13);
        PowerWarningResult result14 = new PowerWarningResult();
        result14.setNumber("14");
        result14.setRiskDimension("电费结清标志");
        result14.setEarlyWarningSituation(powerWarningInfo.getPB_FEE_SETTLEMENT_CHEAK());
        result14.setElectricHouseholdOne(powerWarningInfo.getPB_FEE_SETTLEMENT_CHEAK());
        list.add(result14);
        PowerWarningResult result15 = new PowerWarningResult();
        result15.setNumber("15");
        result15.setRiskDimension("近1个月逾期缴费次数");
        result15.setEarlyWarningSituation(powerWarningInfo.getPB_1M_OVERDUE_PAYMENT_CHEAK());
        result15.setElectricHouseholdOne(powerWarningInfo.getPB_1M_OVERDUE_PAYMENT_CHEAK());
        list.add(result15);
        PowerWarningResult result16 = new PowerWarningResult();
        result16.setNumber("16");
        result16.setRiskDimension("近3个月逾期缴费次数");
        result16.setEarlyWarningSituation(powerWarningInfo.getPB_3M_OVERDUE_PAYMENT_CHEAK());
        result16.setElectricHouseholdOne(powerWarningInfo.getPB_3M_OVERDUE_PAYMENT_CHEAK());
        list.add(result16);
        PowerWarningResult result17 = new PowerWarningResult();
        result17.setNumber("17");
        result17.setRiskDimension("最大逾期时长");
        result17.setEarlyWarningSituation(powerWarningInfo.getPB_MAX_OVERDUE_TIME_CHEAK());
        result17.setElectricHouseholdOne(powerWarningInfo.getPB_MAX_OVERDUE_TIME_CHEAK());
        list.add(result17);
        PowerWarningResult result18 = new PowerWarningResult();
        result18.setNumber(" ");
        result18.setRiskDimension("预警结果");
        result18.setEarlyWarningSituation(powerWarningInfo.getWARNING_SIGNAL());
        result18.setElectricHouseholdOne(powerWarningInfo.getWARNING_SIGNAL());
        list.add(result18);
        return list;
    }



}