package com.inspur.fosunbond.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspur.fosunbond.core.domain.entity.FosunDebtContract1Entity;
import com.inspur.fosunbond.core.domain.entity.FosunDebtContractHistory1Entity;
import com.inspur.fosunbond.core.domain.entity.FosunRepaymentApp1Entity;
import com.inspur.fosunbond.core.domain.entity.FosunRepaymentAppSon1Entity;
import com.inspur.fosunbond.core.domain.repository.FosunDebtContract1Repository;
import com.inspur.fosunbond.core.domain.repository.FosunDebtContractHistory1Repository;
import com.inspur.fosunbond.core.domain.result.Result;
import com.inspur.fosunbond.core.domain.service.Creditdeatil111Service;
import com.inspur.fosunbond.core.domain.repository.BaseRepository;
import io.iec.edp.caf.commons.utils.SpringBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xiaoyc
 */
@Transactional
@Controller
@Slf4j
public class CreaditDatil111ControllerImpl implements CreaditDatil111Controller {
    @Autowired
    private FosunDebtContract1Repository fosunDebtContractRepository;
    @Autowired
    private FosunDebtContractHistory1Repository fosunDebtContractHistoryRepository;
    /**
     * 日志明细更新处理
     * @param disDataStr 异构系统报文
     * 2022-2-10
     */
    @Override
    public String updateCeaditdatil(String disDataStr) {
        try {
            log.error("updateCeaditdatil start==入参" + disDataStr);
            Creditdeatil111Service cdService = SpringBeanUtils.getBean(Creditdeatil111Service.class);
            return cdService.updateCreditDetail(disDataStr);
        }catch (Exception ex ){
            JSONObject jo = new JSONObject();
            jo.put("code","1");
            jo.put("msg","controller:"+ex.toString());
            return jo.toString();
        }
    }
    @Override
    public String getFoSunRepaymentAppById(JsonNode jsonNode) {
        try{
            log.error("复兴还款申请返回结果1");
            String id=jsonNode.get("id").asText();
            String userCode=jsonNode.get("userCode").asText();
            String enUserCode=jsonNode.get("enUserCode").asText();
            String sql=" select * from FOSUNREPAYMENTAPP where ID="+id+" ";
            log.error("复兴还款申请返回结果2");
            BaseRepository baseRepository=new BaseRepository();
            List<FosunRepaymentApp1Entity> fosunRepaymentApp1EntityList =baseRepository.queryList(sql, FosunRepaymentApp1Entity.class);
            log.error("复兴还款申请返回结果3");


            List<Map<String,Object>> jsonlist=new ArrayList<>();
            List<Map<String,Object>> jsonlistinside=new ArrayList<>();
            Map<String,Object> map=new HashMap<>();
            if (fosunRepaymentApp1EntityList.size()>0)
            {
                log.error("复兴还款申请返回结果4");
                sql="select * from FOSUNREPAYMENTAPPSON where MAINID="+id +" ";
                List<FosunRepaymentAppSon1Entity> fosunRepaymentAppSon1EntityList =baseRepository.queryList(sql, FosunRepaymentAppSon1Entity.class);
                FosunRepaymentApp1Entity fosunRepaymentApp1Entity = fosunRepaymentApp1EntityList.get(0);
                map.put("resultCode","S");
                map.put("resultMsg","");
                log.error("复兴还款申请返回结果5");
                Map<String,Object> appmap=new HashMap<>();
                appmap.put("id", fosunRepaymentApp1Entity.getId());
                appmap.put("code", fosunRepaymentApp1Entity.getCode());
                appmap.put("repaydate", fosunRepaymentApp1Entity.getRepaydate());
                appmap.put("sumprincipalamount", fosunRepaymentApp1Entity.getPrincipalamount());
                appmap.put("suminterestamount", fosunRepaymentApp1Entity.getInterestamount());
                log.error("复兴还款申请返回结果6");
                appmap.put("sumamount", fosunRepaymentApp1Entity.getSumamount());
                appmap.put("summary", fosunRepaymentApp1Entity.getSummary());
                appmap.put("status", fosunRepaymentApp1Entity.getStatus());
                appmap.put("detailList", fosunRepaymentAppSon1EntityList);
                log.error("复兴还款申请返回结果7");
                map.put("data",appmap);
                jsonlist.add(map);
                log.error("复兴还款申请返回结果8");

            }
            else
            {
                map.put("resultCode","E");
                map.put("resultMsg","没有获取到数据");
                map.put("data","");
                jsonlist.add(map);
                log.error("复兴还款申请返回结果9");
            }
            JSONArray jsonArrayFosun=new JSONArray(jsonlist);
            String returnMsg=jsonArrayFosun.toString().substring(1);
            log.error("复兴还款申请返回结果",returnMsg);
            log.error("复兴还款申请返回结果10");
            return  returnMsg.substring(0,returnMsg.length()-1);
        }
        catch (Exception e)
        {
            log.error("复兴还款申请返回结果11");
            log.error("复兴还款申请接口错误：",e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public Result getFosunDebtContractHistoryByVersionDate(JsonNode jsonNode) throws ParseException {
        Result result=new Result();
        String versionDate=jsonNode.get("versiondate").asText();
        String com_name=jsonNode.get("com_name").asText();
        String sec_name=jsonNode.get("sec_name").asText();//债券简称
        String bondtype=jsonNode.get("bondtype").asText();
        String carrydate=jsonNode.get("carrydate").asText();
        String maturitydate=jsonNode.get("maturitydate").asText();
        Date nowdate=new Date();
        String nowDateStr=new SimpleDateFormat("yyyy-MM-dd").format(nowdate);
        Date nowmaturityda=new SimpleDateFormat("yyyy-MM-dd").parse(nowDateStr);
        if (versionDate=="")
        {
            Date versionda=null;
            if (!versionDate.isEmpty())
            {
                versionda=new SimpleDateFormat("yyyy-MM-dd").parse(versionDate);
            }
            Date carryda=null;
            if (!carrydate.isEmpty())
            {
                carryda=new SimpleDateFormat("yyyy-MM-dd").parse(carrydate);
            }
            Date maturityda=null;
            if (!maturitydate.isEmpty())
            {
                maturityda = new SimpleDateFormat("yyyy-MM-dd").parse(maturitydate);
            }


            Date finalVersionda = versionda;
            Date finalCarryda = carryda;
            Date finalMaturityda = maturityda;
            Date finalnowmaturityda=nowmaturityda;
            List<FosunDebtContract1Entity> fosunDebtContract1EntityList =fosunDebtContractRepository.findAll((Specification<FosunDebtContract1Entity>) (root, query, cb) -> {
                Predicate where = cb.and();
                if (!"".equals(finalVersionda)&& finalVersionda !=null)
                {
                    Predicate p=cb.equal(root.get("historyversiondate"), finalVersionda);
                    where =cb.and(where,p);
                }
                if (!"".equals(com_name)&&com_name!=null)
                {
                    Predicate p=cb.equal(root.get("com_name"),com_name);
                    where =cb.and(where,p);
                }
                if (!"".equals(sec_name)&&sec_name!=null)
                {
                    Predicate p=cb.equal(root.get("sec_name"),sec_name);
                    where =cb.and(where,p);
                }
                if (!"".equals(bondtype)&&bondtype!=null)
                {
                    Predicate p=cb.equal(root.get("bondtype"),bondtype);
                    where =cb.and(where,p);
                }
                if (!"".equals(finalCarryda)&& finalCarryda !=null)
                {
                    Predicate p=cb.equal(root.get("carrydate"), finalCarryda);
                    where =cb.and(where,p);
                }
                if (!"".equals(finalMaturityda)&& finalMaturityda !=null)
                {
                    Predicate p=cb.equal(root.get("maturitydate"), finalMaturityda);
                    where =cb.and(where,p);
                }
                else
                {

                    Predicate p=cb.greaterThanOrEqualTo(root.get("maturitydate"), finalnowmaturityda);
                    where =cb.and(where,p);
                }

                return where;
            });
            return  result.ok(fosunDebtContract1EntityList);
        }
        else
        {
            Date versionda=null;
            if (!versionDate.isEmpty())
            {
                versionda=new SimpleDateFormat("yyyy-MM-dd").parse(versionDate);
            }
            Date carryda=null;
            if (!carrydate.isEmpty())
            {
                 carryda=new SimpleDateFormat("yyyy-MM-dd").parse(carrydate);
            }
            Date maturityda=null;
            if (!maturitydate.isEmpty())
            {
                maturityda=new SimpleDateFormat("yyyy-MM-dd").parse(maturitydate);
            }
            Integer rebackcount=0;
            List<FosunDebtContractHistory1Entity> fosunDebtContractHistory1EntityList =getFosunDebtContractHisList(versionda,com_name,sec_name,bondtype,carryda,maturityda,nowmaturityda,rebackcount);
            return result.ok(fosunDebtContractHistory1EntityList);
        }

    }

    @Override
    public Result saveFosunDebtContractHistoryByVersionDate(JsonNode jsonNode) throws JsonProcessingException, ParseException {

        Result result=new Result();
        String historyEntity=jsonNode.get("historyentity").asText();
        String versionDate=jsonNode.get("versiondate").asText();
        String com_name=jsonNode.get("com_name").asText();
        String sec_name=jsonNode.get("sec_name").asText();//债券简称
        String bondtype=jsonNode.get("bondtype").asText();
        String carrydate=jsonNode.get("carrydate").asText();
        String maturitydate=jsonNode.get("maturitydate").asText();
        Date nowdate=new Date();
        String nowDateStr=new SimpleDateFormat("yyyy-MM-dd").format(nowdate);
        Date nowmaturityda=new SimpleDateFormat("yyyy-MM-dd").parse(nowDateStr);
        Date versionda=null;
        if (!versionDate.isEmpty())
        {
            versionda=new SimpleDateFormat("yyyy-MM-dd").parse(versionDate);
        }
        Date carryda=null;
        if (!carrydate.isEmpty())
        {
            carryda=new SimpleDateFormat("yyyy-MM-dd").parse(carrydate);
        }
        Date maturityda=null;
        if (!maturitydate.isEmpty())
        {
            maturityda = new SimpleDateFormat("yyyy-MM-dd").parse(maturitydate);
        }


        Date finalVersionda = versionda;
        Date finalCarryda = carryda;
        Date finalMaturityda = maturityda;
        Date finalnowmaturityda=nowmaturityda;


        ObjectMapper objectMapper=new ObjectMapper();
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(smt);
        TypeReference<List<FosunDebtContractHistory1Entity>> ref=new TypeReference<List<FosunDebtContractHistory1Entity>>(){};
        List<FosunDebtContractHistory1Entity> fosunDebtContractHistory1EntityList =objectMapper.readValue(historyEntity,ref);
        //List<FosunDebtContractHistoryEntity> fosunDebtContractHistoryEntityList=JSON.parseObject(historyEntity,clss<>);
        //List<FosunDebtContractHistoryEntity> fosunDebtContractHistoryEntityList=new ArrayList<>();
        //JSONObject jsonObject = JSON.parseObject(historyEntity);
        //Map<String, Object> reflect = JSONHelper.reflect(jsonObject);
        //ArrayList<LinkedHashMap> invoicelist = (ArrayList) reflect.get("data");
        if (fosunDebtContractHistory1EntityList !=null&& fosunDebtContractHistory1EntityList.size()>0)
        {
            Date verisondate= fosunDebtContractHistory1EntityList.get(0).getHistoryversiondate();
            for (FosunDebtContractHistory1Entity item: fosunDebtContractHistory1EntityList)
            {
                boolean isexist=fosunDebtContractHistoryRepository.existsByIdAndHistoryversiondate(item.getId(),item.getHistoryversiondate());
                if (isexist)
                {
                    fosunDebtContractHistoryRepository.deleteByIdAndHistoryversiondate(item.getId(),item.getHistoryversiondate());
                }

                fosunDebtContractHistoryRepository.save(item);
            }
            List<FosunDebtContractHistory1Entity> fosunDebtContractHistory11EntityList =fosunDebtContractHistoryRepository.findAll((Specification<FosunDebtContractHistory1Entity>) (root, query, cb) -> {
                Predicate where = cb.and();
                if (!"".equals(finalVersionda)&&finalVersionda!=null)
                {
                    Predicate p=cb.equal(root.get("historyversiondate"),finalVersionda);
                    where =cb.and(where,p);
                }
                if (!"".equals(com_name)&&com_name!=null)
                {
                    Predicate p=cb.equal(root.get("com_name"),com_name);
                    where =cb.and(where,p);
                }
                if (!"".equals(sec_name)&&sec_name!=null)
                {
                    Predicate p=cb.equal(root.get("sec_name"),sec_name);
                    where =cb.and(where,p);
                }
                if (!"".equals(bondtype)&&bondtype!=null)
                {
                    Predicate p=cb.equal(root.get("bondtype"),bondtype);
                    where =cb.and(where,p);
                }
                if (!"".equals(finalCarryda)&&finalCarryda!=null)
                {
                    Predicate p=cb.equal(root.get("carrydate"),finalCarryda);
                    where =cb.and(where,p);
                }
                if (!"".equals(finalMaturityda)&&finalMaturityda!=null)
                {
                    Predicate p=cb.equal(root.get("maturitydate"),finalMaturityda);
                    where =cb.and(where,p);
                }
                else
                {

                    Predicate p=cb.greaterThanOrEqualTo(root.get("maturitydate"),finalnowmaturityda);
                    where =cb.and(where,p);
                }

                return where;
            });
            if (fosunDebtContractHistory11EntityList !=null&& fosunDebtContractHistory11EntityList.size()>0)
            {
                return  result.ok(fosunDebtContractHistory11EntityList);
            }

        }
        return result.ok();
    }

    private  List<FosunDebtContractHistory1Entity> getFosunDebtContractHisList(Date versionDate, String com_name,String sec_name, String bondtype, Date carrydate, Date maturitydate,Date nowmaturitydate,Integer rebackcount)
    {

        List<FosunDebtContractHistory1Entity> fosunDebtContractHistory1EntityList =fosunDebtContractHistoryRepository.findAll((Specification<FosunDebtContractHistory1Entity>) (root, query, cb) -> {
            Predicate where = cb.and();
            if (!"".equals(versionDate)&&versionDate!=null)
            {
                Predicate p=cb.equal(root.get("historyversiondate"),versionDate);
                where =cb.and(where,p);
            }
            if (!"".equals(com_name)&&com_name!=null)
            {
                Predicate p=cb.equal(root.get("com_name"),com_name);
                where =cb.and(where,p);
            }
            if (!"".equals(sec_name)&&sec_name!=null)
            {
                Predicate p=cb.equal(root.get("sec_name"),sec_name);
                where =cb.and(where,p);
            }
            if (!"".equals(bondtype)&&bondtype!=null)
            {
                Predicate p=cb.equal(root.get("bondtype"),bondtype);
                where =cb.and(where,p);
            }
            if (!"".equals(carrydate)&&carrydate!=null)
            {
                Predicate p=cb.equal(root.get("carrydate"),carrydate);
                where =cb.and(where,p);
            }
            if (!"".equals(maturitydate)&&maturitydate!=null)
            {
                Predicate p=cb.equal(root.get("maturitydate"),maturitydate);
                where =cb.and(where,p);
            }
            else
            {

                Predicate p=cb.greaterThanOrEqualTo(root.get("maturitydate"),nowmaturitydate);
                where =cb.and(where,p);
            }

            return where;
        });
        if (fosunDebtContractHistory1EntityList !=null&& fosunDebtContractHistory1EntityList.size()>0)
        {
            return fosunDebtContractHistory1EntityList;
        }
        else
        {
            rebackcount++;
            //获取前一天数据
            if (rebackcount<=91)//小于91天就查询历史数据
            {
                Calendar c=Calendar.getInstance();
                c.setTime(versionDate);
                c.add(Calendar.DAY_OF_MONTH,-1);
                getFosunDebtContractHisList(c.getTime(),com_name,sec_name,bondtype,carrydate,maturitydate,nowmaturitydate,rebackcount);
            }

        }
        return null;

    }

}
