package com.inspur.fosunbond.core.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.inspur.fosunbond.core.domain.result.Result;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface JtgkFosunBondBankBondInvestmentController {
    @POST
    @Path("/getbankbondinvestmentdetails")
    Result getBankBondInvestmentDetails(JsonNode jsonNode);

}
