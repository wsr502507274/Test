package com.system.power.powerEarlyWarning.powerInfo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ==================================================
 * fileName     : Company
 *
 * @author : Wang Sen Rong
 * @description :
 * @create : 2019/11/27
 * ==================================================
 */
@Getter
@Setter
@ToString
@Data
public class Company {
    @Excel(name = "文件名")
    private String fileNo;
    @Excel(name = "用户号")
    private String userNo;
    @Excel(name = "企业名称")
    private String companyName;
}