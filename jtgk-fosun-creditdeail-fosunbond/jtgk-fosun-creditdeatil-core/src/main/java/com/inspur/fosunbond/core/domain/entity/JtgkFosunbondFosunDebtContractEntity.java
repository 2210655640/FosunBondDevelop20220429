package com.inspur.fosunbond.core.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="FOSUNDEBTCONTRACT")
public class JtgkFosunbondFosunDebtContractEntity
{
    /*
     *LAST_TRADE_DAY
     */
    //private Date last_trade_day;

    /*
     *WINDCODE
     */
    private  String windcode;

    /*
     *COUPONRATE2
     */
    private BigDecimal couponrate2;

    /*
     *ISSUER_RATING
     */
    private  String issuer_rating;

    /*
     *SDATE
     */
    private  Date sdate;

    /*
     *MULTIMKTORNOT
     */
    private  String multimktornot;

    /*
     *CREDITRATING
     */
    private  String creditrating;

    /*
     *ISSUE_FIRSTISSUE
     */
    private  Date issue_firstissue;

    /*
     *RATE_LATESTMIR_CNBD
     */
    private  String rate_latestmir_cnbd;

    /*
     *ISSUE_ISSUEPRICE
     */
    private  BigDecimal issue_issueprice;

    /*
     *CREATETIME
     */
    private  Date createtime;

    /*
     *ISSUEAMOUNT
     */
    private  BigDecimal issueamount;

    /*
     *COUPON
     */
    private  String coupon;

    /*
     *CURRENTSTATE
     */
    private  String currentstate;

    /*
     *NXCUPN2
     */
    private  Integer nxcupn2;

    /*
     *INDUSTRY_GICS
     */
    private  String industry_gics;

    /*
     *ISSUE_REGNUMBER
     */
    private  String issue_regnumber;

    /*
     *AGENCY_LEADUNDERWRITER
     */
    private  String agency_leadunderwriter;

    /*
     *COUPONDATETXT
     */
    private  String coupondatetxt;

    /*
     *ACTUALBENCHMARK
     */
    private  String actualbenchmark;

    /*
     *REPURCHASEDATE
     */
    private  Date repurchasedate;

    /*
     *SEC_NAME
     */
    private  String sec_name;

    /*
     *sec_status
     */
    private  String sec_status;
    /*
     *ID
     */
    @Id
    private  String id;

    /*
     *CARRYDATE
     */
    private  Date carrydate;

    /*
     *UPDATETIME
     */
    private  Date updatetime;

    /*
     *MATURITYDATE
     */
    private  Date maturitydate;

    /*
     *OUTSTANDINGBALANCE
     */
    private  BigDecimal outstandingbalance;

    /*
     *CLAUSEABBR
     */
    private  String clauseabbr;

    /*
     *FULLNAME
     */
    private  String fullname;

    /*
     *ABS_INDUSTRY1
     */
    private  String abs_industry1;

    /*
     *INTERESTFREQUENCY
     */
    private  Integer interestfrequency;

    /*
     *ISSUE_ANNOUNCEMENT
     */
    private  Date issue_announcement;

    /*
     *CLAUSE
     */
    private  String clause;

    /*
     *BONDTYPE
     */
    private  String bondtype;

    /*
     *AGENCY_GUARANTOR
     */
    private  String agency_guarantor;

    /*
     *ISSUE_ISSUEMETHOD
     */
    private  String issue_issuemethod;

    /*
     *RATE_CREDITRATINGAGENCY
     */
    private  String rate_creditratingagency;

    /*
     *TERM2
     */
    private  String term2;

    /*
     *ISSUE_LASTISSUE
     */
    private  Date issue_lastissue;

    /*
     *IPO_DATE
     */
    private  Date ipo_date;

    /*
     *EXCH_CITY
     */
    private  String exch_city;

    /*
     *COUPONTXT
     */
    private  String coupontxt;

    /*
     *PTMYEAR
     */
    //private  BigDecimal ptmyear;
    private String termnote1;
    /*
     *REPO_LASTESTDATE
     */
    private  Date repo_lastestdate;

    /*
     *ADVANCECREDIT_DESC
     */
    private  String advancecredit_desc;

    /*
     *NXOPTIONDATE
     */
    private  String nxoptiondate;

    /*
     *NXCUPN
     */
    private  Date nxcupn;

    /*
     *COMP_NAME
     */
    private  String comp_name;
    /*
     *agency_grnttype
     */
    private  String agency_grnttype;

    /*
     *sourceid
     */
    private  String sourceid;

    /*
     *主承销商(简称)
     */
    private  String agency_leadunderwritersn;
    /*
     *发行人中文简称
     */
    private  String issuershortened;

    /*
     *币种
     */
    private  String curr;
    /*
    *统一社会信用代码
     */
    private  String registernumber;

    /*
     *发行注册日期
     */
    private Date issue_regdate;
    /*
     *发行注册额度
     */
    private BigDecimal issue_regamount;
    /*
     *额度有效期
     */
    private Date expirationdata;


}
