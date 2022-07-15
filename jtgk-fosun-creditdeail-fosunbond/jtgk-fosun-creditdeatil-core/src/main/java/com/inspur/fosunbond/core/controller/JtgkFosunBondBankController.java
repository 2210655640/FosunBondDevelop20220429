package com.inspur.fosunbond.core.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/bank")
//@Consumes({MediaType.APPLICATION_JSON})
//@Produces(MediaType.APPLICATION_JSON)
public interface JtgkFosunBondBankController {
    @POST
    @Path("/getbanklist")
    String getBankList(@RequestBody String paramStr);
    @POST
    @Path("/uploadbankicon")
    String uploadBankIcon(MultipartBody multipartBody) throws IOException;
    @POST
    @Path("/syncbankaccount")
    String syncBankAccount(String bankaccount) throws JsonProcessingException;
}
