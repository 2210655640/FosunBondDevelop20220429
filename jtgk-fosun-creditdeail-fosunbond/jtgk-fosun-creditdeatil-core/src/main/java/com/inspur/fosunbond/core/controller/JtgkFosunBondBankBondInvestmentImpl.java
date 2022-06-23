package com.inspur.fosunbond.core.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.inspur.fosunbond.core.domain.dto.BankBondInvestDetailDto;
import com.inspur.fosunbond.core.domain.repository.JtgkFosunBondBaseRepository;
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
        //String comp_name=jsonNode.get("comp_name").asText();
        String varietiesid=jsonNode.get("varietiesid").asText();
//        String sql="select max(versiondate) as  maxversiondate \n" +
//             "  from (select row_number() over(partition by tor.versiondate  order by tor.versiondate desc) rn, tor.* from FOSUNBONDHOLDER tor\n" +
//             "where tor. versiondate <=?) t  ";
        //log.error("sql:"+sql+",querydate:"+querydate+",varietiesid:"+varietiesid);
        JtgkFosunBondBaseRepository jtgkFosunBondBaseRepository =new JtgkFosunBondBaseRepository();
        //String maxversiondate=baseRepository.queryString(sql,"maxversiondate",querydate);

//        sql="select holder.secname,holder.accountno,holder.accountname,\n" +
//                "shortdic.name_chs as shortname,  \n" +
//                "clasicdic.name_chs as clasicname,\n" +
//                "BFBANKTYPE.name_chs as investname,\n" +
//                "holder.amt,\n" +
//                "holder.DEBTOCCUPY, holder.UNDERWRITEOCCUPY,\n" +
//                "sxht.id,\n" +
//                "sxht.code,\n" +
//                "holder.remark ,\n" +
//                "CONTRACT.comp_name,\n" +
//                "left(lastmodifytime,10) as zydate\n" +
//                "from FOSUNBONDHOLDER  holder \n" +
//                "inner join FOSUNBONDINVESTDIC invest on invest.ACCOUNTNUMBER=holder.accountno\n" +
//                "LEFT JOIN FOSUNINVESTSHORTDIC  shortdic  ON invest.INVESTSHORTID = shortdic.ID\n" +
//                "LEFT JOIN BFBANKTYPE BFBANKTYPE ON invest.BANKTYPEID = BFBANKTYPE.ID\n" +
//                "LEFT JOIN FOSUNINVESTCLASICDIC clasicdic ON invest.INVESTCLASICID = clasicdic.ID\n" +
//                "inner join FOSUNCREDITCONTRACT sxht on sxht.id=holder.CREDITCODE\n" +
//                "left join FOSUNDEBTCONTRACT CONTRACT on CONTRACT.sec_name=holder.secname \n" +
//                "and CONTRACT.sdate=? \n" +
//                "inner join fosunbondcredit on fosunbondcredit.contractid=sxht.id\n" +
//                "where CONTRACT.comp_name=? and holder.versiondate=? ";
//        log.error("sql:"+sql+",sdate:"+maxversiondate+",comp_name:"+comp_name+",versiondate"+maxversiondate);
        String sql="select invest.accountname,\n" +
                "shortdic.name_chs as shortname,  \n" +
                "clasicdic.name_chs as clasicname,\n" +
                "BFBANKTYPE.name_chs as investname,\n" +
                "holderV.amt,\n" +
                "case when sxpzdic.name_chs='债券投资' then zyjl.EXECAMOUNT\n" +
                "else 0 end  as DEBTOCCUPY, \n" +
                "case when sxpzdic.name_chs='债券包销' then zyjl.EXECAMOUNT\n" +
                "else 0 end  as UNDERWRITEOCCUPY,\n" +
                "sxht.id,\n" +
                "sxht.code,\n" +
                "holder.remark,\n" +
                "'' as comp_name, \n"+
                " '' as zydate\n"+
                "from (select sum(sxzy.EXECAMOUNT) AS EXECAMOUNT,ACCOOUNTNUMBER,BONDSHORTNAME,CREDITVARID,CREDITID\n" +
                "            from FOSUNCREDITEXECREC sxzy \n" +
                "            where sxzy.EXECDATE<=? and sxzy.CATEGORY='1'\n" +
                "            GROUP BY sxzy.ACCOOUNTNUMBER,sxzy.BONDSHORTNAME,sxzy.CREDITID,sxzy.CREDITVARID) zyjl\n" +
                "inner join FOSUNBONDINVESTDIC invest on invest.ACCOUNTNUMBER=zyjl.ACCOOUNTNUMBER\n" +
                "LEFT JOIN FOSUNINVESTSHORTDIC  shortdic  ON invest.INVESTSHORTID = shortdic.ID\n" +
                "LEFT JOIN BFBANKTYPE BFBANKTYPE ON invest.BANKTYPEID = BFBANKTYPE.ID\n" +
                "LEFT JOIN FOSUNINVESTCLASICDIC clasicdic ON invest.INVESTCLASICID = clasicdic.ID \n" +
                "inner join FOSUNCREDITCONTRACT sxht on sxht.id=zyjl.CREDITID\n" +
                "inner join FOSUNCREDITVARIETIES sxpz on sxpz.id=zyjl.CREDITVARID\n" +
                "inner join CREDITTYPE sxpzdic on sxpzdic.id=sxpz.VARIETY\n" +
                "left join (select t.SECNAME,t.accountno,t.amt \n" +
                "  from (select row_number() over(partition by tor.SECNAME,tor.accountno  order by tor.versiondate desc) rn, tor.* \n" +
                "            from FOSUNBONDHOLDER tor where tor.versiondate<=? ) t  \n" +
                "            where t.rn = 1) holderV on holderV.SECNAME=zyjl.BONDSHORTNAME and holderV.accountno=zyjl.ACCOOUNTNUMBER \n" +
                "left join FOSUNBONDHOLDERDETAIL holder on holder.SECNAME=zyjl.BONDSHORTNAME \n" +
                "and holder.ACCOUNTNO=zyjl.ACCOOUNTNUMBER\n" +
                "WHERE sxpz.id=? ";
        //List<BankBondInvestDetailDto> bankBondInvestDetailDtoList=baseRepository.queryList(sql,BankBondInvestDetailDto.class,maxversiondate,comp_name,maxversiondate);
        List<BankBondInvestDetailDto> bankBondInvestDetailDtoList= jtgkFosunBondBaseRepository.queryList(sql,BankBondInvestDetailDto.class,querydate,querydate,varietiesid);
        return result.ok(bankBondInvestDetailDtoList);
    }
}
