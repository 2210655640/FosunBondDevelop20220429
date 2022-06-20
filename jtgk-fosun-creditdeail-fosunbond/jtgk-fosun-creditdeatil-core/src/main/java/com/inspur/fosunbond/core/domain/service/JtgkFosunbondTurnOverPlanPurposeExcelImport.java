package com.inspur.fosunbond.core.domain.service;
import com.inspur.fastdweb.exception.ResultException;
import com.inspur.fastdweb.model.excel.*;
import com.inspur.fastdweb.service.excel.IExcelImportEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
//@Component
public class JtgkFosunbondTurnOverPlanPurposeExcelImport implements IExcelImportEvent {
    private static final int CATEGORY_COL_IDX = 1;//来源索引
    /**
     * 导入前校验
     *
     * @param excelObject
     * @return
     */
    @Override
    public ExcelObject beforeImport(ExcelObject excelObject) {
//        log.info("文件导入前校验");
//
//        // 2.获取数据起始行
//        int rowStartIdx = 0;
//        try {
//            rowStartIdx = Integer.parseInt(excelObject.rowStart) - 1;
//        } catch (NumberFormatException e) {
//            log.error("excelObject.rowStart有误");
//            e.printStackTrace();
//            throw new ResultException("check", "excelObject.rowStart有误");
//        }
//        // 3.数据校验
//        List<ExcelResult> rows = excelObject.rows;
//        StringBuilder errMsgStrBd = new StringBuilder();
//        for (int i = rowStartIdx; i < rows.size(); i++) {
//            List<String> curRowData = rows.get(i).result;
//            // a.现金流量项目校验
//            if (zjhSyxCashflowTypes.stream().noneMatch(
//                    cashflowTypeDto -> cashflowTypeDto.getName().equals(curRowData.get(CASHFLOW_COL_IDX)))) {
//                String errMsg = String.format("第%d行第%d列现金流量项目或授信品种数据有误\n", i + 1, CASHFLOW_COL_IDX + 1);
//                errMsgStrBd.append(errMsg);
//                log.warn(errMsg);
//            }
//            // b.日期格式校验
//            try {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                dateFormat.parse(curRowData.get(JHRQ_COL_IDX));
//            } catch (ParseException e) {
//                String errMsg = String.format("第%d行第%d列计划日期格式有误\n", i + 1, JHRQ_COL_IDX + 1);
//                errMsgStrBd.append(errMsg);
//                log.warn(errMsg);
//            }
//        }
//        if (errMsgStrBd.length() > 0) {
//            excelObject.checkStr = errMsgStrBd.toString();
//            log.info("错误信息存储到ExcelObject.checkStr");
//        }
        return excelObject;
    }



    /**
     * 插入数据库前
     *
     * @param excelObject
     * @return
     */
    @Override
    public ExcelObject beforeInsertImport(ExcelObject excelObject) {
//        // 0.预判断
//        log.info("文件导入前");
//        if (excelObject != null) {
//            log.info("excelObject: {}", excelObject);
//        } else {
//            log.warn("excelObject为空!");
//            return excelObject;
//        }
//        if (excelObject.fieldMap != null) {
//            log.info("excelObject.fieldMap: {}", excelObject.fieldMap);
//        } else {
//            log.warn("excelObject.fieldMap为空");
//            return excelObject;
//        }
//        if (excelObject.valMap != null) {
//            log.info("excelObject.valMap : {}", excelObject.valMap);
//        } else {
//            log.warn("excelObject.valMap为空");
//            return excelObject;
//        }
//        // 期间校验
//        boolean inPeriod = isInPeriod(excelObject);
//        if (inPeriod) {
//            log.info("校验通过");
//        } else {
//            log.warn("校验失败");
//        }
//        // 组织校验
//        Map<String, Object> valMap = excelObject.valMap;
//        String jhjhdata_xmnm = (String) valMap.get("JHJHDATA_XMNM");
//        if (StringUtil.isNullOrEmpty(jhjhdata_xmnm)) {
//            log.warn("项目内码为空");
//            excelObject.checkStr += "项目内码为空";
//        } else {
//            log.info("项目内码为: {}", jhjhdata_xmnm);
//        }
//
//        // 1.处理编号
//        // a.获取单据编码值
//        Map<String, String> codeMap = new HashMap<>();
//        codeMap.put("JHJHDATA_JHBH", "");
//        if (codeService == null) {
//            codeService = SpringBeanUtils.getBean(CodeService.class);
//            log.info("codeService为null, 现加载codeService: {}", codeService.toString());
//        } else {
//            log.info("codeService不为空, codeService: {}", codeService.toString());
//        }
//        String ruleCode = null;
//        try {
//            ruleCode = codeService.createCode(DOC_CODE_RULE_ID, codeMap);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            e.printStackTrace();
//            ruleCode = "test";
//        }
//        // b.值字段添加值
//        valMap.put(DOC_CODE_FIELD, ruleCode);
//        log.info("将{}赋予{}值", DOC_CODE_FIELD, ruleCode);
//        // 2.返回折算币种与汇率
//        String originalCurrencyId = (String) valMap.get("JHJHDATA_ZDYXM9");
//        String originalCurrencyCode = (String) valMap.get("JHJHDATA_ZDYXM8");
//        String originalCurrencyName = (String) valMap.get("JHJHDATA_ZDYXM");
//        JtgkExchangeRateManager jtgkExchangeRateManager = new JtgkExchangeRateManager();
//        // 判断
//        if (StringUtil.isNullOrEmpty(originalCurrencyId)) {
//            log.warn("Excel导入中, 原币种id为空");
//            throw new ResultException("check", "Excel导入中, 原币种id为空");
//        }
//        if (StringUtil.isNullOrEmpty(originalCurrencyCode) && StringUtil.isNullOrEmpty(originalCurrencyName)) {
//            log.warn("Excel导入中, 原币种Code和Name均为空, 无法进行币种判断");
//            throw new ResultException("check", "Excel导入中, 原币种Code和Name均为空, 无法进行币种判断");
//        }
//        // 查询
//        ConvertCurrency queryData = jtgkExchangeRateManager.getConvertCurrencyAndExchangeRateStrWithDate(
//                originalCurrencyId, originalCurrencyCode, originalCurrencyName, new Date());
//        // 折算币种赋值
//        valMap.put("JHJHDATA_BZBH", queryData.getConvertCurrencyId());
//        valMap.put("JHJHDATA_RATE", queryData.getExchangeRate());
//        // 3.依据折算币种Code分别计算CNY与USD
//        BigDecimal exchange = queryData.getExchangeRate().setScale(6, BigDecimal.ROUND_HALF_UP);
//        Object amountObj = valMap.get("JHJHDATA_ZDYJE");
//        BigDecimal originalWAmount;
//        if (amountObj instanceof BigDecimal) {
//            originalWAmount = (BigDecimal) amountObj;
//            log.info("金额为BigDecimal类型");
//        } else if (amountObj instanceof String) {
//            originalWAmount = new BigDecimal((String) amountObj);
//            log.info("金额为String类型");
//        } else {
//            log.warn("金额{}, 为未知类型{}", amountObj, amountObj.getClass());
//            return excelObject;
//        }
//        // 四舍五入
//        originalWAmount = originalWAmount.setScale(0, RoundingMode.HALF_UP);
//        valMap.put("JHJHDATA_ZDYJE", originalWAmount.toPlainString());
//        BigDecimal convertCurrencyWVal = originalWAmount.multiply(exchange).setScale(0, RoundingMode.HALF_UP);
//        BigDecimal convertCurrency = convertCurrencyWVal.multiply(CONVERSION_RATIO).setScale(0, RoundingMode.HALF_UP);
//        if (CommonHelper.CurrencyCodeOfCny.equals(queryData.getConvertCurrencyCode())) {
//            valMap.put("JHJHDATA_ZDYJE3", convertCurrencyWVal.toPlainString());
//            valMap.put("JHJHDATA_ZDYJE1", convertCurrency.toPlainString());
//        } else if (CommonHelper.CurrencyCodeOfUsd.equals(queryData.getConvertCurrencyCode())) {
//            valMap.put("JHJHDATA_ZDYJE4", convertCurrencyWVal.toPlainString());
//            valMap.put("JHJHDATA_ZDYJE2", convertCurrency.toPlainString());
//        } else {
//            log.error("折算币种{}有问题", queryData.getConvertCurrencyCode());
//            return excelObject;
//        }
        return excelObject;
    }

    /**
     * 导入后事件
     *
     * @param excelObject
     */
    @Override
    public void afterImport(ExcelObject excelObject) {
        IExcelImportEvent.super.afterImport(excelObject);
    }

    /**
     * 全部导入后事件
     *
     * @param excelObject
     */
    @Override
    public void finalImport(ExcelObject excelObject) {
        // 0.参数验证
        //log.info("全部导入后");
/*        if (excelObject != null) {
            log.info("excelObject: {}", excelObject);
        } else {
            log.warn("excelObject为空!");
            return;
        }
        if (excelObject.valMap != null) {
            log.info("excelObject.valMap : {}", excelObject.valMap);
        } else {
            log.warn("excelObject.valMap为空");
        }
        // 1.获取相关数据
        // a.组织内码 JHJHDATA_BZDW
        Map<String, Object> valMap = excelObject.valMap;
        String vsZZNM = (String) valMap.get("JHJHDATA_BZDW");
        if (StringUtil.isNullOrEmpty(vsZZNM)) {
            log.error("组织内码为空");
            return;
        }
        // b.实例内码 JHJHDATA_SLNM
        String SLNM = (String) valMap.get("JHJHDATA_SLNM");
        if (StringUtil.isNullOrEmpty(SLNM)) {
            log.error("实例内码为空");
            return;
        }
        // c.关联汇总表内码
        String vsBBNM = HUIZ_BD_ID;
        // 2.远程调用参数配置
        LinkedHashMap DataStruct = new LinkedHashMap();
        JSONObject structQueryjson = new JSONObject();
        structQueryjson.put("zznm", vsZZNM);
        structQueryjson.put("slnm", SLNM);
        structQueryjson.put("hzbbnm", vsBBNM);
        DataStruct.put("parameters", structQueryjson.toString());
        log.info(structQueryjson.toString());
        // 3.远程调用
        String vsInfo = SpringBeanUtils.getBean(RpcClient.class).invoke(String.class, " inspur.cb.jhjh.api.PlanPushDataApi.pushSummaryData",
                " jhpt", DataStruct, null);
        log.info(vsInfo);*/
    }

    /**
     * 模板下载前
     *
     * @param exi
     * @param exiMx
     * @param dynMap
     */
    @Override
    public void beforeExcelDownload(IDPExcelImport exi, List<IDPExcelImportMx> exiMx, Map<String, Object> dynMap) {
        IExcelImportEvent.super.beforeExcelDownload(exi, exiMx, dynMap);
    }

    /**
     * 模板导出前
     *
     * @param dataList
     * @param mxInfo
     */
    @Override
    public void beforeExcelExport(List<List<Object>> dataList, ExcelMXInfo mxInfo) {
        IExcelImportEvent.super.beforeExcelExport(dataList, mxInfo);
    }
}
