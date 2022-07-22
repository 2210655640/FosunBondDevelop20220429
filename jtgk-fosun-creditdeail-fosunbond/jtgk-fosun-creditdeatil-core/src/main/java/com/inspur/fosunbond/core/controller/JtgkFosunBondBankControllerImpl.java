package com.inspur.fosunbond.core.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspur.fosunbond.core.domain.dto.*;
import com.inspur.fosunbond.core.domain.entity.FosunDebtContractHistory1Entity;
import com.inspur.fosunbond.core.domain.repository.JtgkFosunBondBaseRepository;
import io.iec.edp.caf.rpc.api.service.RpcClient;
import io.swagger.models.auth.In;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.hibernate.hql.internal.ast.tree.IsNotNullLogicOperatorNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;


@Transactional
@Controller
@Slf4j
public class JtgkFosunBondBankControllerImpl implements JtgkFosunBondBankController {
    @Autowired
    private RpcClient rpcClient;
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
            if("".equals(dataType))//总行查询及所有所属执行查询
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
            else if ("1".equals(dataType))//支行查询
            {
                bondBankListDetailDtoList=jtgkFosunBondBaseRepository.queryList(sql1,JtgkFosunBondBankListDetailDto.class);

            }
            else if("0".equals(dataType))//只查总行信息
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
            if (suffixName.contains("PNG")||suffixName.contains("png"))//只允许上传png格式图片
            {
                //filename= UUID.randomUUID()+suffixName;
                 filename=fileholderid+suffixName;
                //String filePath="/files";
                //String path= ResourceUtils.getURL("classpath:").getPath()+"static/upload";
                Resource resource = new ClassPathResource("");
                String filepath=resource.getFile().getAbsolutePath();
                //String path="/IDP开发/igix_2110_x86_64_build20211126/web/apps/bf/df/web/bankaccounts/banktypes";
                //saveFile = saveFile.replace("src\\main\\resources\\esfile", "web\\apps\\igo\\" + RfqCode + "\\"  + SaveFileName);
                String path=filepath.split("server")[0]+"web\\apps\\bf\\df\\web\\bankaccounts\\banktypes";
                File filefondler=new File(path);
                if(!filefondler.exists())
                {
                    filefondler.mkdirs();//创建文件夹
                }
                att.transferTo(new File(filefondler,filename));
            }
            else
            {
                return "请上传指定格式文件";
            }

            }
        }


        return "1";

    }

    @Override
    public String syncBankAccount(String bankaccount) throws JsonProcessingException {

        log.error("同步账户信息1");
        String returnMsg="";
        ObjectMapper objectMapper=new ObjectMapper();
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(smt);
        TypeReference<List<JtgkFosunBondIncomeBankAccountJHXDto>> ref=new TypeReference<List<JtgkFosunBondIncomeBankAccountJHXDto>>(){};
        List<JtgkFosunBondIncomeBankAccountJHXDto> jtgkFosunBondIncomeBankAccountJHXDtoList =objectMapper.readValue(bankaccount,ref);
       if (jtgkFosunBondIncomeBankAccountJHXDtoList!=null&&jtgkFosunBondIncomeBankAccountJHXDtoList.size()>0)
       {


//            LinkedHashMap sourceMap = new LinkedHashMap();
//            //Map<String, Object> sourceMap=new HashMap<>();
//            sourceMap.put("CLTNO", "Fosun006");//单位编号
//            sourceMap.put("ACCOUNT_NO", "Fosun006");//账号
//            ResponseEntity entity = rpcClient.invoke(ResponseEntity.class,
//                    "com.inspur.gs.tm.am.accountinterface.api.service.ITmAccountRpcService.accountInfoQry",
//                    "TM", sourceMap, null);
           JtgkFosunBondIncomeBankAccountJHXDto IncomeBankAccountJHXDto=jtgkFosunBondIncomeBankAccountJHXDtoList.get(0);
           //查询账户是否存在
           LinkedHashMap sourceMap = new LinkedHashMap();
           //Map<String, Object> sourceMap=new HashMap<>();
//        sourceMap.put("CLTNO", "Fosun006");//单位编号
//        sourceMap.put("ACCOUNT_NO", "Fosun006");//账号

           Map<String, Object> params=new HashMap<>();
//           params.put("CLTNO", "Fosun006");//单位编号
//           params.put("ACCOUNT_NO", "Fosun006");//账号

           params.put("CLTNO", IncomeBankAccountJHXDto.getCLTNO());//单位编号
           params.put("ACCOUNT_NO",IncomeBankAccountJHXDto.getACCOUNTNO());//账号
           sourceMap.put("params",params);
           log.error("同步账户信息2");
           ResponseEntity entity = rpcClient.invoke(ResponseEntity.class,
                   "com.inspur.gs.tm.am.accountinterface.api.service.ITmAccountRpcService.accountInfoQry",
                   "AM", sourceMap, null);
           log.error("同步账户信息3");
           log.error(entity.toString());

           List<BFBankAccounts> bfBankAccountsList=new ArrayList<>();
           JSONObject jsonObject=JSON.parseObject(JSON.toJSONString(entity, SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteNullNumberAsZero));
           List<BFBankAccounts>  mapList=(List<BFBankAccounts>) JSONArray.parseArray(jsonObject.getString("rET_BODY"),BFBankAccounts.class);

           //List<Map<String, String>>  mapList=(List<Map<String, String>>) entity.getRET_BODY();
           //List<Map<String, String>> l = (List<Map<String, String>>) json.getObj("DetailMsg");
           if(mapList!=null&&mapList.size()>0)//存在数据
           {
               log.error("同步账户信息4");
               //将数据重新组合返回
               List<JtgkFosunBondIncomeBankAccountJHXDto> resetIncomeBankAccountJHXDtoList=new ArrayList<>();//重新组合数据
               for (BFBankAccounts bfaccount:mapList)
               {
                   JtgkFosunBondIncomeBankAccountJHXDto resetIncomebankDto=new JtgkFosunBondIncomeBankAccountJHXDto();
                   resetIncomebankDto.setCURRENCYNO(bfaccount.getCRNCY_CODE());
                   resetIncomebankDto.setACCOUNTNO(bfaccount.getACCOUNT_NO());
                   resetIncomebankDto.setAREANAME(bfaccount.getCOUNTRY_NAME());
                   resetIncomebankDto.setAREAID(bfaccount.getCOUNTRY_TWOCHARCODE());
                   resetIncomebankDto.setCLTNAME(bfaccount.getCLT_NAME());
                   resetIncomebankDto.setCANCELDATE(bfaccount.getCLOSED_DATE());
                   resetIncomebankDto.setCREATE_TIME(bfaccount.getACCOUNT_DATE());
                   resetIncomebankDto.setAPPLYID("");
                   resetIncomebankDto.setACCOUNTCODE(bfaccount.getACCOUNT_NO());
                   resetIncomebankDto.setASSOCIATEFLAG("");
                   resetIncomebankDto.setBANKNO(bfaccount.getONLINEBANK_NO());
                   resetIncomebankDto.setCREATEUSER("");
                   resetIncomebankDto.setCTID(bfaccount.getCLTID());
                   resetIncomebankDto.setOPENACCOUNTDATE(bfaccount.getACCOUNT_DATE());
                   resetIncomebankDto.setREGNO(bfaccount.getCITY_NAME());
                   resetIncomebankDto.setCLTNO(bfaccount.getCLTNO());
                   resetIncomebankDto.setCANCELREMARK("");
                   resetIncomebankDto.setNATUREID(bfaccount.getINOROUT().toString());//账户种类
                   resetIncomebankDto.setREGNAME(bfaccount.getCITY_NAME());
                   //是否多币种
                  List<BFBankAccountItems> bankAccountItems=bfaccount.getBfBankAccountItemsList();
                   Boolean iscurrency=false;
                  if (bankAccountItems!=null&&bankAccountItems.size()>0)
                   {
                       String currencytype="";

                       for (BFBankAccountItems accountItems:bankAccountItems)
                       {   String tempCurrencyType=accountItems.getCRNCY_CODE_SUB();
                           if(!"".equals(tempCurrencyType))
                           {
                               if("".equals(currencytype))
                               {
                                   currencytype=tempCurrencyType;
                               }
                               else
                               {
                                   if (currencytype!=tempCurrencyType)
                                   {
                                       iscurrency=true;
                                       break;
                                   }
                               }
                           }
                       }
                   }
                   resetIncomebankDto.setISCURRENCY(iscurrency?"1":"0");//是否多币种
                   resetIncomebankDto.setAREAID("");
                   resetIncomebankDto.setACNTSTATE(bfaccount.getACCOUNTSTATUS().toString());
                   resetIncomebankDto.setCANCELREASON("");
                   resetIncomebankDto.setCNREMARK(bfaccount.getOPENINGEXPLAIN());
                   resetIncomebankDto.setUPDATE_TIME(bfaccount.getACCOUNT_DATE());
                   resetIncomebankDto.setACCOUNTNAME(bfaccount.getACCOUNT_NAME());
                   resetIncomebankDto.setACCOUNTID(bfaccount.getACCOUNT_ID());
                   resetIncomebankDto.setCHANNEL("");
                   resetIncomebankDto.setASSID("");
                   resetIncomebankDto.setRelationID("");
                   resetIncomebankDto.setISABROAD("");


                   resetIncomeBankAccountJHXDtoList.add(resetIncomebankDto);
               }
               //return  JSON.toJSONString(resetIncomeBankAccountJHXDtoList);
               returnMsg="{\"msg\":\"成功\",\"code\":\"200\",\"data\":"+ JSON.toJSONString(resetIncomeBankAccountJHXDtoList)+"}";
               return entity.toString();
           }
           else//未查询到账户信息则为新增账户信息
           {
               BFBankAccounts bfBankAccounts=new BFBankAccounts();//组织数据
               bfBankAccounts.setACCOUNT_ID(IncomeBankAccountJHXDto.getACCOUNTID());
               bfBankAccounts.setACCOUNT_DATE(IncomeBankAccountJHXDto.getOPENACCOUNTDATE());
               bfBankAccounts.setACCOUNT_NAME(IncomeBankAccountJHXDto.getACCOUNTNAME());
               bfBankAccounts.setACCOUNT_NAME_CHT("");
               bfBankAccounts.setACCOUNT_NAME_EN("");
               bfBankAccounts.setACCOUNT_NO(IncomeBankAccountJHXDto.getACCOUNTNO());
               bfBankAccounts.setACCOUNT_SHORTNAME("");
               bfBankAccounts.setACCOUNTING_UNIT_NO("");
               bfBankAccounts.setACCOUNTPROPERTY_CODE(IncomeBankAccountJHXDto.getCTID());
               bfBankAccounts.setACCOUNTPROPERTY_NAME("");
               if (!"".equals(IncomeBankAccountJHXDto.getACNTSTATE()))
               {
                   bfBankAccounts.setACCOUNTSTATUS(Integer.parseInt(IncomeBankAccountJHXDto.getACNTSTATE()));
               }
               if (!"".equals(IncomeBankAccountJHXDto.getUSAGEID()))
               {
                   bfBankAccounts.setACCOUNTUSE_CODE("0"+IncomeBankAccountJHXDto.getUSAGEID());
               }
               bfBankAccounts.setACCOUNTUSE_NAME("");
               bfBankAccounts.setBALCHECK_UNIT_NO("");
               bfBankAccounts.setBANK_CNAPS_CODE(IncomeBankAccountJHXDto.getBANKNO());//必填
               bfBankAccounts.setBANK_CNAPS_NAME("");
               bfBankAccounts.setBANKCANCELLATION_DATE(IncomeBankAccountJHXDto.getCANCELDATE());
               bfBankAccounts.setBANKCANCELLER("");
               bfBankAccounts.setBfBankAccountAuthorizedList(null);

               //设立子账户信息
               List<BFBankAccountItems> bfBankAccountItems=new ArrayList<>();
               BFBankAccountItems bankAccountItem=new BFBankAccountItems();
               bankAccountItem.setCRNCY_CODE_SUB("6a4b352a-f5c7-56c6-dfa8-ed501a87d98e");//设置为人民币
               bankAccountItem.setACCOUNT_TYPE_CODE("1");//账户类型编码
               bankAccountItem.setFREEZED_STATUS(1);//冻结状态
               bfBankAccountItems.add(bankAccountItem);
               bfBankAccounts.setBfBankAccountItemsList(bfBankAccountItems);
               //设立账户权限信息
               BFBankAccountAuthorized bfBankAccountAuthorized=new BFBankAccountAuthorized();
               bfBankAccountAuthorized.setAUTH_UNIT_NO(IncomeBankAccountJHXDto.getCLTNO());
               bfBankAccountAuthorized.setAUTH_STATUS(2);

               bfBankAccounts.setCITY_CODE(IncomeBankAccountJHXDto.getAREAID());
               bfBankAccounts.setCITY_NAME(IncomeBankAccountJHXDto.getAREANAME());
               bfBankAccounts.setCLOSED_DATE(IncomeBankAccountJHXDto.getCANCELDATE());
               bfBankAccounts.setCLOSED_USERNAME("");
               bfBankAccounts.setCLT_NAME(IncomeBankAccountJHXDto.getCLTNAME());
               bfBankAccounts.setCLTID("");
               bfBankAccounts.setCLTNO(IncomeBankAccountJHXDto.getCLTNO());
               bfBankAccounts.setCORP_UNIT_NO("");
               bfBankAccounts.setCOUNTRY_NAME("");
               bfBankAccounts.setCOUNTRY_TWOCHARCODE("");
               bfBankAccounts.setCRNCY_CODE(IncomeBankAccountJHXDto.getCURRENCYNO());
               bfBankAccounts.setCRNCY_NAME("");
               //bfBankAccounts.setDEADLINEBYMONTH();//使用期限
               bfBankAccounts.setDirect_TYPE(1);//必传
               bfBankAccounts.setDUEDATE(IncomeBankAccountJHXDto.getOPENACCOUNTDATE());//必填
               bfBankAccounts.setSTART_DATE(IncomeBankAccountJHXDto.getOPENACCOUNTDATE());//必填
               bfBankAccounts.setSTART_USERNAME(IncomeBankAccountJHXDto.getCREATEUSER());//必填
               bfBankAccounts.setINOROUT(2);//必传
               String returnDtoList=JSON.toJSONString(bfBankAccounts, SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteNullNumberAsZero);
               //组织实体类
               //BFBankAccounts bfBankAccounts=new BFBankAccounts();
               LinkedHashMap sourceMap1 = new LinkedHashMap();
               Map<String, Object> params1=new HashMap<>();
               params1.put("MAINTAIN_FLAG", 1);//处理类型
               params1.put("ACCOUNT_INFO", returnDtoList);//账号
               sourceMap1.put("params",params1);

               //sourceMap.put("MAINTAIN_FLAG", 1);//处理类型
               //sourceMap.put("ACCOUNT_INFO", returnDtoList);//账号
               log.error("同步账户信息2");
               ResponseEntity entity1 = rpcClient.invoke(ResponseEntity.class,
                       "com.inspur.gs.tm.am.accountinterface.api.service.ITmAccountRpcService.accountInfoMaintain",
                       "AM", sourceMap1, null);

               log.error("同步账户信息5");
               log.error(entity1.toString());
               //return JSON.toJSONString(entity1, SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteNullNumberAsZero);
               return entity1.toString();
           }
        }

       return "";
    }
}
