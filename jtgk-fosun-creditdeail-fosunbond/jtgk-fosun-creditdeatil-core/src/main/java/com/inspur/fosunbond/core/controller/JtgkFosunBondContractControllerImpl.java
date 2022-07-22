package com.inspur.fosunbond.core.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.inspur.fosunbond.core.domain.dto.JtgkFosunBondAmountAllocationDto;
import com.inspur.fosunbond.core.domain.dto.JtgkFosunBondContractDetailDto;
import com.inspur.fosunbond.core.domain.dto.JtgkFosunBondContractDetailJsonDto;
import com.inspur.fosunbond.core.domain.dto.JtgkFosunBondGuaranteeContractDto;
import com.inspur.fosunbond.core.domain.repository.JtgkFosunBondBaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Transactional
@Controller
@Slf4j
public class JtgkFosunBondContractControllerImpl implements JtgkFosunBondContractController {
    @Override
    public String getContractDetailById(String paramStr) {
        try {
            JSONObject jsonObject= JSON.parseObject(paramStr);
            String id=jsonObject.getString("id");
            JtgkFosunBondBaseRepository baseRepository=new JtgkFosunBondBaseRepository();
            String sql1="select * from vw_jtzjfosunbankloandetails where id='"+id+"'";
            String sql2="select * from vw_jtzjfosunloanwntycondetails where bankloanid='"+id+"'";
            String sql3="select * from vw_jtzjfosunloanamountatdetails where bankloanid='"+id+"'";
            List<JtgkFosunBondContractDetailDto> jtgkFosunBondContractDetailDtoList=baseRepository.queryList(sql1, JtgkFosunBondContractDetailDto.class);
            List<JtgkFosunBondGuaranteeContractDto> jtgkFosunBondGuaranteeContractDtoList=baseRepository.queryList(sql2,JtgkFosunBondGuaranteeContractDto.class);
            List<JtgkFosunBondAmountAllocationDto> jtgkFosunBondAmountAllocationDtoList=baseRepository.queryList(sql3,JtgkFosunBondAmountAllocationDto.class);

            if (jtgkFosunBondContractDetailDtoList!=null&&jtgkFosunBondContractDetailDtoList.size()>0)
            {
                JtgkFosunBondContractDetailDto cnDetailDto=jtgkFosunBondContractDetailDtoList.get(0);
                JtgkFosunBondContractDetailJsonDto cnDetailJsonDto=new JtgkFosunBondContractDetailJsonDto();

                cnDetailDto.setResultCode("S");
                cnDetailDto.setResultMsg("");
//                cnDetailJsonDto.setInterestsetdate(cnDetailDto.getInterestsetdate()==null?"":cnDetailDto.getInterestsetdate());
//                cnDetailJsonDto.setAnnualinterestrate(cnDetailDto.getAnnualinterestrate()==null?new BigDecimal(0):cnDetailDto.getAnnualinterestrate());
//                cnDetailJsonDto.setFloatingmethod(cnDetailDto.getFloatingmethod()==null?"":cnDetailDto.getFloatingmethod());
//                cnDetailJsonDto.setBankname(cnDetailDto.getBankname()==null?"":cnDetailDto.getBankname());
//                cnDetailJsonDto.setBenchinterestrate(cnDetailDto.getBenchinterestrate()==null?new BigDecimal(0):cnDetailDto.getBenchinterestrate());
//                cnDetailJsonDto.setFloatingtype(cnDetailDto.getFloatingtype()==null?"":cnDetailDto.getFloatingtype());
//                cnDetailJsonDto.setEffectivedate(cnDetailDto.getEffectivedate()==null?"":cnDetailDto.getEffectivedate());
//                cnDetailJsonDto.setBalanceamount(cnDetailDto.getBalanceamount()==null?new BigDecimal(0):cnDetailDto.getBalanceamount());
//                cnDetailJsonDto.setTotalamount(cnDetailDto.getTotalamount()==null?new BigDecimal(0):cnDetailDto.getTotalamount());
//                cnDetailJsonDto.setStatus(cnDetailDto.getStatus()==null?"":cnDetailDto.getStatus());
//                cnDetailJsonDto.setExpiredate(cnDetailDto.getExpiredate()==null?"":cnDetailDto.getExpiredate());
//                SimpleDateFormat sdf =new  SimpleDateFormat ("yyyy-MM-dd");
//                //修改实体时间格式
//                for (JtgkFosunBondGuaranteeContractDto contractDto:jtgkFosunBondGuaranteeContractDtoList)
//                {
//                    if(!"".equals(contractDto.getGuaranteeexpiredate()))
//                    {
//                        long  date_temp =new Long (contractDto.getGuaranteeexpiredate());
//                        String date_string = sdf .format (new Date(date_temp));
//                        contractDto.setGuaranteeexpiredate(date_string);
//                    }
//                    if (!"".equals(contractDto.getGuaranteestartdate()))
//                    {
//                        long  date_temp =new Long (contractDto.getGuaranteestartdate());
//                        String date_string = sdf .format (new Date(date_temp));
//                        contractDto.setGuaranteestartdate(date_string);
//                    }
//
//
//                }
                cnDetailDto.setWntyconList(jtgkFosunBondGuaranteeContractDtoList);
                cnDetailDto.setAmountatList(jtgkFosunBondAmountAllocationDtoList);
                String returnDtoList=JSON.toJSONString(cnDetailDto, SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteNullNumberAsZero);
                return returnDtoList;
            }

            return "{\"resultCode\":\"S\",\"resultMsg\":\"无数据\",}";
        }
        catch (Exception ex)
        {
            log.error("合同详情接口错误："+ex.getMessage());
            return "{\"resultCode\":\"E\",\"resultMsg\":"+ex.getMessage()+",}";
        }

    }
}
