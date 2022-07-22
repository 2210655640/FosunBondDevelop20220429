package com.inspur.fosunbond.core.domain.dto;

import lombok.Data;


import java.sql.Timestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class JtgkFosunBondGuaranteeContractDto {
    /*
     *GUARANTEESTARTDATE
     */

    private String  guaranteestartdate;

    /*
     *COLLATERALAMOUNT
     */
    private BigDecimal collateralamount;

    /*
     *GUARANTEE
     */
    private  String guarantee;

    /*
     *GUARANTEEAMOUNT
     */
    private  BigDecimal guaranteeamount;

    /*
     *GUARANTEEMETHOD
     */
    private  String guaranteemethod;

    /*
     *LOANAMOUNT
     */
    private  BigDecimal loanamount;

    /*
     *BANKLOANID
     */
    private  String bankloanid;

    /*
     *GUARANTEEEXPIREDATE
     */

    private String  guaranteeexpiredate;
}
