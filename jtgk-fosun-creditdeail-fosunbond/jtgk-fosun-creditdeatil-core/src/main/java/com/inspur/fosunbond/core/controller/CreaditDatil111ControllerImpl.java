package com.inspur.fosunbond.core.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspur.fosunbond.core.domain.entity.*;
import com.inspur.fosunbond.core.domain.repository.*;
import com.inspur.fosunbond.core.domain.result.Result;
import com.inspur.fosunbond.core.domain.service.Creditdeatil111Service;
import com.inspur.fosunbond.core.domain.repository.BaseRepository;
import com.inspur.fosunbond.core.domain.service.JtgkFosunbondFosunSynchroMiddleTableForBond;
import io.iec.edp.caf.commons.utils.SpringBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private FosunIdenticalissUer1Repository fosunIdenticalissUer1Repository;
    @Autowired
    private JtgkFosunbondFosunSynchroMiddleTableForBond jtgkFosunbondFosunSynchroMiddleTableForBond;
    @Autowired
    private JtgkFosunbondFosunCompanySortRepository jtgkFosunbondFosunCompanySortRepository;
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
        String isexpired=jsonNode.get("isexpired").asText();
        Date nowdate=new Date();
        String nowDateStr=new SimpleDateFormat("yyyy-MM-dd").format(nowdate);
        Date nowmaturityda=new SimpleDateFormat("yyyy-MM-dd").parse(nowDateStr);
        if (versionDate=="")
        {
//            Date versionda=null;
//            if (!versionDate.isEmpty())
//            {
//                versionda=new SimpleDateFormat("yyyy-MM-dd").parse(versionDate);
//            }
            //Date carryda=null;
            Date begincarryda=null;
            Date endcarryda=null;
            if (!carrydate.isEmpty())
            {
                String[] carrydatearray=carrydate.split(" - ");
                String  begincarraydate=carrydatearray[0];
                String  endcarraydate=carrydatearray[1];
                //carryda=new SimpleDateFormat("yyyy-MM-dd").parse(carrydate);
                begincarryda=new SimpleDateFormat("yyyy-MM-dd").parse(begincarraydate);
                endcarryda=new SimpleDateFormat("yyyy-MM-dd").parse(endcarraydate);

            }
            //Date maturityda=null;
            Date beginmaturityda=null;
            Date endmaturityda=null;
            if (!maturitydate.isEmpty())
            {
                String[] maturityarray=maturitydate.split(" - ");
                String  beginmaturitydate=maturityarray[0];
                String  endmaturitydate=maturityarray[1];
                //maturityda = new SimpleDateFormat("yyyy-MM-dd").parse(maturitydate);
                beginmaturityda=new SimpleDateFormat("yyyy-MM-dd").parse(beginmaturitydate);
                endmaturityda=new SimpleDateFormat("yyyy-MM-dd").parse(endmaturitydate);
            }


            //Date finalVersionda = versionda;
            //Date finalCarryda = carryda;
            Date finalbeginCarryda=begincarryda;
            Date finalendCarryda=endcarryda;
            //Date finalMaturityda = maturityda;
            Date finalbeginMaturityda=beginmaturityda;
            Date finalendMaturityda=endmaturityda;
            Date finalnowmaturityda=nowmaturityda;
            List<FosunDebtContract1Entity> fosunDebtContract1EntityList =fosunDebtContractRepository.findAll((Specification<FosunDebtContract1Entity>) (root, query, cb) -> {
                        Predicate where = cb.and();
                        //if (!"".equals(finalVersionda)&& finalVersionda !=null)
                        if (!"".equals(versionDate) && versionDate != null) {
                            //Predicate p=cb.equal(root.get("historyversiondate"), finalVersionda);
                            List<Date> versiondatelist = new ArrayList<>();
                            String[] versiondatestr = versionDate.split(";");
                            for (String versiond : versiondatestr) {
                                try {
                                    versiondatelist.add(new SimpleDateFormat("yyyy-MM-dd").parse(versiond));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                            Predicate p = root.get("historyversiondate").in(versiondatelist);
                            where = cb.and(where, p);
                        }
                        if (!"".equals(com_name) && com_name != null) {
//                    //Predicate p=cb.equal(root.get("issuershortened"),com_name);//改为简称
//                    Predicate p=cb.like(root.get("issuershortened"),"%"+com_name+"%");//改为简称
//                    where =cb.and(where,p);


                            Predicate p1 = cb.and();
                            List<Predicate> predicateList = new ArrayList<>();
                            String[] com_namelist = com_name.split(";");
                            for (String comname : com_namelist) {
                                Predicate p = cb.like(root.get("issuershortened"), "%" + comname.replaceAll("/", "//").replaceAll("_", "\\_").replaceAll("%", "\\%") + "%");
                                predicateList.add(p);
                            }
                            Predicate[] p2 = new Predicate[com_namelist.length];
                            predicateList.toArray(p2);
                            p1.getExpressions().add(cb.or(p2));

                            where = cb.and(where, p1);
                        }
                        if (!"".equals(sec_name) && sec_name != null) {
                            //Predicate p=cb.like(root.get("sec_name"),"%"+sec_name.replaceAll("/","//").replaceAll("_","\\_").replaceAll("%","\\%")+"%");
                            Predicate p1 = cb.and();
                            List<Predicate> predicateList = new ArrayList<>();
                            String[] sec_namelist = sec_name.split(";");
                            for (String secname : sec_namelist) {
                                Predicate p = cb.like(root.get("sec_name"), "%" + secname.replaceAll("/", "//").replaceAll("_", "\\_").replaceAll("%", "\\%") + "%");
                                predicateList.add(p);
                            }
                            Predicate[] p2 = new Predicate[sec_namelist.length];
                            predicateList.toArray(p2);
                            p1.getExpressions().add(cb.or(p2));

                            where = cb.and(where, p1);

                        }
                        if (!"".equals(bondtype) && bondtype != null) {
//                    Predicate p=cb.like(root.get("bondtype"),"%"+bondtype+"%");
//                    where =cb.and(where,p);

                            Predicate p1 = cb.and();
                            List<Predicate> predicateList = new ArrayList<>();
                            String[] bondtypelist = bondtype.split(";");
                            for (String bdtype : bondtypelist) {
                                Predicate p = cb.like(root.get("bondtype"), "%" + bdtype.replaceAll("/", "//").replaceAll("_", "\\_").replaceAll("%", "\\%") + "%");
                                predicateList.add(p);
                            }
                            Predicate[] p2 = new Predicate[bondtypelist.length];
                            predicateList.toArray(p2);
                            p1.getExpressions().add(cb.or(p2));

                            where = cb.and(where, p1);
                        }
                        //if (!"".equals(finalCarryda)&& finalCarryda !=null)
                        if (!"".equals(finalbeginCarryda) && finalbeginCarryda != null) {
                            //Predicate p=cb.equal(root.get("carrydate"), finalCarryda);
                            Predicate p = cb.between(root.get("carrydate"), finalbeginCarryda, finalendCarryda);
                            where = cb.and(where, p);
                        }
                        //if (!"".equals(finalMaturityda)&& finalMaturityda !=null)
                        if (!"".equals(finalbeginMaturityda) && finalbeginMaturityda != null) {
                            //Predicate p=cb.equal(root.get("maturitydate"), finalMaturityda);
                            Predicate p = cb.between(root.get("maturitydate"), finalbeginMaturityda, finalendMaturityda);
                            where = cb.and(where, p);
                        } else {
                            if ("1".equals(isexpired)) {
                                Predicate p = cb.lessThan(root.get("maturitydate"), finalnowmaturityda);
                                where = cb.and(where, p);
                            } else if ("0;1".equals(isexpired)) {

                            } else {
                                Predicate p = cb.greaterThanOrEqualTo(root.get("maturitydate"), finalnowmaturityda);
                                where = cb.and(where, p);
                            }

                        }

                        return where;
                    });
            //}, Sort.by(Sort.Direction.ASC,"fosunCompanySortEntity.sortnum"));

            //重新组合issue_regnumber注册文号
            List<FosunDebtContract1Entity> resetfosunDebtContract1EntityList=new ArrayList<>();
            if (fosunDebtContract1EntityList!=null&&fosunDebtContract1EntityList.size()>0)
            {
                //按照sec_name分组并获取更新时间最新的一条
                Map<String, FosunDebtContract1Entity> collect = fosunDebtContract1EntityList.stream().collect(
                        Collectors.groupingBy(FosunDebtContract1Entity::getWindcode,
                                Collectors.collectingAndThen(Collectors.reducing((c1, c2) -> c1.getUpdatetime().getTime() > c2.getUpdatetime().getTime() ? c1 : c2),
                                        Optional::get)));
                List<FosunDebtContract1Entity> fosunDebtContract1EntityListgroup = new ArrayList<>(collect.values());

                List<FosunDebtContractHistory1Entity> fosunDebtContractHistory1EntityList=fosunDebtContractHistoryRepository.findAll();
                for (FosunDebtContract1Entity fosunDebtContract1Entity:fosunDebtContract1EntityListgroup)
                {
                    String sourceid=fosunDebtContract1Entity.getSourceid();
                    if (sourceid!=null&&sourceid!="")
                    {

                        //List<FosunDebtContractHistory1Entity> fosunDebtContractHistory1EntityList=fosunDebtContractHistoryRepository.findAllBySourceidOrderByHistoryversiondateDesc(sourceid);
                        List<FosunDebtContractHistory1Entity>   fosunDebtContractHistory1EntityList1 =fosunDebtContractHistory1EntityList.stream().
                                filter(i->sourceid.equals(i.getSourceid())).
                                sorted(Comparator.comparing(FosunDebtContractHistory1Entity::getHistoryversiondate).reversed()).
                                collect(Collectors.toList());
                        if (fosunDebtContractHistory1EntityList1!=null&&fosunDebtContractHistory1EntityList1.size()>0)
                        {
                            String issue_regnumber="";
                            for (FosunDebtContractHistory1Entity history1Entity:fosunDebtContractHistory1EntityList1)
                            {
                                String hisissue_regnumber=history1Entity.getIssue_regnumber();
                                if (hisissue_regnumber!=null&&hisissue_regnumber!="")
                                {
                                    issue_regnumber=hisissue_regnumber;
                                    break;
                                }
                            }
                           String oriissue_regnumber=fosunDebtContract1Entity.getIssue_regnumber();
                            if ("".equals(oriissue_regnumber)||oriissue_regnumber==null)
                            {
                                fosunDebtContract1Entity.setIssue_regnumber(issue_regnumber);
                            }



                        }
                    }
                    //设置排序实体信息
                    JtgkFosunbondFosunCompanySortEntity jtgkFosunbondFosunCompanySortEntity=new JtgkFosunbondFosunCompanySortEntity();
                    String issuershortened=fosunDebtContract1Entity.getIssuershortened();//获取简称
                    List<JtgkFosunbondFosunCompanySortEntity> jtgkFosunbondFosunCompanySortEntityList=jtgkFosunbondFosunCompanySortRepository.findAllByShortname(issuershortened);
                    if (jtgkFosunbondFosunCompanySortEntityList!=null&&jtgkFosunbondFosunCompanySortEntityList.size()>0)
                    {
                        fosunDebtContract1Entity.setSortnum(jtgkFosunbondFosunCompanySortEntityList.get(0).getSortnum());
                    }
                    else
                    {
                        jtgkFosunbondFosunCompanySortEntity.setId("9999");
                        jtgkFosunbondFosunCompanySortEntity.setSortnum("9999");
                        jtgkFosunbondFosunCompanySortEntity.setShortname("9999");
                        fosunDebtContract1Entity.setSortnum(jtgkFosunbondFosunCompanySortEntity.getSortnum());
                    }
                    resetfosunDebtContract1EntityList.add(fosunDebtContract1Entity);
                }
            }
            //将结果按照排序实体的排序字段排序重新组合
            List<FosunDebtContract1Entity>   resetresetfosunDebtContract1EntityList =resetfosunDebtContract1EntityList.stream().

                    sorted(Comparator.comparing(FosunDebtContract1Entity::getSortnum)).
                    collect(Collectors.toList());
            //return  result.ok(fosunDebtContract1EntityList);
            //return  result.ok(resetfosunDebtContract1EntityList);
             return  result.ok(resetresetfosunDebtContract1EntityList);
        }
        else
        {
//            Date versionda=null;
//            if (!versionDate.isEmpty())
//            {
//                versionda=new SimpleDateFormat("yyyy-MM-dd").parse(versionDate);
//            }

            //Date carryda=null;
            Date begincarryda=null;
            Date endcarryda=null;
            if (!carrydate.isEmpty())
            {
                String[] carrydatearray=carrydate.split(" - ");
                String  begincarraydate=carrydatearray[0];
                String  endcarraydate=carrydatearray[1];
                //carryda=new SimpleDateFormat("yyyy-MM-dd").parse(carrydate);
                begincarryda=new SimpleDateFormat("yyyy-MM-dd").parse(begincarraydate);
                endcarryda=new SimpleDateFormat("yyyy-MM-dd").parse(endcarraydate);

            }
            //Date maturityda=null;
            Date beginmaturityda=null;
            Date endmaturityda=null;
            if (!maturitydate.isEmpty())
            {
                String[] maturityarray=maturitydate.split(" - ");
                String  beginmaturitydate=maturityarray[0];
                String  endmaturitydate=maturityarray[1];
                //maturityda = new SimpleDateFormat("yyyy-MM-dd").parse(maturitydate);
                beginmaturityda=new SimpleDateFormat("yyyy-MM-dd").parse(beginmaturitydate);
                endmaturityda=new SimpleDateFormat("yyyy-MM-dd").parse(endmaturitydate);
            }
            Integer rebackcount=0;
            List<FosunDebtContractHistory1Entity> fosunDebtContractHistory1EntityList =getFosunDebtContractHisList(versionDate,com_name,sec_name,bondtype,begincarryda,endcarryda,beginmaturityda,endmaturityda,nowmaturityda,rebackcount,isexpired);
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
        String isexpired=jsonNode.get("isexpired").asText();
        Date nowdate=new Date();
        String nowDateStr=new SimpleDateFormat("yyyy-MM-dd").format(nowdate);
        Date nowmaturityda=new SimpleDateFormat("yyyy-MM-dd").parse(nowDateStr);
        Date versionda=null;
//        if (!versionDate.isEmpty())
//        {
//            versionda=new SimpleDateFormat("yyyy-MM-dd").parse(versionDate);
//        }
        //Date carryda=null;
        Date begincarryda=null;
        Date endcarryda=null;
        if (!carrydate.isEmpty())
        {
            String[] carrydatearray=carrydate.split(" - ");
            String  begincarraydate=carrydatearray[0];
            String  endcarraydate=carrydatearray[1];
            //carryda=new SimpleDateFormat("yyyy-MM-dd").parse(carrydate);
            begincarryda=new SimpleDateFormat("yyyy-MM-dd").parse(begincarraydate);
            endcarryda=new SimpleDateFormat("yyyy-MM-dd").parse(endcarraydate);

        }
        //Date maturityda=null;
        Date beginmaturityda=null;
        Date endmaturityda=null;
        if (!maturitydate.isEmpty())
        {
            String[] maturityarray=maturitydate.split(" - ");
            String  beginmaturitydate=maturityarray[0];
            String  endmaturitydate=maturityarray[1];
            //maturityda = new SimpleDateFormat("yyyy-MM-dd").parse(maturitydate);
            beginmaturityda=new SimpleDateFormat("yyyy-MM-dd").parse(beginmaturitydate);
            endmaturityda=new SimpleDateFormat("yyyy-MM-dd").parse(endmaturitydate);
        }


        Date finalVersionda = versionda;
        //Date finalCarryda = carryda;
        Date finalbeginCarryda=begincarryda;
        Date finalendCarryda=endcarryda;
        //Date finalMaturityda = maturityda;
        Date finalbeginMaturityda=beginmaturityda;
        Date finalendMaturityda=endmaturityda;
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
                //初始数据保存原始利率
                {
                  //更新源表原始利率
                  fosunDebtContractRepository.updateDataByID(item.getOriginalrate(),item.getId());
                  //更新所有历史版本表原始利率
                  fosunDebtContractHistoryRepository.updateDataByID(item.getOriginalrate(),item.getId());
                }

                boolean isexist=fosunDebtContractHistoryRepository.existsByIdAndHistoryversiondate(item.getId(),item.getHistoryversiondate());
                if (isexist)
                {
                    fosunDebtContractHistoryRepository.deleteByIdAndHistoryversiondate(item.getId(),item.getHistoryversiondate());
                }

                fosunDebtContractHistoryRepository.save(item);
            }
            List<FosunDebtContractHistory1Entity> fosunDebtContractHistory11EntityList =fosunDebtContractHistoryRepository.findAll((Specification<FosunDebtContractHistory1Entity>) (root, query, cb) -> {
                Predicate where = cb.and();
                if (!"".equals(versionDate)&&versionDate!=null)
                {
                    //Predicate p=cb.equal(root.get("historyversiondate"),finalVersionda);
                    //where =cb.and(where,p);
                    List<Date> versiondatelist=new ArrayList<>();
                    String[] versiondatestr=versionDate.split(";");
                    for (String versiond:versiondatestr)
                    {
                        try {
                            versiondatelist.add(new SimpleDateFormat("yyyy-MM-dd").parse(versiond));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                    Predicate p=root.get("historyversiondate").in(versiondatelist);
                    where =cb.and(where,p);
                }
                if (!"".equals(com_name)&&com_name!=null)
                {
//                    Predicate p=cb.like(root.get("issuershortened"),"%"+com_name+"%");//改为简称
//                    where =cb.and(where,p);

                    Predicate p1=cb.and();
                    List<Predicate> predicateList=new ArrayList<>();
                    String[] com_namelist=com_name.split(";");
                    for (String comname:com_namelist)
                    {
                        Predicate p=cb.like(root.get("issuershortened"),"%"+comname.replaceAll("/","//").replaceAll("_","\\_").replaceAll("%","\\%")+"%");
                        predicateList.add(p);
                    }
                    Predicate[] p2=new Predicate[com_namelist.length];
                    predicateList.toArray(p2);
                    p1.getExpressions().add(cb.or(p2));

                    where =cb.and(where,p1);
                }
                if (!"".equals(sec_name)&&sec_name!=null)
                {
                    //Predicate p=cb.like(root.get("sec_name"),"%"+sec_name+"%");
                    //where =cb.and(where,p);
                    Predicate p1=cb.and();
                    List<Predicate> predicateList=new ArrayList<>();
                    String[] sec_namelist=sec_name.split(";");
                    for (String secname:sec_namelist)
                    {
                        Predicate p=cb.like(root.get("sec_name"),"%"+secname.replaceAll("/","//").replaceAll("_","\\_").replaceAll("%","\\%")+"%");
                        predicateList.add(p);
                    }
                    Predicate[] p2=new Predicate[sec_namelist.length];
                    predicateList.toArray(p2);
                    p1.getExpressions().add(cb.or(p2));

                    where =cb.and(where,p1);
                }
                if (!"".equals(bondtype)&&bondtype!=null)
                {
//                    Predicate p=cb.like(root.get("bondtype"),"%"+bondtype+"%");
//                    where =cb.and(where,p);

                    Predicate p1=cb.and();
                    List<Predicate> predicateList=new ArrayList<>();
                    String[] bondtypelist=bondtype.split(";");
                    for (String bdtype:bondtypelist)
                    {
                        Predicate p=cb.like(root.get("bondtype"),"%"+bdtype.replaceAll("/","//").replaceAll("_","\\_").replaceAll("%","\\%")+"%");
                        predicateList.add(p);
                    }
                    Predicate[] p2=new Predicate[bondtypelist.length];
                    predicateList.toArray(p2);
                    p1.getExpressions().add(cb.or(p2));

                    where =cb.and(where,p1);
                }
                if (!"".equals(finalbeginCarryda)&&finalbeginCarryda!=null)
                {
                    Predicate p=cb.between(root.get("carrydate"),finalbeginCarryda,finalendCarryda);
                    where =cb.and(where,p);
                }
                if (!"".equals(finalbeginMaturityda)&&finalbeginMaturityda!=null)
                {
                    Predicate p=cb.between(root.get("maturitydate"),finalbeginMaturityda,finalendMaturityda);
                    where =cb.and(where,p);
                }
                else
                {
                    if ("1".equals(isexpired))
                    {
                        Predicate p=cb.lessThan(root.get("maturitydate"), finalnowmaturityda);
                        where =cb.and(where,p);
                    }
                    else if("0;1".equals(isexpired))
                    {

                    }
                    else
                    {
                        Predicate p=cb.greaterThanOrEqualTo(root.get("maturitydate"), finalnowmaturityda);
                        where =cb.and(where,p);
                    }

                }

                return where;
            });
            if (fosunDebtContractHistory11EntityList !=null&& fosunDebtContractHistory11EntityList.size()>0)
            {
                List<FosunDebtContractHistory1Entity> resetfosunDebtContractHistoryEntityList=new ArrayList<>();
                for (FosunDebtContractHistory1Entity fosunDebtContractHistory1Entity:fosunDebtContractHistory11EntityList)
                {
                    JtgkFosunbondFosunCompanySortEntity jtgkFosunbondFosunCompanySortEntity=new JtgkFosunbondFosunCompanySortEntity();
                    String issuershortened=fosunDebtContractHistory1Entity.getIssuershortened();//获取简称
                    List<JtgkFosunbondFosunCompanySortEntity> jtgkFosunbondFosunCompanySortEntityList=jtgkFosunbondFosunCompanySortRepository.findAllByShortname(issuershortened);
                    if (jtgkFosunbondFosunCompanySortEntityList!=null&&jtgkFosunbondFosunCompanySortEntityList.size()>0)
                    {
                        fosunDebtContractHistory1Entity.setSortnum(jtgkFosunbondFosunCompanySortEntityList.get(0).getSortnum());
                    }
                    else
                    {
                        jtgkFosunbondFosunCompanySortEntity.setId("9999");
                        jtgkFosunbondFosunCompanySortEntity.setSortnum("9999");
                        jtgkFosunbondFosunCompanySortEntity.setShortname("9999");
                        fosunDebtContractHistory1Entity.setSortnum(jtgkFosunbondFosunCompanySortEntity.getSortnum());
                    }
                    resetfosunDebtContractHistoryEntityList.add(fosunDebtContractHistory1Entity);
                }
                //将结果按照排序实体的排序字段排序重新组合
                List<FosunDebtContractHistory1Entity>   resetresetfosunDebtContractHistoryEntityList =resetfosunDebtContractHistoryEntityList.stream().

                        sorted(Comparator.comparing(FosunDebtContractHistory1Entity::getSortnum)).
                        collect(Collectors.toList());
                return  result.ok(resetresetfosunDebtContractHistoryEntityList);
            }

        }
        return result.ok();
    }

    @Override
    public String syncBondMsgFromMiddleTable(JsonNode jsonNode) {
        //JtgkFosunbondFosunSynchroMiddleTableForBond jtgkFosunbondFosunSynchroMiddleTableForBond=new JtgkFosunbondFosunSynchroMiddleTableForBond();
        return jtgkFosunbondFosunSynchroMiddleTableForBond.SyncBondMsgFromMiddleTable1(jsonNode);
        //return  null;
    }

    private  List<FosunDebtContractHistory1Entity> getFosunDebtContractHisList(String versionDate, String com_name,String sec_name, String bondtype, Date begincarrydate,Date endcarrydate, Date beginmaturitydate,Date endmaturitydate,Date nowmaturitydate,Integer rebackcount,String isexpired) throws ParseException {

        List<FosunDebtContractHistory1Entity> fosunDebtContractHistory1EntityList =fosunDebtContractHistoryRepository.findAll((Specification<FosunDebtContractHistory1Entity>) (root, query, cb) -> {
            Predicate where = cb.and();
            if (!"".equals(versionDate)&&versionDate!=null)
            {
                List<Date> versiondatelist=new ArrayList<>();
                String[] versiondatestr=versionDate.split(";");
                for (String versiond:versiondatestr)
                {
                    try {
                        versiondatelist.add(new SimpleDateFormat("yyyy-MM-dd").parse(versiond));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                Predicate p=root.get("historyversiondate").in(versiondatelist);
                where =cb.and(where,p);
                //Predicate p=cb.equal(root.get("historyversiondate"),versionDate);
                //where =cb.and(where,p);
            }
            if (!"".equals(com_name)&&com_name!=null)
            {
                //Predicate p=cb.equal(root.get("issuershortened"),com_name);//改为简称
//                Predicate p=cb.like(root.get("issuershortened"),"%"+com_name+"%");//改为简称
//                where =cb.and(where,p);
                Predicate p1=cb.and();
                List<Predicate> predicateList=new ArrayList<>();
                String[] com_namelist=com_name.split(";");
                for (String comname:com_namelist)
                {
                    Predicate p=cb.like(root.get("issuershortened"),"%"+comname.replaceAll("/","//").replaceAll("_","\\_").replaceAll("%","\\%")+"%");
                    predicateList.add(p);
                }
                Predicate[] p2=new Predicate[com_namelist.length];
                predicateList.toArray(p2);
                p1.getExpressions().add(cb.or(p2));

                where =cb.and(where,p1);
            }
            if (!"".equals(sec_name)&&sec_name!=null)
            {
                //Predicate p=cb.like(root.get("sec_name"),"%"+sec_name+"%");
                //where =cb.and(where,p);
                Predicate p1=cb.and();
                List<Predicate> predicateList=new ArrayList<>();
                String[] sec_namelist=sec_name.split(";");
                for (String secname:sec_namelist)
                {
                    Predicate p=cb.like(root.get("sec_name"),"%"+secname.replaceAll("/","//").replaceAll("_","\\_").replaceAll("%","\\%")+"%");
                    predicateList.add(p);
                }
                Predicate[] p2=new Predicate[sec_namelist.length];
                predicateList.toArray(p2);
                p1.getExpressions().add(cb.or(p2));

                where =cb.and(where,p1);
            }
            if (!"".equals(bondtype)&&bondtype!=null)
            {
//                Predicate p=cb.like(root.get("bondtype"),"%"+bondtype+"%");
//                where =cb.and(where,p);

                Predicate p1=cb.and();
                List<Predicate> predicateList=new ArrayList<>();
                String[] bondtypelist=bondtype.split(";");
                for (String bdtype:bondtypelist)
                {
                    Predicate p=cb.like(root.get("bondtype"),"%"+bdtype.replaceAll("/","//").replaceAll("_","\\_").replaceAll("%","\\%")+"%");
                    predicateList.add(p);
                }
                Predicate[] p2=new Predicate[bondtypelist.length];
                predicateList.toArray(p2);
                p1.getExpressions().add(cb.or(p2));

                where =cb.and(where,p1);
            }
            if (!"".equals(begincarrydate)&&begincarrydate!=null)
            {
                Predicate p=cb.between(root.get("carrydate"),begincarrydate,endcarrydate);
                where =cb.and(where,p);
            }
            if (!"".equals(beginmaturitydate)&&beginmaturitydate!=null)
            {
                Predicate p=cb.between(root.get("maturitydate"),beginmaturitydate,endmaturitydate);
                where =cb.and(where,p);
            }
            else
            {
                if ("1".equals(isexpired))
                {
                    Predicate p=cb.lessThan(root.get("maturitydate"), nowmaturitydate);
                    where =cb.and(where,p);
                }
                else if("0;1".equals(isexpired))
                {

                }
                else
                {
                    Predicate p=cb.greaterThanOrEqualTo(root.get("maturitydate"), nowmaturitydate);
                    where =cb.and(where,p);
                }

            }

            return where;
        });
        if (fosunDebtContractHistory1EntityList !=null&& fosunDebtContractHistory1EntityList.size()>0)
        {
            List<FosunDebtContractHistory1Entity> resetfosunDebtContractHistoryEntityList=new ArrayList<>();
            for (FosunDebtContractHistory1Entity fosunDebtContractHistory1Entity:fosunDebtContractHistory1EntityList)
            {
                JtgkFosunbondFosunCompanySortEntity jtgkFosunbondFosunCompanySortEntity=new JtgkFosunbondFosunCompanySortEntity();
                String issuershortened=fosunDebtContractHistory1Entity.getIssuershortened();//获取简称
                List<JtgkFosunbondFosunCompanySortEntity> jtgkFosunbondFosunCompanySortEntityList=jtgkFosunbondFosunCompanySortRepository.findAllByShortname(issuershortened);
                if (jtgkFosunbondFosunCompanySortEntityList!=null&&jtgkFosunbondFosunCompanySortEntityList.size()>0)
                {
                    fosunDebtContractHistory1Entity.setSortnum(jtgkFosunbondFosunCompanySortEntityList.get(0).getSortnum());
                }
                else
                {
                    jtgkFosunbondFosunCompanySortEntity.setId("9999");
                    jtgkFosunbondFosunCompanySortEntity.setSortnum("9999");
                    jtgkFosunbondFosunCompanySortEntity.setShortname("9999");
                    fosunDebtContractHistory1Entity.setSortnum(jtgkFosunbondFosunCompanySortEntity.getSortnum());
                }
                resetfosunDebtContractHistoryEntityList.add(fosunDebtContractHistory1Entity);
            }
            //将结果按照排序实体的排序字段排序重新组合
            List<FosunDebtContractHistory1Entity>   resetresetfosunDebtContractHistoryEntityList =resetfosunDebtContractHistoryEntityList.stream().

                    sorted(Comparator.comparing(FosunDebtContractHistory1Entity::getSortnum)).
                    collect(Collectors.toList());

            //return fosunDebtContractHistory1EntityList;
            return  resetresetfosunDebtContractHistoryEntityList;
        }
        else
        {
            rebackcount++;
            //获取前一天数据
            if (rebackcount<=91)//小于91天就查询历史数据
            {
                Calendar c=Calendar.getInstance();
                Date  versionda1=new SimpleDateFormat("yyyy-MM-dd").parse(versionDate.split(";")[versionDate.split(";").length-1]);
                c.setTime(versionda1);
                c.add(Calendar.DAY_OF_MONTH,-1);
               //Date resetdate=new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
                String resetdate=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
                return getFosunDebtContractHisList(resetdate,com_name,sec_name,bondtype,begincarrydate,endcarrydate,beginmaturitydate,endmaturitydate,nowmaturitydate,rebackcount,isexpired);
            }

        }
        return fosunDebtContractHistory1EntityList;

    }

}
