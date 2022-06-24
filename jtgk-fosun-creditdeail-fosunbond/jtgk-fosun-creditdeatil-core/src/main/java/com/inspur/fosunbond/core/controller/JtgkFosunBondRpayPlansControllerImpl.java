package com.inspur.fosunbond.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspur.fosunbond.core.domain.entity.FosunDebtContractHistory1Entity;
import com.inspur.fosunbond.core.domain.entity.FosunbondrpaytplansEntity;
import com.inspur.fosunbond.core.domain.repository.FosunbondrpaytplansRepository;
import com.inspur.fosunbond.core.domain.result.Result;
import io.iec.edp.caf.boot.context.CAFContext;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Controller
@Slf4j
public class JtgkFosunBondRpayPlansControllerImpl implements JtgkFosunBondRpayPlansController {
    @Autowired
    private FosunbondrpaytplansRepository fosunbondrpaytplansRepository;
    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public Result getFosunBondrpayPlansByWindCode(JsonNode jsonNode) {
        Result result=new Result();
        try {

            List<FosunbondrpaytplansEntity> fosunbondrpaytplansEntityList=getFosunbondrpaytplans(jsonNode);
            //for (FosunbondrpaytplansEntity planEntity:fosunbondrpaytplansEntityList)
            {
                // BigDecimal interestpercny=planEntity.getAccrued_interest_per_cny100par();

            }

            return result.ok(fosunbondrpaytplansEntityList);
        }
        catch (Exception ex)
        {
            log.error(ex.getMessage());
            ex.printStackTrace();
            return result.error500(ex.getMessage());
        }

    }

    @Override
    public Result deleteFosunBondrpayPlansById(JsonNode jsonNode) throws ParseException {
        try{
            Result result=new Result();
            String deleteidsStr=jsonNode.get("deleteids").asText();
            List<String> listdelete= Arrays.asList(deleteidsStr.split(","));
            for (String deleteid:listdelete)
            {
                Optional<FosunbondrpaytplansEntity> fosunbondrpaytplansEntityoption=fosunbondrpaytplansRepository.findById(deleteid);
                if (fosunbondrpaytplansEntityoption.isPresent())
                {
                    FosunbondrpaytplansEntity fosunbondrpaytplansEntity=fosunbondrpaytplansEntityoption.get();
                    fosunbondrpaytplansEntity.setDelflag("1");//设置软删除标识
                    fosunbondrpaytplansEntity.setStatsus("0");//设置状态
                    //fosunbondrpaytplansEntity.setCreator(CAFContext.current.getUserId());//修改创建人
                    Date nowtime=null;
                    nowtime=dateFormat.parse(dateFormat.format(new Date()));
                    //fosunbondrpaytplansEntity.setCreatedtime(createtime);//修改创建时间
                    fosunbondrpaytplansEntity.setLastmodifier(CAFContext.current.getUserId());
                    fosunbondrpaytplansEntity.setLastmodifiedtime(nowtime);


                }
            }
            List<FosunbondrpaytplansEntity> fosunbondrpaytplansEntityList=getFosunbondrpaytplans(jsonNode);
            return result.ok(fosunbondrpaytplansEntityList);
        }
        catch (Exception ex)
        {
            Result result=new Result();
          log.error(ex.getMessage());
          ex.printStackTrace();
          return result.error500(ex.getMessage());
        }


    }

    @Override
    public Result saveFosunBondrpayPlans(JsonNode jsonNode) throws JsonProcessingException {
        try {
            Result result=new Result();
            String fosunbondrpayplansEntity=jsonNode.get("fosunbondrpayplansentity").asText();
            ObjectMapper objectMapper=new ObjectMapper();
            SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            objectMapper.setDateFormat(smt);
            TypeReference<List<FosunbondrpaytplansEntity>> ref=new TypeReference<List<FosunbondrpaytplansEntity>>(){};
            List<FosunbondrpaytplansEntity> fosunbondrpaytplansEntityList =objectMapper.readValue(fosunbondrpayplansEntity,ref);
            if (fosunbondrpaytplansEntityList!=null&&fosunbondrpaytplansEntityList.size()>0)
            {
                for (FosunbondrpaytplansEntity planentity:fosunbondrpaytplansEntityList)
                {
                    Boolean isExist=fosunbondrpaytplansRepository.existsById(planentity.getId());
                    if (isExist)
                    {
                        fosunbondrpaytplansRepository.deleteById(planentity.getId());
                        fosunbondrpaytplansRepository.save(planentity);
                    }
                    else
                    {
                        fosunbondrpaytplansRepository.save(planentity);
                    }
                }
            }
            return result.ok(getFosunbondrpaytplans(jsonNode));
        }
        catch (Exception ex)
        {
            Result result=new Result();
            log.error(ex.getMessage());
            ex.printStackTrace();
            return result.error500(ex.getMessage());
        }

    }

    @Override
    public Result syncRateFromBond(JsonNode jsonNode) {
        String windcode=jsonNode.get("windcode").asText();
        String curr=jsonNode.get("curr").asText();
        String originalrate=jsonNode.get("originalrate").asText();
        List<FosunbondrpaytplansEntity>  fosunbondrpaytplansEntityList=fosunbondrpaytplansRepository.findAllByWindcodeOrderByZjcashflowsdateAsc(windcode);
        if(fosunbondrpaytplansEntityList!=null&&fosunbondrpaytplansEntityList.size()>0)
        {
            for (FosunbondrpaytplansEntity planEntity:fosunbondrpaytplansEntityList)
            {
                if ("".equals(planEntity.getCurr())||planEntity.getCurr()==null)
                {
                    planEntity.setCurr(curr);
                    planEntity.setOriginalorexerate(originalrate);
                }

            }
        }
        Result result=new Result();
        return result.ok();
    }

    private List<FosunbondrpaytplansEntity> getFosunbondrpaytplans(JsonNode jsonNode)
    {
        String windcode=jsonNode.get("windcode").asText();
        List<FosunbondrpaytplansEntity>  fosunbondrpaytplansEntityList=fosunbondrpaytplansRepository.findAllByWindcodeAndDelflagNotOrderByZjcashflowsdateAsc(windcode,"1");
        return fosunbondrpaytplansEntityList;
    }
}
