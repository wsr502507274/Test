package com.system.power.powerEarlyWarning.powerInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * ==================================================
 * fileName     : PowerWarningInfo
 *
 * @author : Wang Sen Rong
 * @description :
 * @create : 2019/11/26
 * ==================================================
 */
@Getter
@Setter
@ToString
public class PowerWarningInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 半年度用电量异常变动
     */
    private String PD_6M_POWER_ABNORMAL_CHEAK;
    /**
     * 当日用电量的异常变动
     */
    private String PD_1D_POWER_ABNORMAL_CHEAK;
    /**
     * PB_FEE_SETTLEMENT_CHEAK
     */
    private String PB_FEE_SETTLEMENT_CHEAK;
    /**
     * 季度用电量异常变动
     */
    private String PD_3M_POWER_ABNORMAL_CHEAK;
    /**
     * 近1个月违法窃电次数
     */
    private String EB_1M_ILLEGAL_STEALING_CHEAK;
    /**
     * 近1个月违法用电次数
     */
    private String EB_1M_ILLEGAL_USE_CHEAK;
    /**
     * 近3个月违法窃电次数
     */
    private String EB_3M_ILLEGAL_STEALING_CHEAK;
    /**
     * 近3个月违法用电次数
     */
    private String EB_3M_ILLEGAL_USE_CHEAK;
    /**
     * 近5日用电量的异常变动
     */
    private String PD_5D_POWER_ABNORMAL_CHEAK;
    /**
     * 近10日用电量的异常变动
     */
    private String PD_10D_POWER_ABNORMAL_CHEAK;
    /**
     * 近15日用电量的异常变动
     */
    private String PD_15D_POWER_ABNORMAL_CHEAK;
    /**
     * 日冻结电量数据异常变动
     */
    private String DQ_30D_ABNOMAL_RATIO_CHEAK;
    /**
     * 用户状态
     */
    private String EB_USER_STATUS_CHEAK;
    /**
     * 月用电量异常变动
     */
    private String PD_1M_POWER_ABNORMAL_CHEAK;
    /**
     * 最大逾期时长
     */
    private String PB_MAX_OVERDUE_TIME_CHEAK;
    /**
     * 近1个月逾期缴费次数
     */
    private String PB_1M_OVERDUE_PAYMENT_CHEAK;
    /**
     * 近3个月逾期缴费次数
     */
    private String PB_3M_OVERDUE_PAYMENT_CHEAK;
    /**
     * 预警信号
     */
    private String WARNING_SIGNAL;
    /**
     * 日期
     */
    private String DATETIME;
    /**
     * 预警总况
     */
    private String ELCE_NUM;
    /**
     * 日冻结电量
     */
    private String FREEZE_ELEC_DAY;
    /**
     * 电户用电信息
     */
    private List<STRATEGYINFOBean> STRATEGY_INFO;

    @Getter
    @Setter
    public static class STRATEGYINFOBean {

        /**
         * 半年度用电量异常变动
         */
        private String PD_6M_POWER_ABNORMAL_CHEAK;
        /**
         * 当日用电量的异常变动
         */
        private String PD_1D_POWER_ABNORMAL_CHEAK;
        /**
         * 电费结清标志
         */
        private String PB_FEE_SETTLEMENT_CHEAK;
        /**
         * 季度用电量异常变动
         */
        private String PD_3M_POWER_ABNORMAL_CHEAK;
        /**
         * 近1个月违法窃电次数
         */
        private String EB_1M_ILLEGAL_STEALING_CHEAK;
        /**
         * 近1个月违法用电次数
         */
        private String EB_1M_ILLEGAL_USE_CHEAK;
        /**
         * 近3个月违法窃电次数
         */
        private String EB_3M_ILLEGAL_STEALING_CHEAK;
        /**
         * 近3个月违法用电次数
         */
        private String EB_3M_ILLEGAL_USE_CHEAK;
        /**
         * 近5日用电量的异常变动
         */
        private String PD_5D_POWER_ABNORMAL_CHEAK;
        /**
         * 近10日用电量的异常变动
         */
        private String PD_10D_POWER_ABNORMAL_CHEAK;
        /**
         * 近15日用电量的异常变动
         */
        private String PD_15D_POWER_ABNORMAL_CHEAK;
        /**
         * 日冻结电量数据异常变动
         */
        private String DQ_30D_ABNOMAL_RATIO_CHEAK;
        /**
         * 用户状态
         */
        private String EB_USER_STATUS_CHEAK;
        /**
         * 月用电量异常变动
         */
        private String PD_1M_POWER_ABNORMAL_CHEAK;
        /**
         * 最大逾期时长
         */
        private String PB_MAX_OVERDUE_TIME_CHEAK;
        /**
         * 近1个月逾期缴费次数
         */
        private String PB_1M_OVERDUE_PAYMENT_CHEAK;
        /**
         * 近3个月逾期缴费次数
         */
        private String PB_3M_OVERDUE_PAYMENT_CHEAK;
        /**
         * 预警信号
         */
        private String WARNING_SIGNAL;
        /**
         * 用户id
         */
        private String USER_ID;
    }
}