package com.inspur.fosunbond.core.config;

import com.inspur.fosunbond.core.controller.*;
import com.inspur.fosunbond.core.domain.service.FosunSynchroWDMiddleTable;
import io.iec.edp.caf.rest.RESTEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.inspur.fosunbond.core")
@EnableJpaRepositories("com.inspur.fosunbond.core.domain.repository")
@EntityScan( basePackages ={"com.inspur.fosunbond.core.domain.entity"})
public class JtgkZjglFosunbondConfig {


    @Autowired
    private CreaditDatil111Controller creaditDatil111Controller;
    @Autowired
    private JtgkFosunBondRpayPlansController jtgkFosunBondRpayPlansController;

    @Bean
    public FosunSynchroWDMiddleTable fosunSynchroWDMiddleTable() {
        return new FosunSynchroWDMiddleTable();
    }
    @Bean
    public RESTEndpoint jtgkCreditDetailfosunbondRESTEndpoint(){
        //return  new RESTEndpoint("/jtgk/fosun/v1.0/creditdetail",creaditDatilController);
        return  new RESTEndpoint("/jtgk/fosunbond/v1.0/getfsun", creaditDatil111Controller,jtgkFosunBondRpayPlansController);
    }
}
