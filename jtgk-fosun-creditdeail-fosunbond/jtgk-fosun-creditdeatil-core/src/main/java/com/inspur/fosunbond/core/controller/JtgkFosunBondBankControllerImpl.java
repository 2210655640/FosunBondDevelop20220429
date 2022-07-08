package com.inspur.fosunbond.core.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inspur.fosunbond.core.domain.dto.JtgkFosunBondBankListDetailDto;
import com.inspur.fosunbond.core.domain.dto.JtgkFosunBondBankListDetailJsonDto;
import com.inspur.fosunbond.core.domain.repository.JtgkFosunBondBaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Transactional
@Controller
@Slf4j
public class JtgkFosunBondBankControllerImpl implements JtgkFosunBondBankController {
    @Override
    public String getBankList(String paramStr) {
        String returnMsg="";
        try {
            //获取参数
            //JSONArray jsonArray= JSONArray.parseArray(paramStr);
            JSONObject job= JSON.parseObject(paramStr);
            //JSONObject job=jsonArray.getJSONObject(0);
            String bankTypeID=job.getString("bankTypeID");
            String dataType=job.getString("dataType");
            String dataID=job.getString("dataID");
            String sql="select id,banktype,detail, code,name_chs,simplename,EnglishName,\n" +
                    "Bfbanktypename,counrey,area,AreaDeatil,BANKIDENTIFIER,swiftcode,\n" +
                    "annualReview,bankanglea_name,bankangleb_name,note\n" +
                    "from VM_BANKMANAGE where 1=1 \n";
            String sql1=sql;
            JtgkFosunBondBaseRepository jtgkFosunBondBaseRepository=new JtgkFosunBondBaseRepository();
            if (!"".equals(dataID))
            {
                sql1+=" and id='"+dataID+"' ";
            }
            if (!"".equals(bankTypeID))//如果银行行别内码不为空
            {
                sql1+=" and banktype='"+bankTypeID+" '";
            }
            List<JtgkFosunBondBankListDetailDto> bondBankListDetailDtoList=new ArrayList<>();
            List<JtgkFosunBondBankListDetailJsonDto> bondBankListDetailJsonDtoList=new ArrayList<>();
            if("0".equals(dataType))//总行查询
            {
                bondBankListDetailDtoList=jtgkFosunBondBaseRepository.queryList(sql1,JtgkFosunBondBankListDetailDto.class);
                if (bondBankListDetailDtoList!=null&&bondBankListDetailDtoList.size()>0)
                {
                    String name_chs=bondBankListDetailDtoList.get(0).getName_chs();
                    if (!"".equals(name_chs))
                    {
                        String sql2=sql+" and bfbanktypename='"+name_chs+"' ";
                        List<JtgkFosunBondBankListDetailDto> bondBankListDetailDtoList1=jtgkFosunBondBaseRepository.queryList(sql2,JtgkFosunBondBankListDetailDto.class);
                        if (bondBankListDetailDtoList1!=null&&bondBankListDetailDtoList1.size()>0)
                        {
                            for (JtgkFosunBondBankListDetailDto bankListDetailDto:bondBankListDetailDtoList1)
                            {
                                bondBankListDetailDtoList.add(bankListDetailDto);
                            }
                        }
                    }
                }
            }
            else if ("1".equals(dataType)||"".equals(dataType))//支行查询或者行别为空
            {
                bondBankListDetailDtoList=jtgkFosunBondBaseRepository.queryList(sql1,JtgkFosunBondBankListDetailDto.class);

            }
            if (bondBankListDetailDtoList!=null&&bondBankListDetailDtoList.size()>0)
            {
                for (JtgkFosunBondBankListDetailDto bankListDetailDto:bondBankListDetailDtoList)
                {
                    JtgkFosunBondBankListDetailJsonDto bondBankListDetailJsonDto=new JtgkFosunBondBankListDetailJsonDto();
                    bondBankListDetailJsonDto.setDataID(bankListDetailDto.getId()==null?"":bankListDetailDto.getId());
                    bondBankListDetailJsonDto.setBankTypeID(bankListDetailDto.getBanktype()==null?"":bankListDetailDto.getBanktype());
                    bondBankListDetailJsonDto.setDataType(bankListDetailDto.getDetail()==null?"":bankListDetailDto.getDetail());
                    bondBankListDetailJsonDto.setCode(bankListDetailDto.getCode()==null?"":bankListDetailDto.getCode());
                    bondBankListDetailJsonDto.setName(bankListDetailDto.getName_chs()==null?"":bankListDetailDto.getName_chs());
                    bondBankListDetailJsonDto.setShortname(bankListDetailDto.getSimplename()==null?"":bankListDetailDto.getSimplename());
                    bondBankListDetailJsonDto.setEnName(bankListDetailDto.getEnglishName()==null?"":bankListDetailDto.getEnglishName());
                    bondBankListDetailJsonDto.setBankTypeName(bankListDetailDto.getBfbanktypename()==null?"":bankListDetailDto.getBfbanktypename());
                    bondBankListDetailJsonDto.setCountry(bankListDetailDto.getCounrey()==null?"":bankListDetailDto.getCounrey());
                    bondBankListDetailJsonDto.setArea(bankListDetailDto.getArea()==null?"":bankListDetailDto.getArea());
                    bondBankListDetailJsonDto.setAddress(bankListDetailDto.getAreaDetail()==null?"":bankListDetailDto.getAreaDetail());
                    bondBankListDetailJsonDto.setLhh(bankListDetailDto.getBANKIDENTIFIER()==null?"":bankListDetailDto.getBANKIDENTIFIER());
                    bondBankListDetailJsonDto.setSwiftcode(bankListDetailDto.getSwiftcode()==null?"":bankListDetailDto.getSwiftcode());
                    bondBankListDetailJsonDto.setAnnualReview(bankListDetailDto.getAnnualReview()==null?"":bankListDetailDto.getAnnualReview());
                    bondBankListDetailJsonDto.setBankanglea_Name(bankListDetailDto.getBankanglea_name()==null?"":bankListDetailDto.getBankanglea_name());
                    bondBankListDetailJsonDto.setBankangleb_Name(bankListDetailDto.getBankangleb_name()==null?"":bankListDetailDto.getBankangleb_name());
                    bondBankListDetailJsonDto.setNote(bankListDetailDto.getNote()==null?"":bankListDetailDto.getNote());
                    bondBankListDetailJsonDtoList.add(bondBankListDetailJsonDto);
                }
            }

            String returnDtoList=JSON.toJSONString(bondBankListDetailJsonDtoList);
             returnMsg="{\"resultCode\":\"S\",\"resultMsg\":\"\",\"dataList\":"+returnDtoList+"}";
        }
        catch (Exception ex)
        {
            returnMsg="{\"resultCode\":\"E\",\"resultMsg\":"+ex.getMessage()+",\"dataList\":\"\"}";
        }


        return returnMsg;
    }
}
