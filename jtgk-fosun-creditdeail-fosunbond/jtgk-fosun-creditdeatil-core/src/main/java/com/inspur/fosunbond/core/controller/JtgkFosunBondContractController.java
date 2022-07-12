package com.inspur.fosunbond.core.controller;

import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/contract")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface JtgkFosunBondContractController {
    @POST
    @Path("/getcontractdetailbyid")
    String getContractDetailById(@RequestBody String paramStr);

}
