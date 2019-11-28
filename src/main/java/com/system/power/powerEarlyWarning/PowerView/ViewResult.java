package com.system.power.powerEarlyWarning.PowerView;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * ==================================================
 * fileName     : ViewResult
 *
 * @author : Wang Sen Rong
 * @description :
 * @create : 2019/11/27
 * ==================================================
 */
@Getter
@Setter
@ToString
public class ViewResult implements Serializable {
    private static final long serialVersionUID = 1L;
    /**公司名称*/
    private String companyName;
    /**电户号*/
    private String electricityAcountNumber;
    /**预警状态*/
    private String warningSignal;
    /**预警异常信息*/
    private Object warningList;
}