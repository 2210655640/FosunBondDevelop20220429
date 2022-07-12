package com.inspur.fosunbond.core.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class JtgkFosunBondAmountAllocationDto {
    /*
     *AMOUNTATNOTE
     */
    private  String amountatnote;

    /*
     *PERIODICCYCLE
     */
    private  String periodiccycle;

    /*
     *COMMITRATIO
     */
    private BigDecimal commitratio;

    /*
     *COMMITAMOUNT
     */
    private  BigDecimal commitamount;

    /*
     *BANKLOANID
     */
    private  String bankloanid;

    /*
     *BANKROLE
     */
    private  String bankrole;

    /*
     *AMOUNTATBANKTYPESHORTNAME
     */
    private  String amountatbanktypeshortname;

    /*
     *AMOUNTATICON
     */
    private  String amountaticon;
}
