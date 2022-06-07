package com.inspur.fosunbond.core.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.inspur.fosunbond.core.domain.dto.BankBondInvestDetailDto;
import com.inspur.fosunbond.core.domain.repository.BaseRepository;
import com.inspur.fosunbond.core.domain.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author songxinqiang
 */
@Transactional
@Controller
@Slf4j
public class JtgkFosunBondBankBondInvestmentImpl implements JtgkFosunBondBankBondInvestmentController {
    @Override
    public Result getBankBondInvestmentDetails(JsonNode jsonNode) {
        Result result=new Result();
        String querydate=jsonNode.get("querydate").asText();
        String comp_name=jsonNode.get("comp_name").asText();
        String sql="select max(versiondate) as  maxversiondate \n" +
             "  from (select row_number() over(partition by tor.versiondate  order by tor.versiondate desc) rn, tor.* from FOSUNBONDHOLDER tor\n" +
             "where tor. versiondate <=?) t  ";
        log.error("sql:"+sql+",querydate:"+querydate+",comp_name:"+comp_name);
        BaseRepository baseRepository=new BaseRepository();
        String maxversiondate=baseRepository.queryString(sql,"maxversiondate",querydate);

        sql="select holder.secname,holder.accountno,holder.accountname,\n" +
                "shortdic.name_chs as shortname,  \n" +
                "clasicdic.name_chs as clasicname,\n" +
                "BFBANKTYPE.name_chs as investname,\n" +
                "holder.amt,\n" +
                "holder.DEBTOCCUPY, holder.UNDERWRITEOCCUPY,\n" +
                "sxht.id,\n" +
                "sxht.code,\n" +
                "holder.remark ,\n" +
                "CONTRACT.comp_name,\n" +
                "left(lastmodifytime,10) as zydate\n" +
                "from FOSUNBONDHOLDER  holder \n" +
                "inner join FOSUNBONDINVESTDIC invest on invest.ACCOUNTNUMBER=holder.accountno\n" +
                "LEFT JOIN FOSUNINVESTSHORTDIC  shortdic  ON invest.INVESTSHORTID = shortdic.ID\n" +
                "LEFT JOIN BFBANKTYPE BFBANKTYPE ON invest.BANKTYPEID = BFBANKTYPE.ID\n" +
                "LEFT JOIN FOSUNINVESTCLASICDIC clasicdic ON invest.INVESTCLASICID = clasicdic.ID\n" +
                "left join FOSUNCREDITCONTRACT sxht on sxht.id=holder.CREDITCODE\n" +
                "left join FOSUNDEBTCONTRACT CONTRACT on CONTRACT.sec_name=holder.secname \n" +
                "and CONTRACT.sdate<=? \n" +
                "left join fosunbondcredit on fosunbondcredit.contractid=sxht.id\n" +
                "where CONTRACT.comp_name=? and holder.versiondate=? ";
        log.error("sql:"+sql+",sdate:"+maxversiondate+",comp_name:"+comp_name+",versiondate"+maxversiondate);
        List<BankBondInvestDetailDto> bankBondInvestDetailDtoList=baseRepository.queryList(sql,BankBondInvestDetailDto.class,maxversiondate,comp_name,maxversiondate);
        return result.ok(bankBondInvestDetailDtoList);
    }
}
