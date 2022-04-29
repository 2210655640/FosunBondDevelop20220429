package com.inspur.fosunbond.core.domain.service;


import com.inspur.fosunbond.core.domain.repository.FosunDebtContract1Repository;
import com.inspur.fosunbond.core.domain.repository.FosunDebtSecondaryMarket1Repository;
import com.inspur.fosunbond.core.domain.repository.FosunDebtValuation1Repository;
import com.inspur.fosunbond.core.domain.repository.FosunIdenticalissUer1Repository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

//@GspComponent(id="AD2021-AF-KS-ZD-041BOND")//定义通用构件由调度任务执行
@Slf4j
//@Service
public class FosunSynchroMiddleTableForBond111 {
    @Autowired
    private FosunDebtContract1Repository fosunDebtContractRepository;
    @Autowired
    private FosunDebtSecondaryMarket1Repository fosunDebtSecondaryMarket1Repository;
    @Autowired
    private FosunDebtValuation1Repository fosunDebtValuation1Repository;
    @Autowired
    private FosunIdenticalissUer1Repository fosunIdenticalissUer1Repository;
//    @Autowired
//    private T_Debt_Contract1Repository t_debt_contract1Repository;
//    @Autowired
//    private T_Debt_SecondaryMarket1Repository t_debt_secondaryMarket1Repository;
//    @Autowired
//    private T_Debt_Valuation1Repository t_debt_valuation1Repository;
//    @Autowired
//    private T_IdenticalissUer1Repository t_identicalissUer1Repository;
    @Transactional(rollbackFor ={Exception.class})
    public String  SyncBondMsgFromMiddleTable()
    {
        log.error("从中间表同步债券信息0");
        try
        {
////            String sql="select * from fosunidenticalissuer";
////            EntityManager manager= SpringBeanUtils.getBean(EntityManager.class);
//            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
//            //List<T_Debt_ContractEntity> t_debt_contractEntityList=t_debt_contractRepository.findAll();
//            //List<T_Debt_SecondaryMarketEntity> t_debt_secondaryMarketEntityList123=t_debt_secondaryMarketRepository.findAll();
//            String predate=dateFormat.format(getPreDay(new Date()));
//            List<T_Debt_Contract1Entity> t_debt_contract1EntityList = t_debt_contract1Repository.findAll();
//            log.error("从中间表同步债券信息1");
//            if (t_debt_contract1EntityList !=null&& t_debt_contract1EntityList.size()>0)
//            {
//                log.error("从中间表同步债券信息2");
//                List<FosunDebtContract1Entity> fosunDebtContract1EntityList =new ArrayList<>();
//                for (T_Debt_Contract1Entity contractEntity: t_debt_contract1EntityList)
//                {
//                    log.error("原始时间："+contractEntity.getUpdatetime());
//                    String updatetime=dateFormat.format(contractEntity.getUpdatetime());
//                    log.error("updatetime:"+updatetime);
//
//                    log.error("predate:"+predate);
//                    if (updatetime.equals(predate))//如果更新日期为前一天则更新数据
//                    {
//                        FosunDebtContract1Entity fosunDebtContract1Entity =new FosunDebtContract1Entity();
//                        fosunDebtContract1Entity.setId(UUID.randomUUID().toString());
//                        fosunDebtContract1Entity.setAbs_industry1(contractEntity.getAbs_industry1());
//                        fosunDebtContract1Entity.setActualbenchmark(contractEntity.getActualbenchmark());
//                        //fosunDebtContractEntity.setAdvancecredit_desc(contractEntity.getAdvancecredit_desc());
//                        fosunDebtContract1Entity.setAgency_guarantor(contractEntity.getAgency_guarantor());
//                        fosunDebtContract1Entity.setAgency_leadunderwriter(contractEntity.getAgency_leadunderwriter());
//                        fosunDebtContract1Entity.setBondtype(contractEntity.getBondtype());
//                        fosunDebtContract1Entity.setCarrydate(contractEntity.getCarrydate());
//                        fosunDebtContract1Entity.setClause(contractEntity.getClause());
//                        fosunDebtContract1Entity.setClauseabbr(contractEntity.getClauseabbr());
//                        fosunDebtContract1Entity.setComp_name(contractEntity.getComp_name());
//                        fosunDebtContract1Entity.setCoupon(contractEntity.getCoupon());
//                        fosunDebtContract1Entity.setCoupondatetxt(contractEntity.getCoupondatetxt());
//                        fosunDebtContract1Entity.setCouponrate2(contractEntity.getCouponrate2());
//                        fosunDebtContract1Entity.setCoupontxt(contractEntity.getCoupontxt());
//                        fosunDebtContract1Entity.setCreatetime(contractEntity.getCreatetime());
//                        fosunDebtContract1Entity.setCreditrating(contractEntity.getCreditrating());
//                        //fosunDebtContractEntity.setCurrentstate(contractEntity.getCurrentstate()==null?"":contractEntity.getCurrentstate());
//                        fosunDebtContract1Entity.setExch_city(contractEntity.getExch_city());
//                        fosunDebtContract1Entity.setFullname(contractEntity.getFullname());
//                        fosunDebtContract1Entity.setIndustry_gics(contractEntity.getMultimktornot());
//                        fosunDebtContract1Entity.setInterestfrequency(contractEntity.getInterestfrequency());
//                        fosunDebtContract1Entity.setIpo_date(contractEntity.getIpo_date());
//                        fosunDebtContract1Entity.setIssue_announcement(contractEntity.getIssue_announcement());
//                        fosunDebtContract1Entity.setIssue_firstissue(contractEntity.getIssue_firstissue());
//                        fosunDebtContract1Entity.setIssue_issuemethod(contractEntity.getIssue_issuemethod());
//                        fosunDebtContract1Entity.setIssue_issueprice(contractEntity.getIssue_issueprice());
//                        fosunDebtContract1Entity.setIssue_lastissue(contractEntity.getIssue_lastissue());
//                        fosunDebtContract1Entity.setIssue_regnumber(contractEntity.getIssue_regnumber());
//                        fosunDebtContract1Entity.setIssueamount(contractEntity.getIssueamount());
//                        fosunDebtContract1Entity.setIssuer_rating(contractEntity.getIssuer_rating());
//                        //fosunDebtContractEntity.setLast_trade_day(contractEntity.getLast_trade_day());
//                        fosunDebtContract1Entity.setMaturitydate(contractEntity.getMaturitydate());
//                        fosunDebtContract1Entity.setMultimktornot(contractEntity.getMultimktornot());
//                        fosunDebtContract1Entity.setNxcupn(contractEntity.getNxcupn());
//                        fosunDebtContract1Entity.setNxcupn2(contractEntity.getNxcupn2());
//                        fosunDebtContract1Entity.setNxoptiondate(contractEntity.getNxoptiondate());
//                        fosunDebtContract1Entity.setOutstandingbalance(contractEntity.getOutstandingbalance());
//                        fosunDebtContract1Entity.setPtmyear(contractEntity.getPtmyear());
//                        fosunDebtContract1Entity.setRate_creditratingagency(contractEntity.getRate_creditratingagency());
//                        fosunDebtContract1Entity.setRate_latestmir_cnbd(contractEntity.getRate_latestmir_cnbd());
//                        fosunDebtContract1Entity.setRepo_lastestdate(contractEntity.getRepo_lastestdate());
//                        fosunDebtContract1Entity.setRepurchasedate(contractEntity.getRepurchasedate());
//                        fosunDebtContract1Entity.setSdate(contractEntity.getSdate());
//                        fosunDebtContract1Entity.setSec_name(contractEntity.getSec_name());
//                        fosunDebtContract1Entity.setTerm2(contractEntity.getTerm2());
//                        fosunDebtContract1Entity.setUpdatetime(contractEntity.getUpdatetime());
//                        fosunDebtContract1Entity.setWindcode(contractEntity.getWindcode());
//                        fosunDebtContract1EntityList.add(fosunDebtContract1Entity);
//                    }
//
//
//                }
//                log.error("从中间表同步债券信息3");
//                if (fosunDebtContract1EntityList !=null&& fosunDebtContract1EntityList.size()>0)
//                {
//                    log.error("从中间表同步债券信息4");
//                    fosunDebtContractRepository.saveAll(fosunDebtContract1EntityList);
//                    log.error("从中间表同步债券信息5");
//                }
//
//            }
//            log.error("从中间表同步债券信息6");
//            List<T_Debt_SecondaryMarket1Entity> t_debt_secondaryMarket1EntityList = t_debt_secondaryMarket1Repository.findAll();
//            log.error("从中间表同步债券信息7");
//            if(t_debt_secondaryMarket1EntityList !=null&& t_debt_secondaryMarket1EntityList.size()>0)
//            {
//                log.error("从中间表同步债券信息8");
//                List<FosunDebtSecondaryMarket1Entity> fosunDebtSecondaryMarket1EntityList =new ArrayList<>();
//                for (T_Debt_SecondaryMarket1Entity marketEntity: t_debt_secondaryMarket1EntityList)
//                {
//                    String updatetime=dateFormat.format(marketEntity.getUpdatetime());
//                    //String predate=dateFormat.format(getPreDay(new Date()));
//                    if (updatetime.equals(predate))//如果更新日期为前一天则更新数据
//                    {
//                        FosunDebtSecondaryMarket1Entity fosunDebtSecondaryMarket1Entity =new FosunDebtSecondaryMarket1Entity();
//                        fosunDebtSecondaryMarket1Entity.setId(UUID.randomUUID().toString());
//                        //marketEntity.getAmt();
//                        fosunDebtSecondaryMarket1Entity.setAmt(marketEntity.getAmt());
//                        fosunDebtSecondaryMarket1Entity.setCleanprice(marketEntity.getCleanprice());
//                        fosunDebtSecondaryMarket1Entity.setCreatetime(marketEntity.getCreatetime());
//                        fosunDebtSecondaryMarket1Entity.setSdate(marketEntity.getSdate());
//                        fosunDebtSecondaryMarket1Entity.setUpdatetime(marketEntity.getUpdatetime());
//                        fosunDebtSecondaryMarket1Entity.setWindcode(marketEntity.getWindcode());
//                        fosunDebtSecondaryMarket1Entity.setYtm_b(marketEntity.getYtm_b());
//                        fosunDebtSecondaryMarket1EntityList.add(fosunDebtSecondaryMarket1Entity);
//                    }
//
//                }
//                log.error("从中间表同步债券信息9");
//                if (fosunDebtSecondaryMarket1EntityList !=null&& fosunDebtSecondaryMarket1EntityList.size()>0)
//                {
//                    log.error("从中间表同步债券信息10");
//                    fosunDebtSecondaryMarket1Repository.saveAll(fosunDebtSecondaryMarket1EntityList);
//                    log.error("从中间表同步债券信息11");
//                }
//            }
//            log.error("从中间表同步债券信息12");
//            List<T_Debt_Valuation1Entity> t_debt_valuation1EntityList = t_debt_valuation1Repository.findAll();
//            log.error("从中间表同步债券信息13");
//            if (t_debt_valuation1EntityList !=null&& t_debt_valuation1EntityList.size()>0)
//            {
//                log.error("从中间表同步债券信息14");
//                List<FosunDebtValuation1Entity> fosunDebtValuation1EntityList =new ArrayList<>();
//                for (T_Debt_Valuation1Entity valuationEntity: t_debt_valuation1EntityList)
//                {
//                    String updatetime=dateFormat.format(valuationEntity.getUpdatetime());
//                    //String predate=dateFormat.format(getPreDay(new Date()));
//                    if (updatetime.equals(predate))//如果更新日期为前一天则更新数据
//                    {
//                        FosunDebtValuation1Entity fosunDebtValuation1Entity =new FosunDebtValuation1Entity();
//                        fosunDebtValuation1Entity.setId(UUID.randomUUID().toString());
//                        fosunDebtValuation1Entity.setCreatetime(valuationEntity.getCreatetime());
//                        fosunDebtValuation1Entity.setNet_cnbd(valuationEntity.getNet_cnbd());
//                        fosunDebtValuation1Entity.setNet_cnbd2(valuationEntity.getNet_cnbd2());
//                        fosunDebtValuation1Entity.setSdate(valuationEntity.getSdate());
//                        fosunDebtValuation1Entity.setUpdatetime(valuationEntity.getUpdatetime());
//                        fosunDebtValuation1Entity.setWindcode(valuationEntity.getWindcode());
//                        fosunDebtValuation1Entity.setYield_cnbd(valuationEntity.getYield_cnbd());
//                        fosunDebtValuation1Entity.setYield_cnbd2(valuationEntity.getYield_cnbd2());
//                        fosunDebtValuation1EntityList.add(fosunDebtValuation1Entity);
//                    }
//
//                }
//                log.error("从中间表同步债券信息15");
//                if (fosunDebtValuation1EntityList !=null&& fosunDebtValuation1EntityList.size()>0)
//                {
//                    log.error("从中间表同步债券信息16");
//                    fosunDebtValuation1Repository.saveAll(fosunDebtValuation1EntityList);
//                    log.error("从中间表同步债券信息17");
//                }
//            }
//            log.error("从中间表同步债券信息18");
//            List<T_IdenticalissUer1Entity> t_identicalissUer1EntityList = t_identicalissUer1Repository.findAll();
//            log.error("从中间表同步债券信息19");
//            if (t_identicalissUer1EntityList !=null&& t_identicalissUer1EntityList.size()>0)
//            {
//                log.error("从中间表同步债券信息20");
//                List<FosunIdenticalissUer1Entity> fosunIdenticalissUer1EntityList =new ArrayList<>();
//                for (T_IdenticalissUer1Entity userEntity: t_identicalissUer1EntityList)
//                {
//                    String updatetime=dateFormat.format(userEntity.getUpdatetime());
//                    //String predate=dateFormat.format(getPreDay(new Date()));
//                    if (updatetime.equals(predate))//如果更新日期为前一天则更新数据
//                    {
//                        FosunIdenticalissUer1Entity fosunIdenticalissUer1Entity =new FosunIdenticalissUer1Entity();
//                        fosunIdenticalissUer1Entity.setId(UUID.randomUUID().toString());
//                        fosunIdenticalissUer1Entity.setBondrating(userEntity.getBondrating());
//                        fosunIdenticalissUer1Entity.setBondtype(userEntity.getBondtype());
//                        fosunIdenticalissUer1Entity.setCorporaterating(userEntity.getCorporaterating());
//                        fosunIdenticalissUer1Entity.setCreatetime(userEntity.getCreatetime());
//                        fosunIdenticalissUer1Entity.setCurrentcouponrate(userEntity.getCurrentcouponrate());
//                        fosunIdenticalissUer1Entity.setSec_status(userEntity.getSec_status());
//                        fosunIdenticalissUer1Entity.setIssuedate(userEntity.getIssuedate());
//                        fosunIdenticalissUer1Entity.setOutstandingbalance(userEntity.getOutstandingbalance());
//                        fosunIdenticalissUer1Entity.setRemainingmaturity(userEntity.getRemainingmaturity());
//                        fosunIdenticalissUer1Entity.setSecname(userEntity.getSecname());
//                        fosunIdenticalissUer1Entity.setUpdatetime(userEntity.getUpdatetime());
//                        fosunIdenticalissUer1Entity.setWindcode(userEntity.getWindcode());
//                        fosunIdenticalissUer1EntityList.add(fosunIdenticalissUer1Entity);
//                    }
//
//                }
//                log.error("从中间表同步债券信息21");
//                if (fosunIdenticalissUer1EntityList !=null&& fosunIdenticalissUer1EntityList.size()>0)
//                {
//                    log.error("从中间表同步债券信息22");
//                    fosunIdenticalissUer1Repository.saveAll(fosunIdenticalissUer1EntityList);
//                    log.error("从中间表同步债券信息23");
//                }
//            }

        }
        catch (Exception ex)
        {
            log.error("从中间表同步债券信息24");
            ex.printStackTrace();
            log.error("从中间表同步债券信息:"+ex.getMessage());
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
