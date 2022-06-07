package com.inspur.fosunbond.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.inspur.fosunbond.core.domain.result.Result;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;


/**
 * @author xiaoyinchuan
 * 2022-2-10
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface CreaditDatil111Controller {
    /**
     * 日志明细更新处理
     * @param disDataStr 异构系统报文
     * 2022-2-10
     */
    @POST
    @Path("/updateCeaditdatil")
    String updateCeaditdatil(@RequestBody String disDataStr);

    @POST
    @Path("/getfosunrepaymentappbyid")
    String getFoSunRepaymentAppById(JsonNode jsonNode);

    @POST
    @Path("/getfosundebtcon")
    Result getFosunDebtContractHistoryByVersionDate(JsonNode jsonNode) throws ParseException;

    @POST
    @Path("/savefosundebtcontracthistorybyversiondate")
    Result saveFosunDebtContractHistoryByVersionDate(JsonNode jsonNode) throws JsonProcessingException, ParseException;
    @POST
    @Path("syncbomdmsgfrommiddletable")
    String syncBondMsgFromMiddleTable(JsonNode jsonNode);
}
