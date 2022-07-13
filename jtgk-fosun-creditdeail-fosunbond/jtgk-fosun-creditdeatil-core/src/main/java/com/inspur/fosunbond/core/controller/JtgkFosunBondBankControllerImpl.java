package com.inspur.fosunbond.core.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.inspur.fosunbond.core.domain.dto.JtgkFosunBondBankListDetailDto;
import com.inspur.fosunbond.core.domain.dto.JtgkFosunBondBankListDetailJsonDto;
import com.inspur.fosunbond.core.domain.repository.JtgkFosunBondBaseRepository;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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

    @Override
    public String uploadBankIcon(MultipartBody file) throws IOException {

        //String fileOriginalName=URLDecoder.decode(jsonNode.get("filename").asText());
        String fileholderid="";
        List<Attachment> fileList=file.getAllAttachments();
        for (Attachment att:fileList)
        {
           String filename=att.getDataHandler().getName();
            if ("filename".equals(filename))
            {
                @Cleanup InputStream is = att.getDataHandler().getInputStream();
                StringBuilder str = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line=null;
                while ((line=reader.readLine())!=null) {
                    str.append(line);
                }
                fileholderid=URLDecoder.decode(str.toString(),"utf-8");
            }
            else
            {
            String suffixName=filename.substring(filename.lastIndexOf("."));
            filename= UUID.randomUUID()+suffixName;
            //String filePath="/files";
            String path= ResourceUtils.getURL("classpath:").getPath()+"static/upload";
            File filefondler=new File(path);
            if(!filefondler.exists())
            {
                filefondler.mkdirs();//创建文件夹
            }
            att.transferTo(new File(filefondler,filename));
            }
        }


        return "1";

    }
}
