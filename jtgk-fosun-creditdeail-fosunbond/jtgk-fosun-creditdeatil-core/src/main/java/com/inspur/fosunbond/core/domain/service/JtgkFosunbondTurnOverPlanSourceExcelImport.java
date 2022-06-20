package com.inspur.fosunbond.core.domain.service;

import com.inspur.fastdweb.exception.ResultException;
import com.inspur.fastdweb.model.excel.ExcelObject;
import com.inspur.fastdweb.model.excel.ExcelResult;
import com.inspur.fastdweb.service.excel.IExcelImportEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
//@Service
public  class JtgkFosunbondTurnOverPlanSourceExcelImport implements IExcelImportEvent {

    private static final int CATEGORY_COL_IDX = 1;//来源索引
    private static final int FINANCIAL_COL_IDX = 2;//金融机构
    private static final int REMARKS_COL_IDX=5;//备注
    /**
     * 导入前校验
     *
     * @param excelObject
     * @return
     */
    @Override
    public ExcelObject beforeImport(ExcelObject excelObject) {
        log.info("文件导入前校验");

        // 2.获取数据起始行
        int rowStartIdx = 0;
        try {
            rowStartIdx = Integer.parseInt(excelObject.rowStart) - 1;
        } catch (NumberFormatException e) {
            log.error("excelObject.rowStart有误");
            e.printStackTrace();
            throw new ResultException("check", "excelObject.rowStart有误");
        }
        // 3.数据校验
        List<ExcelResult> rows = excelObject.rows;
        StringBuilder errMsgStrBd = new StringBuilder();
        for (int i = rowStartIdx; i < rows.size(); i++)
        {
            List<String> curRowData = rows.get(i).result;
            //获取类型
            String category=curRowData.get(CATEGORY_COL_IDX);
            String remarks=curRowData.get(REMARKS_COL_IDX);
            String financial=curRowData.get(FINANCIAL_COL_IDX);
            if ("2".equals(category))//资金来源为公开市场债券 校验备注不为空
            {
               if ("".equals(remarks)||remarks==null)
               {
                   String errMsg = String.format("第%d行类别为公开市场债券第%d列备注不能为空\n", i + 1, REMARKS_COL_IDX + 1);
                   errMsgStrBd.append(errMsg);
                   log.warn(errMsg);
               }
               if (!financial.isEmpty())
               {
                   String errMsg = String.format("第%d行类别为公开市场债券第%d列金融机构不能填写\n", i + 1, FINANCIAL_COL_IDX + 1);
                   errMsgStrBd.append(errMsg);
                   log.warn(errMsg);
               }
            }

        }
        if (errMsgStrBd.length() > 0) {
            excelObject.checkStr = errMsgStrBd.toString();
            log.info("错误信息存储到ExcelObject.checkStr");
            throw new ResultException("check", errMsgStrBd.toString());
        }
        return  excelObject;
    }

}
