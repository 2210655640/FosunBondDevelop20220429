package com.inspur.fosunbond.core.domain.service;


import com.inspur.edp.cdf.component.api.annotation.GspComponent;
import com.inspur.fosunbond.core.domain.entity.JtgkFosunBondBfblinterestrateEntity;
import com.inspur.fosunbond.core.domain.entity.JtgkFosunbondT_baserateEntity;
import com.inspur.fosunbond.core.domain.repository.JtgkFosunBondBfblinterestrateRepository;
import com.inspur.fosunbond.core.domain.repository.JtgkFosunBondBfblinterestrateverRepository;
import com.inspur.fosunbond.core.domain.repository.JtgkFosunbondT_baserateRepository;
import com.inspur.fosunbond.core.domain.utils.JtgkFosunbondPublicMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@GspComponent(id="AD2021-AF-KS-ZD-0WDRATE")//定义通用构件由调度任务执行
@Slf4j
@Service
public class JtgkFosunbondFosunSynchroBaseRateFromWD {
    @Autowired
    private JtgkFosunBondBfblinterestrateRepository jtgkFosunBondBfblinterestrateRepository;
    @Autowired
    private JtgkFosunBondBfblinterestrateverRepository jtgkFosunBondBfblinterestrateverRepository;
    @Autowired
    private JtgkFosunbondT_baserateRepository jtgkFosunbondT_baserateRepository;

    @Transactional(rollbackFor={Exception.class})
    public  String SyncWDBaseRate()
    {
        try
        {

            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            String predate=dateFormat.format(JtgkFosunbondPublicMethod.getPreDay(new Date()));
            String nowdate=dateFormat.format(new Date());
            //获取前一天的数据
            //获取一年期数据
            List<JtgkFosunbondT_baserateEntity> jtgkFosunbondT_baserateEntityListone=jtgkFosunbondT_baserateRepository.getdatabyupdatetime(predate,"M0096870");
            if(jtgkFosunbondT_baserateEntityListone!=null&&jtgkFosunbondT_baserateEntityListone.size()>0)
            {
                List<JtgkFosunBondBfblinterestrateEntity> jtgkFosunBondBfblinterestrateEntityList=jtgkFosunBondBfblinterestrateRepository.findAllByIrterm("01");
                //存在
                if (jtgkFosunBondBfblinterestrateEntityList!=null&&jtgkFosunBondBfblinterestrateEntityList.size()>0)
                {

                }
                //不存在
                else
                {
                    JtgkFosunbondT_baserateEntity t_baserateEntity=jtgkFosunbondT_baserateEntityListone.get(0);
                    JtgkFosunBondBfblinterestrateEntity jtgkFosunBondBfblinterestrateEntity=new JtgkFosunBondBfblinterestrateEntity();
                    jtgkFosunBondBfblinterestrateEntity.setId(UUID.randomUUID().toString());//设置主键
                    jtgkFosunBondBfblinterestrateEntity.setAnnualdays(360);
                    jtgkFosunBondBfblinterestrateEntity.setDelflag("0");
                    jtgkFosunBondBfblinterestrateEntity.setAnnualir(t_baserateEntity.getValue());

                }
                for (JtgkFosunbondT_baserateEntity baserateEntity:jtgkFosunbondT_baserateEntityListone)
                {

                }
            }

        }
        catch (Exception ex)
        {
            log.error(ex.getMessage());
            ex.printStackTrace();
            return  ex.getMessage();
        }
        return "";
    }
}
