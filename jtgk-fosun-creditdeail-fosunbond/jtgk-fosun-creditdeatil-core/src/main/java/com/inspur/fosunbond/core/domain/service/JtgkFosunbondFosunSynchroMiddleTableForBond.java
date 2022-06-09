package com.inspur.fosunbond.core.domain.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.inspur.edp.cdf.component.api.annotation.GspComponent;
import com.inspur.fosunbond.core.domain.entity.*;
import com.inspur.fosunbond.core.domain.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@GspComponent(id="AD2021-AF-KS-ZD-0FOBOND")//定义通用构件由调度任务执行
@Slf4j
@Service
public class JtgkFosunbondFosunSynchroMiddleTableForBond
{
    @Autowired
    private JtgkFosunbondFosunDebtContractRepository fosunDebtContractRepository;
    @Autowired
    private JtgkFosunbondFosunDebtSecondaryMarketRepository fosunDebtSecondaryMarketRepository;
    @Autowired
    private JtgkFosunbondFosunDebtValuationRepository fosunDebtValuationRepository;
    @Autowired
    private JtgkFosunbondFosunIdenticalissUerRepository fosunIdenticalissUerRepository;
    @Autowired
    private JtgkFosunbondT_Debt_ContractRepository t_debt_contractRepository;
    @Autowired
    private JtgkFosunbondT_Debt_SecondaryMarketRepository t_debt_secondaryMarketRepository;
    @Autowired
    private JtgkFosunbondT_Debt_ValuationRepository t_debt_valuationRepository;
    @Autowired
    private JtgkFosunbondT_IdenticalissUerRepository t_identicalissUerRepository;
    //@Transactional(rollbackFor ={Exception.class})
    public String  SyncBondMsgFromMiddleTable()
    {
        log.error("从中间表同步债券信息0");
        try
        {
//            String sql="select * from fosunidenticalissuer";
//            EntityManager manager= SpringBeanUtils.getBean(EntityManager.class);
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            //List<T_Debt_ContractEntity> t_debt_contractEntityList=t_debt_contractRepository.findAll();
            //List<T_Debt_SecondaryMarketEntity> t_debt_secondaryMarketEntityList123=t_debt_secondaryMarketRepository.findAll();
            String predate=dateFormat.format(getPreDay(new Date()));
            String nowdate=dateFormat.format(new Date());
            log.error(nowdate);
            //log.error(predate);
            //Date nowdate=new Date();
            //String nowDateStr=new SimpleDateFormat("yyyy-MM-dd").format(nowdate);
            Date querydate=new SimpleDateFormat("yyyy-MM-dd").parse(predate);
            //List<T_Debt_ContractEntity> t_debt_contractEntityList=t_debt_contractRepository.findAll();
            List<JtgkFosunbondT_Debt_ContractEntity> t_debt_contractEntityList=t_debt_contractRepository.getdatabyupdatetime(nowdate);
            log.error("从中间表同步债券信息1");
            List<JtgkFosunbondFosunDebtContractEntity> fosunDebtContractEntityList=new ArrayList<>();
            if (t_debt_contractEntityList!=null&&t_debt_contractEntityList.size()>0)
            {

                log.error("从中间表同步债券信息2");

                for (JtgkFosunbondT_Debt_ContractEntity contractEntity:t_debt_contractEntityList)
                {

                    String updatetime=dateFormat.format(contractEntity.getUpdatetime());



                    //if (updatetime.equals(predate))//如果更新日期为前一天则更新数据
                    if (updatetime.equals(nowdate))//如果更新日期为当天则更新日期
                    {
                        String sourceid=contractEntity.getId();
                        JtgkFosunbondFosunDebtContractEntity fosunDebtContractEntityOriginal=fosunDebtContractRepository.findBySourceid(sourceid);
                        String originalId=UUID.randomUUID().toString();
                        if(!ObjectUtils.isEmpty(fosunDebtContractEntityOriginal))
                        {
                            originalId=fosunDebtContractEntityOriginal.getId();
                            fosunDebtContractRepository.deleteById(originalId);
                        }
                        JtgkFosunbondFosunDebtContractEntity fosunDebtContractEntity=new JtgkFosunbondFosunDebtContractEntity();
                        fosunDebtContractEntity.setId(originalId);
                        fosunDebtContractEntity.setAbs_industry1(contractEntity.getAbs_industry1());
                        fosunDebtContractEntity.setActualbenchmark(contractEntity.getActualbenchmark());
                        //fosunDebtContractEntity.setAdvancecredit_desc(contractEntity.getAdvancecredit_desc());
                        fosunDebtContractEntity.setAgency_guarantor(contractEntity.getAgency_guarantor());
                        fosunDebtContractEntity.setAgency_leadunderwriter(contractEntity.getAgency_leadunderwriter());
                        fosunDebtContractEntity.setBondtype(contractEntity.getBondtype());
                        fosunDebtContractEntity.setCarrydate(contractEntity.getCarrydate());
                        fosunDebtContractEntity.setClause(contractEntity.getClause());
                        fosunDebtContractEntity.setClauseabbr(contractEntity.getClauseabbr());
                        fosunDebtContractEntity.setComp_name(contractEntity.getComp_name());
                        fosunDebtContractEntity.setAgency_grnttype(contractEntity.getAgency_grnttype());
                        fosunDebtContractEntity.setCoupon(contractEntity.getCoupon());
                        fosunDebtContractEntity.setCoupondatetxt(contractEntity.getCoupondatetxt());
                        fosunDebtContractEntity.setCouponrate2(contractEntity.getCouponrate2());
                        fosunDebtContractEntity.setCoupontxt(contractEntity.getCoupontxt());
                        fosunDebtContractEntity.setCreatetime(contractEntity.getCreatetime());
                        fosunDebtContractEntity.setCreditrating(contractEntity.getCreditrating());
                        //fosunDebtContractEntity.setCurrentstate(contractEntity.getCurrentstate()==null?"":contractEntity.getCurrentstate());
                        fosunDebtContractEntity.setSec_status(contractEntity.getSec_status());
                        fosunDebtContractEntity.setExch_city(contractEntity.getExch_city());
                        fosunDebtContractEntity.setFullname(contractEntity.getFullname());
                        fosunDebtContractEntity.setIndustry_gics(contractEntity.getIndustry_gics());
                        fosunDebtContractEntity.setInterestfrequency(contractEntity.getInterestfrequency());
                        fosunDebtContractEntity.setIpo_date(contractEntity.getIpo_date());
                        fosunDebtContractEntity.setIssue_announcement(contractEntity.getIssue_announcement());
                        fosunDebtContractEntity.setIssue_firstissue(contractEntity.getIssue_firstissue());
                        fosunDebtContractEntity.setIssue_issuemethod(contractEntity.getIssue_issuemethod());
                        fosunDebtContractEntity.setIssue_issueprice(contractEntity.getIssue_issueprice());
                        fosunDebtContractEntity.setIssue_lastissue(contractEntity.getIssue_lastissue());
                        fosunDebtContractEntity.setIssue_regnumber(contractEntity.getIssue_regnumber());
                        fosunDebtContractEntity.setIssueamount(contractEntity.getIssueamount());
                        fosunDebtContractEntity.setIssuer_rating(contractEntity.getIssuer_rating());
                        //fosunDebtContractEntity.setLast_trade_day(contractEntity.getLast_trade_day());
                        fosunDebtContractEntity.setMaturitydate(contractEntity.getMaturitydate());
                        fosunDebtContractEntity.setMultimktornot(contractEntity.getMultimktornot());
                        fosunDebtContractEntity.setNxcupn(contractEntity.getNxcupn());
                        fosunDebtContractEntity.setNxcupn2(contractEntity.getNxcupn2());
                        fosunDebtContractEntity.setNxoptiondate(contractEntity.getNxoptiondate());
                        fosunDebtContractEntity.setOutstandingbalance(contractEntity.getOutstandingbalance());
                        //fosunDebtContractEntity.setPtmyear(contractEntity.getPtmyear());
                        fosunDebtContractEntity.setTermnote1(contractEntity.getTermnote1());
                        fosunDebtContractEntity.setRate_creditratingagency(contractEntity.getRate_creditratingagency());
                        fosunDebtContractEntity.setRate_latestmir_cnbd(contractEntity.getRate_latestmir_cnbd());
                        fosunDebtContractEntity.setRepo_lastestdate(contractEntity.getRepo_lastestdate());
                        fosunDebtContractEntity.setRepurchasedate(contractEntity.getRepurchasedate());
                        fosunDebtContractEntity.setSdate(contractEntity.getSdate());
                        fosunDebtContractEntity.setSec_name(contractEntity.getSec_name());
                        fosunDebtContractEntity.setTerm2(contractEntity.getTerm2());
                        fosunDebtContractEntity.setUpdatetime(contractEntity.getUpdatetime());
                        fosunDebtContractEntity.setWindcode(contractEntity.getWindcode());
                        fosunDebtContractEntity.setSourceid(contractEntity.getId());
                        fosunDebtContractEntity.setAgency_leadunderwritersn(contractEntity.getAgency_leadunderwritersn());
                        fosunDebtContractEntity.setIssuershortened(contractEntity.getIssuershortened());
                        fosunDebtContractEntity.setCurr(contractEntity.getCurr());//币种
                        fosunDebtContractEntityList.add(fosunDebtContractEntity);
                    }


                }
                log.error("从中间表同步债券信息3");
                if (fosunDebtContractEntityList!=null&&fosunDebtContractEntityList.size()>0)
                {
                    log.error("从中间表同步债券信息4");
                    fosunDebtContractRepository.saveAll(fosunDebtContractEntityList);
                    log.error("从中间表同步债券信息5");
                }

            }
            log.error("从中间表同步债券信息6");
            if (fosunDebtContractEntityList!=null&&fosunDebtContractEntityList.size()>0)
            {
                for (JtgkFosunbondFosunDebtContractEntity contractEntity:fosunDebtContractEntityList)
                {
                    String wincode=contractEntity.getWindcode();
                    List<JtgkFosunbondT_Debt_SecondaryMarketEntity> t_debt_secondaryMarketEntityList=t_debt_secondaryMarketRepository.findAllByWindcode(wincode);
                    log.error("从中间表同步债券信息7");
                    if(t_debt_secondaryMarketEntityList!=null&&t_debt_secondaryMarketEntityList.size()>0)
                    {
                        log.error("从中间表同步债券信息8");
                        List<JtgkFosunbondFosunDebtSecondaryMarketEntity> fosunDebtSecondaryMarketEntityList=new ArrayList<>();
                        for (JtgkFosunbondT_Debt_SecondaryMarketEntity marketEntity:t_debt_secondaryMarketEntityList)
                        {
                            //String updatetime=dateFormat.format(marketEntity.getUpdatetime());
                            //String predate=dateFormat.format(getPreDay(new Date()));
                            //if (updatetime.equals(predate))//如果更新日期为前一天则更新数据
                            {
                                String sourceid=marketEntity.getId();
                                JtgkFosunbondFosunDebtSecondaryMarketEntity fosunDebtSecondaryMarketEntityOriginal=fosunDebtSecondaryMarketRepository.findBySourceid(sourceid);
                                String originalId=UUID.randomUUID().toString();
                                if(!ObjectUtils.isEmpty(fosunDebtSecondaryMarketEntityOriginal))
                                {
                                    originalId=fosunDebtSecondaryMarketEntityOriginal.getId();
                                    fosunDebtSecondaryMarketRepository.deleteById(originalId);
                                }
                                JtgkFosunbondFosunDebtSecondaryMarketEntity fosunDebtSecondaryMarketEntity=new JtgkFosunbondFosunDebtSecondaryMarketEntity();
                                fosunDebtSecondaryMarketEntity.setId(originalId);
                                //marketEntity.getAmt();
                                fosunDebtSecondaryMarketEntity.setAmt(marketEntity.getAmt());
                                fosunDebtSecondaryMarketEntity.setCleanprice(marketEntity.getCleanprice());
                                fosunDebtSecondaryMarketEntity.setCreatetime(marketEntity.getCreatetime());
                                fosunDebtSecondaryMarketEntity.setSdate(marketEntity.getSdate());
                                fosunDebtSecondaryMarketEntity.setUpdatetime(marketEntity.getUpdatetime());
                                fosunDebtSecondaryMarketEntity.setWindcode(marketEntity.getWindcode());
                                fosunDebtSecondaryMarketEntity.setYtm_b(marketEntity.getYtm_b());
                                fosunDebtSecondaryMarketEntity.setSourceid(marketEntity.getId());
                                fosunDebtSecondaryMarketEntity.setYield_cnbd(marketEntity.getYield_cnbd());
                                fosunDebtSecondaryMarketEntity.setYield_cnbd2(marketEntity.getYield_cnbd2());
                                fosunDebtSecondaryMarketEntityList.add(fosunDebtSecondaryMarketEntity);

                            }

                        }
                        log.error("从中间表同步债券信息9");
                        if (fosunDebtSecondaryMarketEntityList!=null&&fosunDebtSecondaryMarketEntityList.size()>0)
                        {
                            log.error("从中间表同步债券信息10");
                            fosunDebtSecondaryMarketRepository.saveAll(fosunDebtSecondaryMarketEntityList);
                            log.error("从中间表同步债券信息11");
                        }
                    }
                    log.error("从中间表同步债券信息12");
                    List<JtgkFosunbondT_Debt_ValuationEntity> t_debt_valuationEntityList=t_debt_valuationRepository.findAllByWindcode(wincode);
                    log.error("从中间表同步债券信息13");
                    if (t_debt_valuationEntityList!=null&&t_debt_valuationEntityList.size()>0)
                    {
                        log.error("从中间表同步债券信息14");
                        List<JtgkFosunbondFosunDebtValuationEntity> fosunDebtValuationEntityList=new ArrayList<>();
                        for (JtgkFosunbondT_Debt_ValuationEntity valuationEntity:t_debt_valuationEntityList)
                        {
                            //String updatetime=dateFormat.format(valuationEntity.getUpdatetime());
                            //String predate=dateFormat.format(getPreDay(new Date()));
                            //if (updatetime.equals(predate))//如果更新日期为前一天则更新数据
                            {
                                String sourceid=valuationEntity.getId();
                                JtgkFosunbondFosunDebtValuationEntity fosunDebtValuationEntityOriginal=fosunDebtValuationRepository.findBySourceid(sourceid);
                                String originalId=UUID.randomUUID().toString();
                                if(!ObjectUtils.isEmpty(fosunDebtValuationEntityOriginal))
                                {
                                    originalId=fosunDebtValuationEntityOriginal.getId();
                                    fosunDebtValuationRepository.deleteById(originalId);
                                }
                                JtgkFosunbondFosunDebtValuationEntity fosunDebtValuationEntity=new JtgkFosunbondFosunDebtValuationEntity();
                                fosunDebtValuationEntity.setId(originalId);
                                fosunDebtValuationEntity.setCreatetime(valuationEntity.getCreatetime());
                                fosunDebtValuationEntity.setNet_cnbd(valuationEntity.getNet_cnbd());
                                fosunDebtValuationEntity.setNet_cnbd2(valuationEntity.getNet_cnbd2());
                                fosunDebtValuationEntity.setSdate(valuationEntity.getSdate());
                                fosunDebtValuationEntity.setUpdatetime(valuationEntity.getUpdatetime());
                                fosunDebtValuationEntity.setWindcode(valuationEntity.getWindcode());
                                fosunDebtValuationEntity.setYield_cnbd(valuationEntity.getYield_cnbd());
                                fosunDebtValuationEntity.setYield_cnbd2(valuationEntity.getYield_cnbd2());
                                fosunDebtValuationEntity.setSourceid(valuationEntity.getId());
                                fosunDebtValuationEntityList.add(fosunDebtValuationEntity);
                            }

                        }
                        log.error("从中间表同步债券信息15");
                        if (fosunDebtValuationEntityList!=null&&fosunDebtValuationEntityList.size()>0)
                        {
                            log.error("从中间表同步债券信息16");
                            fosunDebtValuationRepository.saveAll(fosunDebtValuationEntityList);
                            log.error("从中间表同步债券信息17");
                        }
                    }
                    log.error("从中间表同步债券信息18");
                    List<JtgkFosunbondT_IdenticalissUerEntity> t_identicalissUerEntityList=t_identicalissUerRepository.findAllByWindcode(wincode);
                    log.error("从中间表同步债券信息19");
                    if (t_identicalissUerEntityList!=null&&t_identicalissUerEntityList.size()>0)
                    {
                        log.error("从中间表同步债券信息20");
                        List<JtgkFosunbondFosunIdenticalissUerEntity> fosunIdenticalissUerEntityList=new ArrayList<>();
                        for (JtgkFosunbondT_IdenticalissUerEntity userEntity:t_identicalissUerEntityList)
                        {
                            //String updatetime=dateFormat.format(userEntity.getUpdatetime());
                            //String predate=dateFormat.format(getPreDay(new Date()));
                            //if (updatetime.equals(predate))//如果更新日期为前一天则更新数据
                            {
                                String sourceid=userEntity.getId();
                                JtgkFosunbondFosunIdenticalissUerEntity fosunIdenticalissUerEntityOriginal=fosunIdenticalissUerRepository.findBySourceid(sourceid);
                                String originalId=UUID.randomUUID().toString();
                                if(!ObjectUtils.isEmpty(fosunIdenticalissUerEntityOriginal))
                                {
                                    originalId=fosunIdenticalissUerEntityOriginal.getId();
                                    fosunIdenticalissUerRepository.deleteById(originalId);
                                }
                                JtgkFosunbondFosunIdenticalissUerEntity fosunIdenticalissUerEntity=new JtgkFosunbondFosunIdenticalissUerEntity();
                                fosunIdenticalissUerEntity.setId(originalId);
                                fosunIdenticalissUerEntity.setBondrating(userEntity.getBondrating());
                                fosunIdenticalissUerEntity.setBondtype(userEntity.getBondtype());
                                fosunIdenticalissUerEntity.setCorporaterating(userEntity.getCorporaterating());
                                fosunIdenticalissUerEntity.setCreatetime(userEntity.getCreatetime());
                                fosunIdenticalissUerEntity.setCurrentcouponrate(userEntity.getCurrentcouponrate());
                                //fosunIdenticalissUerEntity.setSec_status(userEntity.getSec_status());
                                fosunIdenticalissUerEntity.setSec_status(userEntity.getCurrentstate());
                                fosunIdenticalissUerEntity.setIssuedate(userEntity.getIssuedate());
                                fosunIdenticalissUerEntity.setOutstandingbalance(userEntity.getOutstandingbalance());
                                fosunIdenticalissUerEntity.setRemainingmaturity(userEntity.getRemainingmaturity());
                                fosunIdenticalissUerEntity.setSecname(userEntity.getSecname());
                                fosunIdenticalissUerEntity.setUpdatetime(userEntity.getUpdatetime());
                                fosunIdenticalissUerEntity.setWindcode(userEntity.getWindcode());
                                fosunIdenticalissUerEntity.setSourceid(userEntity.getId());
                                fosunIdenticalissUerEntityList.add(fosunIdenticalissUerEntity);
                            }

                        }
                        log.error("从中间表同步债券信息21");
                        if (fosunIdenticalissUerEntityList!=null&&fosunIdenticalissUerEntityList.size()>0)
                        {
                            log.error("从中间表同步债券信息22");
                            fosunIdenticalissUerRepository.saveAll(fosunIdenticalissUerEntityList);
                            log.error("从中间表同步债券信息23");
                        }
                    }
                }
            }


        }
        catch (Exception ex)
        {
            log.error("从中间表同步债券信息24");
            ex.printStackTrace();
            log.error("从中间表同步债券信息:"+ex.getMessage());
        }
        return "";
    }
    public String  SyncBondMsgFromMiddleTable1(JsonNode jsonNode)
    {
        log.error("b从中间表同步债券信息0");
        try
        {
            String beginDate=jsonNode.get("begindate").asText();
            String endDate=jsonNode.get("enddate").asText();

            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

            String predate=dateFormat.format(getPreDay(new Date()));
            String nowdate=dateFormat.format(new Date());
            log.error(nowdate);
            //log.error(predate);
            //Date nowdate=new Date();
            //String nowDateStr=new SimpleDateFormat("yyyy-MM-dd").format(nowdate);
            Date querydate=new SimpleDateFormat("yyyy-MM-dd").parse(predate);
            //List<T_Debt_ContractEntity> t_debt_contractEntityList=t_debt_contractRepository.findAll();
            List<JtgkFosunbondT_Debt_ContractEntity> t_debt_contractEntityList=t_debt_contractRepository.getdatabetwwenupdatetime(beginDate,endDate);
            log.error("b从中间表同步债券信息1");
            List<JtgkFosunbondFosunDebtContractEntity> fosunDebtContractEntityList=new ArrayList<>();
            if (t_debt_contractEntityList!=null&&t_debt_contractEntityList.size()>0)
            {

                log.error("b从中间表同步债券信息2");

                for (JtgkFosunbondT_Debt_ContractEntity contractEntity:t_debt_contractEntityList)
                {

                    String updatetime=dateFormat.format(contractEntity.getUpdatetime());



                    //if (updatetime.equals(predate))//如果更新日期为前一天则更新数据
                    //if (updatetime.equals(nowdate))//如果更新日期为当天则更新日期
                    {
                        String sourceid=contractEntity.getId();
                        JtgkFosunbondFosunDebtContractEntity fosunDebtContractEntityOriginal=fosunDebtContractRepository.findBySourceid(sourceid);
                        String originalId=UUID.randomUUID().toString();
                        if(!ObjectUtils.isEmpty(fosunDebtContractEntityOriginal))
                        {
                            originalId=fosunDebtContractEntityOriginal.getId();
                            fosunDebtContractRepository.deleteById(originalId);
                        }
                        JtgkFosunbondFosunDebtContractEntity fosunDebtContractEntity=new JtgkFosunbondFosunDebtContractEntity();
                        fosunDebtContractEntity.setId(originalId);
                        fosunDebtContractEntity.setAbs_industry1(contractEntity.getAbs_industry1());
                        fosunDebtContractEntity.setActualbenchmark(contractEntity.getActualbenchmark());
                        //fosunDebtContractEntity.setAdvancecredit_desc(contractEntity.getAdvancecredit_desc());
                        fosunDebtContractEntity.setAgency_guarantor(contractEntity.getAgency_guarantor());
                        fosunDebtContractEntity.setAgency_leadunderwriter(contractEntity.getAgency_leadunderwriter());
                        fosunDebtContractEntity.setBondtype(contractEntity.getBondtype());
                        fosunDebtContractEntity.setCarrydate(contractEntity.getCarrydate());
                        fosunDebtContractEntity.setClause(contractEntity.getClause());
                        fosunDebtContractEntity.setClauseabbr(contractEntity.getClauseabbr());
                        fosunDebtContractEntity.setComp_name(contractEntity.getComp_name());
                        fosunDebtContractEntity.setAgency_grnttype(contractEntity.getAgency_grnttype());
                        fosunDebtContractEntity.setCoupon(contractEntity.getCoupon());
                        fosunDebtContractEntity.setCoupondatetxt(contractEntity.getCoupondatetxt());
                        fosunDebtContractEntity.setCouponrate2(contractEntity.getCouponrate2());
                        fosunDebtContractEntity.setCoupontxt(contractEntity.getCoupontxt());
                        fosunDebtContractEntity.setCreatetime(contractEntity.getCreatetime());
                        fosunDebtContractEntity.setCreditrating(contractEntity.getCreditrating());
                        //fosunDebtContractEntity.setCurrentstate(contractEntity.getCurrentstate()==null?"":contractEntity.getCurrentstate());
                        fosunDebtContractEntity.setSec_status(contractEntity.getSec_status());
                        fosunDebtContractEntity.setExch_city(contractEntity.getExch_city());
                        fosunDebtContractEntity.setFullname(contractEntity.getFullname());
                        fosunDebtContractEntity.setIndustry_gics(contractEntity.getIndustry_gics());
                        fosunDebtContractEntity.setInterestfrequency(contractEntity.getInterestfrequency());
                        fosunDebtContractEntity.setIpo_date(contractEntity.getIpo_date());
                        fosunDebtContractEntity.setIssue_announcement(contractEntity.getIssue_announcement());
                        fosunDebtContractEntity.setIssue_firstissue(contractEntity.getIssue_firstissue());
                        fosunDebtContractEntity.setIssue_issuemethod(contractEntity.getIssue_issuemethod());
                        fosunDebtContractEntity.setIssue_issueprice(contractEntity.getIssue_issueprice());
                        fosunDebtContractEntity.setIssue_lastissue(contractEntity.getIssue_lastissue());
                        fosunDebtContractEntity.setIssue_regnumber(contractEntity.getIssue_regnumber());
                        fosunDebtContractEntity.setIssueamount(contractEntity.getIssueamount());
                        fosunDebtContractEntity.setIssuer_rating(contractEntity.getIssuer_rating());
                        //fosunDebtContractEntity.setLast_trade_day(contractEntity.getLast_trade_day());
                        fosunDebtContractEntity.setMaturitydate(contractEntity.getMaturitydate());
                        fosunDebtContractEntity.setMultimktornot(contractEntity.getMultimktornot());
                        fosunDebtContractEntity.setNxcupn(contractEntity.getNxcupn());
                        fosunDebtContractEntity.setNxcupn2(contractEntity.getNxcupn2());
                        fosunDebtContractEntity.setNxoptiondate(contractEntity.getNxoptiondate());
                        fosunDebtContractEntity.setOutstandingbalance(contractEntity.getOutstandingbalance());
                        //fosunDebtContractEntity.setPtmyear(contractEntity.getPtmyear());
                        fosunDebtContractEntity.setTermnote1(contractEntity.getTermnote1());
                        fosunDebtContractEntity.setRate_creditratingagency(contractEntity.getRate_creditratingagency());
                        fosunDebtContractEntity.setRate_latestmir_cnbd(contractEntity.getRate_latestmir_cnbd());
                        fosunDebtContractEntity.setRepo_lastestdate(contractEntity.getRepo_lastestdate());
                        fosunDebtContractEntity.setRepurchasedate(contractEntity.getRepurchasedate());
                        fosunDebtContractEntity.setSdate(contractEntity.getSdate());
                        fosunDebtContractEntity.setSec_name(contractEntity.getSec_name());
                        fosunDebtContractEntity.setTerm2(contractEntity.getTerm2());
                        fosunDebtContractEntity.setUpdatetime(contractEntity.getUpdatetime());
                        fosunDebtContractEntity.setWindcode(contractEntity.getWindcode());
                        fosunDebtContractEntity.setSourceid(contractEntity.getId());
                        fosunDebtContractEntity.setAgency_leadunderwritersn(contractEntity.getAgency_leadunderwritersn());
                        fosunDebtContractEntity.setIssuershortened(contractEntity.getIssuershortened());
                        fosunDebtContractEntity.setCurr(contractEntity.getCurr());//币种
                        fosunDebtContractEntityList.add(fosunDebtContractEntity);
                    }


                }
                log.error("b从中间表同步债券信息3");
                if (fosunDebtContractEntityList!=null&&fosunDebtContractEntityList.size()>0)
                {
                    log.error("b从中间表同步债券信息4");
                    fosunDebtContractRepository.saveAll(fosunDebtContractEntityList);
                    log.error("b从中间表同步债券信息5");
                }

            }
            log.error("b从中间表同步债券信息6");
            if (fosunDebtContractEntityList!=null&&fosunDebtContractEntityList.size()>0)
            {
                for (JtgkFosunbondFosunDebtContractEntity contractEntity:fosunDebtContractEntityList)
                {
                    String wincode=contractEntity.getWindcode();
                    List<JtgkFosunbondT_Debt_SecondaryMarketEntity> t_debt_secondaryMarketEntityList=t_debt_secondaryMarketRepository.findAllByWindcode(wincode);
                    log.error("b从中间表同步债券信息7");
                    if(t_debt_secondaryMarketEntityList!=null&&t_debt_secondaryMarketEntityList.size()>0)
                    {
                        log.error("b从中间表同步债券信息8");
                        List<JtgkFosunbondFosunDebtSecondaryMarketEntity> fosunDebtSecondaryMarketEntityList=new ArrayList<>();
                        for (JtgkFosunbondT_Debt_SecondaryMarketEntity marketEntity:t_debt_secondaryMarketEntityList)
                        {
                            //String updatetime=dateFormat.format(marketEntity.getUpdatetime());
                            //String predate=dateFormat.format(getPreDay(new Date()));
                            //if (updatetime.equals(predate))//如果更新日期为前一天则更新数据
                            {
                                String sourceid=marketEntity.getId();
                                JtgkFosunbondFosunDebtSecondaryMarketEntity fosunDebtSecondaryMarketEntityOriginal=fosunDebtSecondaryMarketRepository.findBySourceid(sourceid);
                                String originalId=UUID.randomUUID().toString();
                                if(!ObjectUtils.isEmpty(fosunDebtSecondaryMarketEntityOriginal))
                                {
                                    originalId=fosunDebtSecondaryMarketEntityOriginal.getId();
                                    fosunDebtSecondaryMarketRepository.deleteById(originalId);
                                }
                                JtgkFosunbondFosunDebtSecondaryMarketEntity fosunDebtSecondaryMarketEntity=new JtgkFosunbondFosunDebtSecondaryMarketEntity();
                                fosunDebtSecondaryMarketEntity.setId(originalId);
                                //marketEntity.getAmt();
                                fosunDebtSecondaryMarketEntity.setAmt(marketEntity.getAmt());
                                fosunDebtSecondaryMarketEntity.setCleanprice(marketEntity.getCleanprice());
                                fosunDebtSecondaryMarketEntity.setCreatetime(marketEntity.getCreatetime());
                                fosunDebtSecondaryMarketEntity.setSdate(marketEntity.getSdate());
                                fosunDebtSecondaryMarketEntity.setUpdatetime(marketEntity.getUpdatetime());
                                fosunDebtSecondaryMarketEntity.setWindcode(marketEntity.getWindcode());
                                fosunDebtSecondaryMarketEntity.setYtm_b(marketEntity.getYtm_b());
                                fosunDebtSecondaryMarketEntity.setSourceid(marketEntity.getId());
                                fosunDebtSecondaryMarketEntity.setYield_cnbd(marketEntity.getYield_cnbd());
                                fosunDebtSecondaryMarketEntity.setYield_cnbd2(marketEntity.getYield_cnbd2());
                                fosunDebtSecondaryMarketEntityList.add(fosunDebtSecondaryMarketEntity);

                            }

                        }
                        log.error("b从中间表同步债券信息9");
                        if (fosunDebtSecondaryMarketEntityList!=null&&fosunDebtSecondaryMarketEntityList.size()>0)
                        {
                            log.error("b从中间表同步债券信息10");
                            fosunDebtSecondaryMarketRepository.saveAll(fosunDebtSecondaryMarketEntityList);
                            log.error("b从中间表同步债券信息11");
                        }
                    }
                    log.error("b从中间表同步债券信息12");
                    List<JtgkFosunbondT_Debt_ValuationEntity> t_debt_valuationEntityList=t_debt_valuationRepository.findAllByWindcode(wincode);
                    log.error("b从中间表同步债券信息13");
                    if (t_debt_valuationEntityList!=null&&t_debt_valuationEntityList.size()>0)
                    {
                        log.error("b从中间表同步债券信息14");
                        List<JtgkFosunbondFosunDebtValuationEntity> fosunDebtValuationEntityList=new ArrayList<>();
                        for (JtgkFosunbondT_Debt_ValuationEntity valuationEntity:t_debt_valuationEntityList)
                        {
                            //String updatetime=dateFormat.format(valuationEntity.getUpdatetime());
                            //String predate=dateFormat.format(getPreDay(new Date()));
                            //if (updatetime.equals(predate))//如果更新日期为前一天则更新数据
                            {
                                String sourceid=valuationEntity.getId();
                                JtgkFosunbondFosunDebtValuationEntity fosunDebtValuationEntityOriginal=fosunDebtValuationRepository.findBySourceid(sourceid);
                                String originalId=UUID.randomUUID().toString();
                                if(!ObjectUtils.isEmpty(fosunDebtValuationEntityOriginal))
                                {
                                    originalId=fosunDebtValuationEntityOriginal.getId();
                                    fosunDebtValuationRepository.deleteById(originalId);
                                }
                                JtgkFosunbondFosunDebtValuationEntity fosunDebtValuationEntity=new JtgkFosunbondFosunDebtValuationEntity();
                                fosunDebtValuationEntity.setId(originalId);
                                fosunDebtValuationEntity.setCreatetime(valuationEntity.getCreatetime());
                                fosunDebtValuationEntity.setNet_cnbd(valuationEntity.getNet_cnbd());
                                fosunDebtValuationEntity.setNet_cnbd2(valuationEntity.getNet_cnbd2());
                                fosunDebtValuationEntity.setSdate(valuationEntity.getSdate());
                                fosunDebtValuationEntity.setUpdatetime(valuationEntity.getUpdatetime());
                                fosunDebtValuationEntity.setWindcode(valuationEntity.getWindcode());
                                fosunDebtValuationEntity.setYield_cnbd(valuationEntity.getYield_cnbd());
                                fosunDebtValuationEntity.setYield_cnbd2(valuationEntity.getYield_cnbd2());
                                fosunDebtValuationEntity.setSourceid(valuationEntity.getId());
                                fosunDebtValuationEntityList.add(fosunDebtValuationEntity);
                            }

                        }
                        log.error("b从中间表同步债券信息15");
                        if (fosunDebtValuationEntityList!=null&&fosunDebtValuationEntityList.size()>0)
                        {
                            log.error("b从中间表同步债券信息16");
                            fosunDebtValuationRepository.saveAll(fosunDebtValuationEntityList);
                            log.error("b从中间表同步债券信息17");
                        }
                    }
                    log.error("b从中间表同步债券信息18");
                    List<JtgkFosunbondT_IdenticalissUerEntity> t_identicalissUerEntityList=t_identicalissUerRepository.findAllByWindcode(wincode);
                    log.error("b从中间表同步债券信息19");
                    if (t_identicalissUerEntityList!=null&&t_identicalissUerEntityList.size()>0)
                    {
                        log.error("b从中间表同步债券信息20");
                        List<JtgkFosunbondFosunIdenticalissUerEntity> fosunIdenticalissUerEntityList=new ArrayList<>();
                        for (JtgkFosunbondT_IdenticalissUerEntity userEntity:t_identicalissUerEntityList)
                        {
                            //String updatetime=dateFormat.format(userEntity.getUpdatetime());
                            //String predate=dateFormat.format(getPreDay(new Date()));
                            //if (updatetime.equals(predate))//如果更新日期为前一天则更新数据
                            {
                                String sourceid=userEntity.getId();
                                JtgkFosunbondFosunIdenticalissUerEntity fosunIdenticalissUerEntityOriginal=fosunIdenticalissUerRepository.findBySourceid(sourceid);
                                String originalId=UUID.randomUUID().toString();
                                if(!ObjectUtils.isEmpty(fosunIdenticalissUerEntityOriginal))
                                {
                                    originalId=fosunIdenticalissUerEntityOriginal.getId();
                                    fosunIdenticalissUerRepository.deleteById(originalId);
                                }
                                JtgkFosunbondFosunIdenticalissUerEntity fosunIdenticalissUerEntity=new JtgkFosunbondFosunIdenticalissUerEntity();
                                fosunIdenticalissUerEntity.setId(originalId);
                                fosunIdenticalissUerEntity.setBondrating(userEntity.getBondrating());
                                fosunIdenticalissUerEntity.setBondtype(userEntity.getBondtype());
                                fosunIdenticalissUerEntity.setCorporaterating(userEntity.getCorporaterating());
                                fosunIdenticalissUerEntity.setCreatetime(userEntity.getCreatetime());
                                fosunIdenticalissUerEntity.setCurrentcouponrate(userEntity.getCurrentcouponrate());
                                //fosunIdenticalissUerEntity.setSec_status(userEntity.getSec_status());
                                fosunIdenticalissUerEntity.setSec_status(userEntity.getCurrentstate());
                                fosunIdenticalissUerEntity.setIssuedate(userEntity.getIssuedate());
                                fosunIdenticalissUerEntity.setOutstandingbalance(userEntity.getOutstandingbalance());
                                fosunIdenticalissUerEntity.setRemainingmaturity(userEntity.getRemainingmaturity());
                                fosunIdenticalissUerEntity.setSecname(userEntity.getSecname());
                                fosunIdenticalissUerEntity.setUpdatetime(userEntity.getUpdatetime());
                                fosunIdenticalissUerEntity.setWindcode(userEntity.getWindcode());
                                fosunIdenticalissUerEntity.setSourceid(userEntity.getId());
                                fosunIdenticalissUerEntityList.add(fosunIdenticalissUerEntity);
                            }

                        }
                        log.error("b从中间表同步债券信息21");
                        if (fosunIdenticalissUerEntityList!=null&&fosunIdenticalissUerEntityList.size()>0)
                        {
                            log.error("b从中间表同步债券信息22");
                            fosunIdenticalissUerRepository.saveAll(fosunIdenticalissUerEntityList);
                            log.error("b从中间表同步债券信息23");
                        }
                    }
                }
            }


        }
        catch (Exception ex)
        {
            log.error("b从中间表同步债券信息24");
            ex.printStackTrace();
            log.error("b从中间表同步债券信息:"+ex.getMessage());
        }
        return "";
    }
    private  static Date getPreDay(Date date)
    {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        date=calendar.getTime();
        return  date;
    }
}
