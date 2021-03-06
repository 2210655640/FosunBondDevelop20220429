package com.inspur.fosunbond.core.domain.service;


import com.inspur.edp.cdf.component.api.annotation.GspComponent;
import com.inspur.fosunbond.core.domain.entity.*;
import com.inspur.fosunbond.core.domain.repository.FosunbondrpaytplansRepository;
import com.inspur.fosunbond.core.domain.repository.JtgkFosunbondFosunDebtContractRepository;
import com.inspur.fosunbond.core.domain.repository.JtgkFosunbondFosunIdenticalissUerRepository;
import com.inspur.fosunbond.core.domain.repository.T_debt_cashflowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@GspComponent(id="AD2021-AF-KS-ZD-0WDBOND")//定义通用构件由调度任务执行
@Slf4j
@Service
public class FosunSynchroWDMiddleTable {

    @Autowired
    private T_debt_cashflowRepository t_debt_cashflowRepository;
    @Autowired
    private FosunbondrpaytplansRepository fosunbondrpaytplansRepository;
    @Autowired
    private JtgkFosunbondFosunIdenticalissUerRepository jtgkFosunbondFosunIdenticalissUerRepository;
    @Autowired
    private JtgkFosunbondFosunDebtContractRepository jtgkFosunbondFosunDebtContractRepository;
   @Transactional(rollbackFor ={Exception.class})
    public String syncWDcashflow()
    {
        try
        {

            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            String nowdate=dateFormat.format(new Date());
            log.error(nowdate);
            List<T_debt_cashflowEntity> t_debt_cashflowEntityList=t_debt_cashflowRepository.getdatabycreatetime(nowdate);
            log.error(String.valueOf(t_debt_cashflowEntityList.size()));
            List<FosunbondrpaytplansEntity> fosunbondrpaytplansEntityList=new ArrayList<>();
            for (T_debt_cashflowEntity flowEntity:t_debt_cashflowEntityList)
            {   log.error("3");
                String flowid=flowEntity.getId();
                List<FosunbondrpaytplansEntity> fosunbondrpaytplansEntity=fosunbondrpaytplansRepository.findAllBySourceid(flowid);


                if (fosunbondrpaytplansEntity==null||fosunbondrpaytplansEntity.size()==0)
                {
                    String cf_type=flowEntity.getCf_type();//类型
                    if ("兑付".equals(cf_type))//如果类型为兑付则做拆分
                    {
                        //将还款计划拆分成两条数据还本和付息
                        for(int i=0;i<2;i++)
                        {
                            FosunbondrpaytplansEntity fosunbondrpaytplansEntity1=new FosunbondrpaytplansEntity();
                            fosunbondrpaytplansEntity1.setId(UUID.randomUUID().toString());
                            //fosunbondrpaytplansEntity1.setAccrued_interest_per_cny100par(flowEntity.getAccrued_interest_per_cny100par());
                            //处理利息
                            //List<JtgkFosunbondFosunIdenticalissUerEntity> jtgkFosunbondFosunIdenticalissUerEntityList=jtgkFosunbondFosunIdenticalissUerRepository.findAllByWindcodeAndIssuedateBeforeOrderByIssuedateDesc(flowEntity.getWindcode(),flowEntity.getCash_flows_date());
                            List<JtgkFosunbondFosunDebtContractEntity> fosunDebtContractEntityList=jtgkFosunbondFosunDebtContractRepository.findAllByWindcodeAndSdateBeforeOrderBySdateDesc(flowEntity.getWindcode(),flowEntity.getCash_flows_date());
                            BigDecimal  Accrued_interest_per_cny100par=new BigDecimal(0);
                            BigDecimal  Accrued_principal_per_cny100par=new BigDecimal(0);
                            BigDecimal Outstandingbalance=new BigDecimal(0);
                            if (fosunDebtContractEntityList!=null&&fosunDebtContractEntityList.size()>0)
                            {
                                Outstandingbalance=fosunDebtContractEntityList.get(0).getOutstandingbalance();
                                Outstandingbalance=(Outstandingbalance==null?new BigDecimal(0):Outstandingbalance);
                            }

                            if(i==0)//付息
                            {
                                Accrued_interest_per_cny100par=Outstandingbalance.multiply(new BigDecimal(100000000)).multiply((flowEntity.getAccrued_interest_per_cny100par()==null?new BigDecimal(0):flowEntity.getAccrued_interest_per_cny100par())).divide(new BigDecimal(100));
                            }

                            fosunbondrpaytplansEntity1.setAccrued_interest_per_cny100par(Accrued_interest_per_cny100par);
                            if(i==1)//还本
                            {
                                Accrued_principal_per_cny100par=Outstandingbalance.multiply(new BigDecimal(100000000)).multiply((flowEntity.getAccrued_interest_per_cny100par()==null?new BigDecimal(0):flowEntity.getAccrued_principal_per_cny100par())).divide(new BigDecimal(100));
                            }

                            //fosunbondrpaytplansEntity1.setAccrued_principal_per_cny100par(flowEntity.getAccrued_principal_per_cny100par());
                            fosunbondrpaytplansEntity1.setAccrued_principal_per_cny100par(Accrued_principal_per_cny100par);

                            fosunbondrpaytplansEntity1.setCashflowsdate(flowEntity.getCash_flows_date());
                            fosunbondrpaytplansEntity1.setZjcashflowsdate(flowEntity.getCash_flows_date());
                            //fosunbondrpaytplansEntity1.setCash_flows_per_cny100par(flowEntity.getCash_flows_per_cny100par());
                            fosunbondrpaytplansEntity1.setCash_flows_per_cny100par(Accrued_interest_per_cny100par.add(Accrued_principal_per_cny100par));

                            fosunbondrpaytplansEntity1.setCf_type(i==0?"付息":i==1?"还本":"");
                            fosunbondrpaytplansEntity1.setCoupon_rate(flowEntity.getCoupon_rate());
                            fosunbondrpaytplansEntity1.setCreatedtime(flowEntity.getCreatetime());
                            fosunbondrpaytplansEntity1.setCreator("");
                            //fosunbondrpaytplansEntity1.setHadrpayinterest();
                            fosunbondrpaytplansEntity1.setWindcode(flowEntity.getWindcode());
                            fosunbondrpaytplansEntity1.setLastmodifier("");
                            fosunbondrpaytplansEntity1.setLastmodifiedtime(flowEntity.getUpdatetime());
                            fosunbondrpaytplansEntity1.setStatsus("0");
                            fosunbondrpaytplansEntity1.setSourceid(flowEntity.getId());
                            fosunbondrpaytplansEntity1.setDelflag("0");
                            fosunbondrpaytplansEntityList.add(fosunbondrpaytplansEntity1);
                        }
                    }
                    else
                    {
                        FosunbondrpaytplansEntity fosunbondrpaytplansEntity1=new FosunbondrpaytplansEntity();
                        fosunbondrpaytplansEntity1.setId(UUID.randomUUID().toString());
                        //fosunbondrpaytplansEntity1.setAccrued_interest_per_cny100par(flowEntity.getAccrued_interest_per_cny100par());
                        //处理利息
                        //List<JtgkFosunbondFosunIdenticalissUerEntity> jtgkFosunbondFosunIdenticalissUerEntityList=jtgkFosunbondFosunIdenticalissUerRepository.findAllByWindcodeAndIssuedateBeforeOrderByIssuedateDesc(flowEntity.getWindcode(),flowEntity.getCash_flows_date());
                        List<JtgkFosunbondFosunDebtContractEntity> fosunDebtContractEntityList=jtgkFosunbondFosunDebtContractRepository.findAllByWindcodeAndSdateBeforeOrderBySdateDesc(flowEntity.getWindcode(),flowEntity.getCash_flows_date());
                        BigDecimal  Accrued_interest_per_cny100par=new BigDecimal(0);
                        BigDecimal  Accrued_principal_per_cny100par=new BigDecimal(0);
                        BigDecimal Outstandingbalance=new BigDecimal(0);
                        if (fosunDebtContractEntityList!=null&&fosunDebtContractEntityList.size()>0)
                        {
                            Outstandingbalance=fosunDebtContractEntityList.get(0).getOutstandingbalance();
                            Outstandingbalance=(Outstandingbalance==null?new BigDecimal(0):Outstandingbalance);
                        }
                        Accrued_interest_per_cny100par=Outstandingbalance.multiply(new BigDecimal(100000000)).multiply((flowEntity.getAccrued_interest_per_cny100par()==null?new BigDecimal(0):flowEntity.getAccrued_interest_per_cny100par())).divide(new BigDecimal(100));
                        fosunbondrpaytplansEntity1.setAccrued_interest_per_cny100par(Accrued_interest_per_cny100par);

                        Accrued_principal_per_cny100par=Outstandingbalance.multiply(new BigDecimal(100000000)).multiply((flowEntity.getAccrued_interest_per_cny100par()==null?new BigDecimal(0):flowEntity.getAccrued_principal_per_cny100par())).divide(new BigDecimal(100));

                        //fosunbondrpaytplansEntity1.setAccrued_principal_per_cny100par(flowEntity.getAccrued_principal_per_cny100par());
                        fosunbondrpaytplansEntity1.setAccrued_principal_per_cny100par(Accrued_principal_per_cny100par);

                        fosunbondrpaytplansEntity1.setCashflowsdate(flowEntity.getCash_flows_date());
                        fosunbondrpaytplansEntity1.setZjcashflowsdate(flowEntity.getCash_flows_date());
                        //fosunbondrpaytplansEntity1.setCash_flows_per_cny100par(flowEntity.getCash_flows_per_cny100par());
                        fosunbondrpaytplansEntity1.setCash_flows_per_cny100par(Accrued_interest_per_cny100par.add(Accrued_principal_per_cny100par));

                        fosunbondrpaytplansEntity1.setCf_type(flowEntity.getCf_type());
                        fosunbondrpaytplansEntity1.setCoupon_rate(flowEntity.getCoupon_rate());
                        fosunbondrpaytplansEntity1.setCreatedtime(flowEntity.getCreatetime());
                        fosunbondrpaytplansEntity1.setCreator("");
                        //fosunbondrpaytplansEntity1.setHadrpayinterest();
                        fosunbondrpaytplansEntity1.setWindcode(flowEntity.getWindcode());
                        fosunbondrpaytplansEntity1.setLastmodifier("");
                        fosunbondrpaytplansEntity1.setLastmodifiedtime(flowEntity.getUpdatetime());
                        fosunbondrpaytplansEntity1.setStatsus("0");
                        fosunbondrpaytplansEntity1.setSourceid(flowEntity.getId());
                        fosunbondrpaytplansEntity1.setDelflag("0");
                        fosunbondrpaytplansEntityList.add(fosunbondrpaytplansEntity1);
                    }



                }
            }
            if (fosunbondrpaytplansEntityList!=null&&fosunbondrpaytplansEntityList.size()>0)
            {
                fosunbondrpaytplansRepository.saveAll(fosunbondrpaytplansEntityList);
            }
            return "";
        }
        catch (Exception ex)
        {
            log.error(ex.getMessage());
            return ex.getMessage();
        }

    }

}
