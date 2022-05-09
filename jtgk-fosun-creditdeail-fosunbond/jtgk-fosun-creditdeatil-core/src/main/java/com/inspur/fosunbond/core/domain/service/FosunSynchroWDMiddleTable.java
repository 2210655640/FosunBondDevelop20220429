package com.inspur.fosunbond.core.domain.service;


import com.inspur.edp.cdf.component.api.annotation.GspComponent;
import com.inspur.fosunbond.core.domain.entity.FosunbondrpaytplansEntity;
import com.inspur.fosunbond.core.domain.entity.T_debt_cashflowEntity;
import com.inspur.fosunbond.core.domain.repository.FosunbondrpaytplansRepository;
import com.inspur.fosunbond.core.domain.repository.T_debt_cashflowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@GspComponent(id="AD2021-AF-KS-ZD-0WDBOND")//定义通用构件由调度任务执行
@Slf4j
@Service
public class FosunSynchroWDMiddleTable {

    @Autowired
    private T_debt_cashflowRepository t_debt_cashflowRepository;
    @Autowired
    private FosunbondrpaytplansRepository fosunbondrpaytplansRepository;

   @Transactional
    public String syncWDcashflow()
    {
        try
        {
            List<T_debt_cashflowEntity> t_debt_cashflowEntityList=t_debt_cashflowRepository.findAll();
            List<FosunbondrpaytplansEntity> fosunbondrpaytplansEntityList=new ArrayList<>();
            for (T_debt_cashflowEntity flowEntity:t_debt_cashflowEntityList)
            {
                String flowid=flowEntity.getId();
                Optional<FosunbondrpaytplansEntity> fosunbondrpaytplansEntity=fosunbondrpaytplansRepository.findById(flowid);
                if (!fosunbondrpaytplansEntity.isPresent())
                {
                    FosunbondrpaytplansEntity fosunbondrpaytplansEntity1=new FosunbondrpaytplansEntity();
                    fosunbondrpaytplansEntity1.setId(flowEntity.getId());
                    fosunbondrpaytplansEntity1.setAccrued_interest_per_cny100par(flowEntity.getAccrued_interest_per_cny100par());
                    fosunbondrpaytplansEntity1.setAccrued_principal_per_cny100par(flowEntity.getAccrued_principal_per_cny100par());
                    fosunbondrpaytplansEntity1.setCash_flows_date(flowEntity.getCash_flows_date());
                    fosunbondrpaytplansEntity1.setCash_flows_per_cny100par(flowEntity.getCash_flows_per_cny100par());
                    fosunbondrpaytplansEntity1.setCf_type(flowEntity.getCf_type());
                    fosunbondrpaytplansEntity1.setCoupon_rate(flowEntity.getCoupon_rate());
                    fosunbondrpaytplansEntity1.setCreatedtime(flowEntity.getCreatetime());
                    fosunbondrpaytplansEntity1.setCreator("");
                    //fosunbondrpaytplansEntity1.setHadrpayinterest();
                    fosunbondrpaytplansEntity1.setWindcode(flowEntity.getWindcode());
                    fosunbondrpaytplansEntity1.setLastmodifier("");;
                    fosunbondrpaytplansEntity1.setLastmodifiedtime(flowEntity.getUpdatetime());
                    fosunbondrpaytplansEntity1.setStatsus("0");
                    fosunbondrpaytplansEntityList.add(fosunbondrpaytplansEntity1);

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
