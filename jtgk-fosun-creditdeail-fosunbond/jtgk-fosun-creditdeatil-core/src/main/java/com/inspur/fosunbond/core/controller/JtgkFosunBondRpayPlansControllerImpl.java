package com.inspur.fosunbond.core.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.inspur.fosunbond.core.domain.entity.FosunbondrpaytplansEntity;
import com.inspur.fosunbond.core.domain.repository.FosunbondrpaytplansRepository;
import com.inspur.fosunbond.core.domain.result.Result;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class JtgkFosunBondRpayPlansControllerImpl implements JtgkFosunBondRpayPlansController {
    @Autowired
    private FosunbondrpaytplansRepository fosunbondrpaytplansRepository;
    @Override
    public Result getFosunBondrpayPlansByWindCode(JsonNode jsonNode) {
        Result result=new Result();
        String windcode=jsonNode.get("windcode").asText();
        List<FosunbondrpaytplansEntity>  fosunbondrpaytplansEntityList=fosunbondrpaytplansRepository.findAllByWindcode(windcode);
        for (FosunbondrpaytplansEntity planEntity:fosunbondrpaytplansEntityList)
        {

        }
        return result.ok(fosunbondrpaytplansEntityList);
    }

    @Override
    public Result deleteFosunBondrpayPlansById(JsonNode jsonNode) {
        return null;
    }

    @Override
    public Result saveFosunBondrpayPlans(JsonNode jsonNode) {
        return null;
    }
}
