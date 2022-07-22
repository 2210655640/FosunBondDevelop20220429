package com.inspur.fosunbond.core.domain.dto;

import lombok.Data;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;

@Data
public class JtgkFosunBondContractDetailDto {
    @Transient
    private String resultCode;
    @Transient
    private String resultMsg;

    /*
     *INTERESTSETDATE
     */
    private  String interestsetdate;

    /*
     *ANNUALINTERESTRATE
     */
    private  String annualinterestrate;

    /*
     *FLOATINGMETHOD
     */
    private  String floatingmethod;

    /*
     *BANKNAME
     */
    private  String bankname;

    /*
     *BENCHINTERESTRATE
     */
    private  BigDecimal benchinterestrate;

    /*
     *FLOATINGTYPE
     */
    private  String floatingtype;

    /*
     *EFFECTIVEDATE
     */
    private  String effectivedate;

    /*
     *BALANCEAMOUNT
     */
    private  BigDecimal balanceamount;

    /*
     *TOTALAMOUNT
     */
    private  BigDecimal totalamount;

    /*
     *STATUS
     */
    private  String status;

    /*
     *EXPIREDATE
     */
    private  String expiredate;

    /*
     *FLOATINGVALUE
     */
    private  BigDecimal floatingvalue;

    /*
     *LOANCATEGORY
     */
    private  String loancategory;

    /*
     *CURRENCYCODE
     */
    private  String currencycode;

    /*
     *CONTRNO
     */
    private  String contrno;

    /*
     *ID
     */
    private  String id;

    /*
     *BANKTYPENAME
     */
    private  String banktypename;

    /*
     *WITHDRAWABLEAMOUNT
     */
    private BigDecimal withdrawableamount;

    /*
     *BANKSHORTNAME
     */
    private  String bankshortname;

    /*
     *BORROWERSHORTNAME
     */
    private  String borrowershortname;

    /*
     *INTERESTSETPERIOD
     */
    private  String interestsetperiod;

    /*
     *BORROWERNAME
     */
    private  String borrowername;

    /*
     *FLOATINGRATEMETHOD
     */
    private  String floatingratemethod;

    /*
     *BANKTYPESHORTNAME
     */
    private  String banktypeshortname;

    /*
     *RATECATEGORY
     */
    private  String ratecategory;

    /*
     *ANNUALINTERESTDAYS
     */
    private  String annualinterestdays;

    /*
     *REPAYMETHOD
     */
    private  String repaymethod;

    @Transient
    private List<JtgkFosunBondGuaranteeContractDto> wntyconList;
    @Transient
    private List<JtgkFosunBondAmountAllocationDto> amountatList;
}
