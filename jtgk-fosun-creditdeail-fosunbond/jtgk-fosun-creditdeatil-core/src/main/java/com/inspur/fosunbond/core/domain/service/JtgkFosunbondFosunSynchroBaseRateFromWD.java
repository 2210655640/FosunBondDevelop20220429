package com.inspur.fosunbond.core.domain.service;


import com.inspur.edp.cdf.component.api.annotation.GspComponent;
import com.inspur.fosunbond.core.domain.entity.JtgkFosunBondBfblinterestrateEntity;
import com.inspur.fosunbond.core.domain.entity.JtgkFosunBondBfblinterestrateverEntity;
import com.inspur.fosunbond.core.domain.entity.JtgkFosunbondT_baserateEntity;
import com.inspur.fosunbond.core.domain.repository.JtgkFosunBondBfblinterestrateRepository;
import com.inspur.fosunbond.core.domain.repository.JtgkFosunBondBfblinterestrateverRepository;
import com.inspur.fosunbond.core.domain.repository.JtgkFosunbondT_baserateRepository;
import com.inspur.fosunbond.core.domain.utils.JtgkFosunbondPublicMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            operateData("M0096870","01");
            operateData("M0331299","02");

        }
        catch (Exception ex)
        {
            log.error(ex.getMessage());
            ex.printStackTrace();
            return  ex.getMessage();
        }
        return "";
    }
    /*
    *baseRateTypeNo M0096870 一年期 M0331299 五年期
    *bfblInterestRateNo 01 一年期 02  五年期
     */
    public void operateData(String baseRateTypeNo,String bfblInterestRateNo) throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");
        String predate=dateFormat.format(JtgkFosunbondPublicMethod.getPreDay(new Date()));
        String nowdate=dateFormat.format(new Date());
        Date dateNow=dateFormat.parse(dateFormat.format(new Date()));
        //获取前一天的数据
        //获取一年期数据
        List<JtgkFosunbondT_baserateEntity> jtgkFosunbondT_baserateEntityListone=jtgkFosunbondT_baserateRepository.getdatabyupdatetime(nowdate,baseRateTypeNo);
        List<JtgkFosunbondT_baserateEntity> jtgkFosunbondT_baserateEntityListhis=jtgkFosunbondT_baserateRepository.gethistorydatabyupdatetime(baseRateTypeNo);
        if(jtgkFosunbondT_baserateEntityListone!=null&&jtgkFosunbondT_baserateEntityListone.size()>0)
        {
            List<JtgkFosunBondBfblinterestrateEntity> jtgkFosunBondBfblinterestrateEntityList=jtgkFosunBondBfblinterestrateRepository.findAllByIrterm(bfblInterestRateNo);
            JtgkFosunbondT_baserateEntity t_baserateEntity=jtgkFosunbondT_baserateEntityListone.get(0);
            String mainid=UUID.randomUUID().toString();
            //存在 则更新利率和时间
            if (jtgkFosunBondBfblinterestrateEntityList!=null&&jtgkFosunBondBfblinterestrateEntityList.size()>0)
            {
                JtgkFosunBondBfblinterestrateEntity jtgkFosunBondBfblinterestrateEntity=jtgkFosunBondBfblinterestrateEntityList.get(0);
                mainid=jtgkFosunBondBfblinterestrateEntity.getId();
                jtgkFosunBondBfblinterestrateRepository.updatebyID(t_baserateEntity.getValue(),t_baserateEntity.getSdate(),mainid);
            }
            //不存在
            else
            {


                JtgkFosunBondBfblinterestrateEntity jtgkFosunBondBfblinterestrateEntity=new JtgkFosunBondBfblinterestrateEntity();
                jtgkFosunBondBfblinterestrateEntity.setId(mainid);//设置主键
                jtgkFosunBondBfblinterestrateEntity.setAnnualdays(360);
                jtgkFosunBondBfblinterestrateEntity.setDelflag("0");
                jtgkFosunBondBfblinterestrateEntity.setAnnualir(t_baserateEntity.getValue());
                jtgkFosunBondBfblinterestrateEntity.setIrdate(t_baserateEntity.getSdate());
                jtgkFosunBondBfblinterestrateEntity.setIrterm(bfblInterestRateNo);
                jtgkFosunBondBfblinterestrateEntity.setIrtype("dc1852e6-16d7-45ac-8a02-2d63bd3db724");
                jtgkFosunBondBfblinterestrateEntity.setOperatedate(dateNow);
                jtgkFosunBondBfblinterestrateEntity.setOperator("66ea3b0d-8117-3aa7-1e93-057ed3c627ce");
                jtgkFosunBondBfblinterestrateEntity.setOperatorname("刁敬厚");
                jtgkFosunBondBfblinterestrateEntity.setRatestatus(2);
                jtgkFosunBondBfblinterestrateEntity.setRemark("");
                jtgkFosunBondBfblinterestrateEntity.setTimestamp_createdby("刁敬厚");
                jtgkFosunBondBfblinterestrateEntity.setTimestamp_createdon(dateFormat2.parse(dateFormat2.format(new Date())));
                jtgkFosunBondBfblinterestrateEntity.setTimestamp_lastchangedby("刁敬厚");
                jtgkFosunBondBfblinterestrateEntity.setTimestamp_createdon(dateFormat2.parse(dateFormat2.format(new Date())));
                jtgkFosunBondBfblinterestrateEntity.setVersion(dateFormat2.parse(dateFormat2.format(new Date())));
                jtgkFosunBondBfblinterestrateRepository.save(jtgkFosunBondBfblinterestrateEntity);


            }
            //处理历史记录
            List<JtgkFosunBondBfblinterestrateverEntity> rateverList=jtgkFosunBondBfblinterestrateverRepository.findAllByParentidOrderByStartdateDesc(mainid);
            //存在历史记录
            if (rateverList!=null&&rateverList.size()>0)
            {
                int maxVersionNo=rateverList.get(0).getVersionno();
                List<JtgkFosunBondBfblinterestrateverEntity> jtgkFosunBondBfblinterestrateverEntityList=new ArrayList<>();
                //一年期历史记录
                for(int i=jtgkFosunbondT_baserateEntityListhis.size()-1;i>=0;i--)
                {

                    JtgkFosunbondT_baserateEntity rateentity=jtgkFosunbondT_baserateEntityListhis.get(i);
                    Date sdate=rateentity.getSdate();
                    //获取数据中是否存在当前sdate的数据
                    List<JtgkFosunBondBfblinterestrateverEntity> rateverbystartdate=jtgkFosunBondBfblinterestrateverRepository.findAllByParentidAndStartdate(mainid,sdate);
                    if (rateverbystartdate!=null&&rateverbystartdate.size()>0)//存在相同开始时间的数据则更新
                    {
                        rateverbystartdate.get(0).setAnnualir(rateentity.getValue());
                    }
                    else
                    {
                        //第一次循环时将开始时间存入原一年期历史记录结束时间
//                        if(i==jtgkFosunbondT_baserateEntityListone.size()-1)
//                        {
//                            rateverList.get(0).setEnddate(rateentity.getSdate());
//                        }

                        JtgkFosunBondBfblinterestrateverEntity jtgkFosunBondBfblinterestrateverEntity=new JtgkFosunBondBfblinterestrateverEntity();
                        jtgkFosunBondBfblinterestrateverEntity.setId(UUID.randomUUID().toString());
                        jtgkFosunBondBfblinterestrateverEntity.setAnnualir(rateentity.getValue());
//                        if (i!=0)//如果不是最后一条数据则结束日期等于下一条的sdate即开始日期
//                        {
//                            jtgkFosunBondBfblinterestrateverEntity.setEnddate(jtgkFosunbondT_baserateEntityListhis.get(i-1).getSdate());
//                        }
                        jtgkFosunBondBfblinterestrateverEntity.setOperatedate(dateNow);
                        jtgkFosunBondBfblinterestrateverEntity.setOperator("66ea3b0d-8117-3aa7-1e93-057ed3c627ce");
                        jtgkFosunBondBfblinterestrateverEntity.setOperatorname("刁敬厚");
                        jtgkFosunBondBfblinterestrateverEntity.setParentid(mainid);
                        jtgkFosunBondBfblinterestrateverEntity.setRemark("");
                        jtgkFosunBondBfblinterestrateverEntity.setStartdate(rateentity.getSdate());
                        jtgkFosunBondBfblinterestrateverEntity.setTimestamp_createdby("刁敬厚");
                        jtgkFosunBondBfblinterestrateverEntity.setTimestamp_createdon(dateFormat2.parse(dateFormat2.format(new
                                Date())));
                        jtgkFosunBondBfblinterestrateverEntity.setTimestamp_lastchangedby("刁敬厚");
                        jtgkFosunBondBfblinterestrateverEntity.setTimestamp_lastchangedon(dateFormat2.parse(dateFormat2.format(new
                                Date())));
                        //jtgkFosunBondBfblinterestrateverEntity.setVersionno(jtgkFosunbondT_baserateEntityListhis.size()-i+maxVersionNo);
                        jtgkFosunBondBfblinterestrateverEntityList.add(jtgkFosunBondBfblinterestrateverEntity);
                    }

                }
                if (jtgkFosunBondBfblinterestrateverEntityList.size()>0)
                {
                    jtgkFosunBondBfblinterestrateverRepository.saveAll(jtgkFosunBondBfblinterestrateverEntityList);
                }
                //重新设置versionno及enddate
                List<JtgkFosunBondBfblinterestrateverEntity> rateverList1=jtgkFosunBondBfblinterestrateverRepository.findAllByParentidOrderByStartdateDesc(mainid);
                if(rateverList1!=null&&rateverList1.size()>0)
                {
                    for(int i=rateverList1.size()-1;i>=0;i--)
                    {
                        if (i!=0)//如果不是最后一条数据则结束日期等于下一条的sdate即开始日期
                        {
                            rateverList1.get(i).setEnddate(rateverList1.get(i-1).getStartdate());//设置前一条数据的结束日期为前一条的开始日期
                        }
                        rateverList1.get(i).setVersionno(rateverList1.size()-i);
                    }
                }

            }
            //不存在历史记录
            else
            {
                List<JtgkFosunBondBfblinterestrateverEntity> jtgkFosunBondBfblinterestrateverEntityList=new ArrayList<>();
                //一年期历史记录
                for(int i=jtgkFosunbondT_baserateEntityListhis.size()-1;i>=0;i--)
                {
                    JtgkFosunbondT_baserateEntity rateentity=jtgkFosunbondT_baserateEntityListhis.get(i);

                    JtgkFosunBondBfblinterestrateverEntity jtgkFosunBondBfblinterestrateverEntity=new JtgkFosunBondBfblinterestrateverEntity();
                    jtgkFosunBondBfblinterestrateverEntity.setId(UUID.randomUUID().toString());
                    jtgkFosunBondBfblinterestrateverEntity.setAnnualir(rateentity.getValue());
                    if (i!=0)//如果不是最后一条数据则介绍日期等于下一条的sdate即开始日期
                    {
                        jtgkFosunBondBfblinterestrateverEntity.setEnddate(jtgkFosunbondT_baserateEntityListhis.get(i-1).getSdate());
                    }
                    jtgkFosunBondBfblinterestrateverEntity.setOperatedate(dateNow);
                    jtgkFosunBondBfblinterestrateverEntity.setOperator("66ea3b0d-8117-3aa7-1e93-057ed3c627ce");
                    jtgkFosunBondBfblinterestrateverEntity.setOperatorname("刁敬厚");
                    jtgkFosunBondBfblinterestrateverEntity.setParentid(mainid);
                    jtgkFosunBondBfblinterestrateverEntity.setRemark("");
                    jtgkFosunBondBfblinterestrateverEntity.setStartdate(rateentity.getSdate());
                    jtgkFosunBondBfblinterestrateverEntity.setTimestamp_createdby("刁敬厚");
                    jtgkFosunBondBfblinterestrateverEntity.setTimestamp_createdon(dateFormat2.parse(dateFormat2.format(new
                            Date())));
                    jtgkFosunBondBfblinterestrateverEntity.setTimestamp_lastchangedby("刁敬厚");
                    jtgkFosunBondBfblinterestrateverEntity.setTimestamp_lastchangedon(dateFormat2.parse(dateFormat2.format(new
                            Date())));
                    jtgkFosunBondBfblinterestrateverEntity.setVersionno(jtgkFosunbondT_baserateEntityListhis.size()-i);
                    jtgkFosunBondBfblinterestrateverEntityList.add(jtgkFosunBondBfblinterestrateverEntity);
                }
                if (jtgkFosunBondBfblinterestrateverEntityList.size()>0)
                {
                    jtgkFosunBondBfblinterestrateverRepository.saveAll(jtgkFosunBondBfblinterestrateverEntityList);
                }

            }


        }

    }
}
