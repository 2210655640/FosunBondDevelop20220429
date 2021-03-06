package com.inspur.fosunbond.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.inspur.fosunbond.core.domain.result.Result;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface JtgkFosunBondRpayPlansController {
    @POST
    @Path("/getfosunbondrpayplansbywindcode")
    Result getFosunBondrpayPlansByWindCode(JsonNode jsonNode);
    @POST
    @Path("/deletefosunbondrpayplansbyid")
    Result deleteFosunBondrpayPlansById(JsonNode jsonNode) throws ParseException;
    @POST
    @Path("/savefosunbondrpayplans")
    Result saveFosunBondrpayPlans(JsonNode jsonNode) throws JsonProcessingException;
    @POST
    @Path("/syncratefrombond")
    Result syncRateFromBond(JsonNode jsonNode);
}
