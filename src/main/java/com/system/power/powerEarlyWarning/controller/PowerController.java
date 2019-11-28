package com.system.power.powerEarlyWarning.controller;


import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.system.power.powerEarlyWarning.PowerView.ViewResult;
import com.system.power.powerEarlyWarning.powerInfo.Company;
import com.system.power.powerEarlyWarning.powerInfo.PowerWarningInfo;
import com.system.power.powerEarlyWarning.powerService.PowerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ==================================================
 * fileName     : PowerController
 *
 * @author : Wang Sen Rong
 * @description : 电力预警系统查询
 * @create : 2019/11/25
 * ==================================================
 */
@RestController
@Slf4j
public class PowerController {

    @Autowired
    private PowerService powerService;

    /**
     * 公司名称与公司名称,用户号,文件号映射关系
     */
    private static Map<String, Company> COMPANY_MAPPING = new ConcurrentHashMap<>();

    /**
     * 文件编号和json对象对应关系
     */
    private static final Map<String, ArrayList<PowerWarningInfo>> NUMBER_OBJECT_MAPPING = new ConcurrentHashMap<>();

    @Value("${excle.read.path}")
    private Resource exclePath;

    @Value("${json.package.path}")
    private String packagePath;

    /**
     * ========================================
     *
     * @Author: Wang Sen Rong
     * @Description: 启动加载数据文件
     * @create: 2019/11/25 14:37
     * ========================================
     */
    @PostConstruct
    public void readFileLoop() {
        log.info("======开始读取文件======");
        long start = System.currentTimeMillis();
        readJsonFiles();
        long end = System.currentTimeMillis();
        log.info("耗时,{}", end - start);
        readEx();
        log.info("======结束读取文件======");

    }

    /**
     * ========================================
     *
     * @Author: Wang Sen Rong
     * @Description: 重新读取json数据,
     * @create: 2019/11/26 9:44
     * ========================================
     */
    @RequestMapping(value = "/power/reloadJsonFile.json")
    public void reloadJsonFile() {
        //清空map
        COMPANY_MAPPING.clear();
        NUMBER_OBJECT_MAPPING.clear();
        readFileLoop();
    }

    /**
     * ========================================
     *
     * @Author: Wang Sen Rong
     * @Description: 获取所有公司名称
     * @create: 2019/11/26 16:43
     * ========================================
     */
    @RequestMapping(value = "/power/queryCompanyName.json")
    public ViewResult queryCompanyName() {
        //返回前端结果
        ViewResult resultView = new ViewResult();
        List<String> list = new ArrayList<>();
        Set<String> keys = COMPANY_MAPPING.keySet();
        keys.forEach(key -> list.add(key));
        resultView.setWarningList(list);
        return resultView;
    }

    /**
     * ========================================
     *
     * @Author: Wang Sen Rong
     * @Description: 根据公司名称查询电力预警信息
     * @create: 2019/11/25 14:36
     * ========================================
     */
    @RequestMapping(value = "/power/queryPower.json")
    public ViewResult queryPower(String companyName, String endDate) {
        if (StringUtils.isBlank(companyName)) {
            return null;
        }

        log.info("查询电力预警信息入参:corporateName,endDate:{},{}", companyName, endDate);
        if (StringUtils.isBlank(endDate)) {
            endDate = "2019-10-29";
        }
        //获取编号信息
        Company company = COMPANY_MAPPING.get(companyName.trim());
        if (company == null) {
            log.info("根据公司名称未获取到文件信息");
            return null;
        }
        //返回前端结果
        ViewResult resultView = new ViewResult();
        //获取去用电户号
        if (StringUtils.isBlank(company.getUserNo())) {
            log.info("根据公司信息未获取到电户号");
            return null;
        }
        //获取电力信息
        ArrayList<PowerWarningInfo> powerWarningInfos = NUMBER_OBJECT_MAPPING.get(company.getFileNo() + ".json");

        if (powerWarningInfos != null && powerWarningInfos.size() > 0) {
            Map<String, PowerWarningInfo> map = new HashMap<>();
            //公司名称
            resultView.setCompanyName(companyName);
            //用电户号
            resultView.setElectricityAcountNumber(company.getUserNo());
            //预警状态
            resultView.setWarningSignal(powerWarningInfos.get(0).getWARNING_SIGNAL());
            for (PowerWarningInfo powerWarningInfo : powerWarningInfos) {
                //根据日期放入map中
                map.put(powerWarningInfo.getDATETIME(), powerWarningInfo);
            }
            //根据日期获取用电信息
            resultView = powerService.getPowerInfoResult(endDate, map, resultView);
            return resultView;
        } else {
            log.info("根据文件名称,没找到用电信息");
            return null;
        }
    }

    /**
     * ========================================
     *
     * @Author: Wang Sen Rong
     * @Description: 读取所有json文件
     * @create: 2019/11/26 16:14
     * ========================================
     */
    private void readJsonFiles() {
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(packagePath);
            for (Resource resource : resources) {
                String areaDate = IOUtils.toString(resource.getInputStream(), Charset.forName("UTF-8"));
                ArrayList<PowerWarningInfo> powerWarningInfos = JSON.parseObject(areaDate, new TypeReference<ArrayList<PowerWarningInfo>>() {
                });
                NUMBER_OBJECT_MAPPING.put(resource.getFilename().trim(), powerWarningInfos);
            }
        } catch (Exception e) {
            log.error("读取json文件异常", e);
        }
    }

    /**
     * ========================================
     *
     * @Author: Wang Sen Rong
     * @Description: 读取Excel文件
     * @create: 2019/11/26 14:38
     * ========================================
     */
    public void readEx() {
        try {
            //File file = exclePath.getFile();
            InputStream ip = Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("companyMapping/company_code.xlsx"));
            ImportParams params = new ImportParams();
            params.setHeadRows(1);
            List<Company> objects = ExcelImportUtil.importExcel(ip, Company.class, params);
            COMPANY_MAPPING =
                    objects.stream().collect(Collectors.toMap(Company::getCompanyName, Function.identity(), (v1, v2) -> v1));

        } catch (Exception e) {
            log.error("读取Excel获取流失败", e);
        }
    }
}