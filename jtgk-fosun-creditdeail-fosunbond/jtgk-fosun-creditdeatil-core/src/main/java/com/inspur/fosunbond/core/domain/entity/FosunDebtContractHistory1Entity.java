package com.inspur.fosunbond.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name="FOSUNDEBTCONTRACTHISTORY")
@IdClass(PrimaryKey.class)
public class FosunDebtContractHistory1Entity
{
    /*
     *COUPON
     */
    @JsonProperty(value = "COUPON")
    private  String coupon;

    /*
     *REPURCHASEDATE
     */
    @JsonProperty(value = "REPURCHASEDATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp repurchasedate;

    /*
     *AGENCY_GUARANTOR
     */
    @JsonProperty(value = "AGENCY_GUARANTOR")
    private  String agency_guarantor;

    /*
     *UPDATETIME
     */
    @JsonProperty(value = "UPDATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Timestamp updatetime;

    /*
     *RATE_LATESTMIR_CNBD
     */
    @JsonProperty(value = "RATE_LATESTMIR_CNBD")
    private  String rate_latestmir_cnbd;

    /*
     *SOURCEID
     */
    @JsonProperty(value = "SOURCEID")
    private  String sourceid;

    /*
     *TERM2
     */
    @JsonProperty(value = "TERM2")
    private  String term2;

    /*
     *CLAUSEABBR
     */
    @JsonProperty(value = "CLAUSEABBR")
    private  String clauseabbr;

    /*
     *AGENCY_LEADUNDERWRITER
     */
    @JsonProperty(value = "AGENCY_LEADUNDERWRITER")
    private  String agency_leadunderwriter;

    /*
     *HISTORYVERSIONDATE
     */
    @Id
    @JsonProperty(value = "HISTORYVERSIONDATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp historyversiondate;

    /*
     *ACTUALBENCHMARK
     */
    @JsonProperty(value = "ACTUALBENCHMARK")
    private  String actualbenchmark;

    /*
     *ABS_INDUSTRY1
     */
    @JsonProperty(value = "ABS_INDUSTRY1")
    private  String abs_industry1;

    /*
     *OUTSTANDINGBALANCE
     */
    @JsonProperty(value = "OUTSTANDINGBALANCE")
    private BigDecimal outstandingbalance;

    /*
     *ADVANCECREDIT_DESC
     */
    @JsonProperty(value = "ADVANCECREDIT_DESC")
    private  String advancecredit_desc;

    /*
     *ISSUEAMOUNT
     */
    @JsonProperty(value = "ISSUEAMOUNT")
    private  BigDecimal issueamount;

    /*
     *PTMYEAR
     */
    //@JsonProperty(value = "PTMYEAR")
    //private  BigDecimal ptmyear;
    @JsonProperty(value = "TERMNOTE1")
    private  String termnote1;

    /*
     *REMARKS
     */
    @JsonProperty(value = "REMARKS")
    private  String remarks;

    /*
     *INDUSTRY_GICS
     */
    @JsonProperty(value = "INDUSTRY_GICS")
    private  String industry_gics;

    /*
     *COUPONRATE2
     */
    @JsonProperty(value = "COUPONRATE2")
    private  BigDecimal couponrate2;

    /*
     *NXOPTIONDATE
     */
    @JsonProperty(value = "NXOPTIONDATE")
    private  String nxoptiondate;

    /*
     *CURRENTSTATE
     */
    @JsonProperty(value = "CURRENTSTATE")
    private  String currentstate;

    /*
     *ISSUE_ISSUEPRICE
     */
    @JsonProperty(value = "ISSUE_ISSUEPRICE")
    private  BigDecimal issue_issueprice;

    /*
     *RATE_CREDITRATINGAGENCY
     */
    @JsonProperty(value = "RATE_CREDITRATINGAGENCY")
    private  String rate_creditratingagency;

    /*
     *COMP_NAME
     */
    @JsonProperty(value = "COMP_NAME")
    private  String comp_name;

    /*
     *SDATE
     */
    @JsonProperty(value = "SDATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Timestamp sdate;

    /*
     *SEC_NAME
     */
    @JsonProperty(value = "SEC_NAME")
    private  String sec_name;

    /*
     *CREATETIME
     */
    @JsonProperty(value = "CREATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Timestamp createtime;

    /*
     *WINDCODE
     */
    @JsonProperty(value = "WINDCODE")
    private  String windcode;

    /*
     *BONDTYPE
     */
    @JsonProperty(value = "BONDTYPE")
    private  String bondtype;

    /*
     *NXCUPN2
     */
    @JsonProperty(value = "NXCUPN2")
    private  Integer nxcupn2;

    /*
     *ISSUE_ISSUEMETHOD
     */
    @JsonProperty(value = "ISSUE_ISSUEMETHOD")
    private  String issue_issuemethod;

    /*
     *COUPONDATETXT
     */
    @JsonProperty(value = "COUPONDATETXT")
    private  String coupondatetxt;

    /*
     *ISSUE_FIRSTISSUE
     */
    @JsonProperty(value = "ISSUE_FIRSTISSUE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Timestamp issue_firstissue;

    /*
     *CLAUSE
     */
    @JsonProperty(value = "CLAUSE")
    private  String clause;

    /*
     *ISSUER_RATING
     */
    @JsonProperty(value = "ISSUER_RATING")
    private  String issuer_rating;

    /*
     *MULTIMKTORNOT
     */
    @JsonProperty(value = "MULTIMKTORNOT")
    private  String multimktornot;

    /*
     *ID
     */
    @Id
    @JsonProperty(value = "ID")
    private  String id;

    /*
     *IPO_DATE
     */
    @JsonProperty(value = "IPO_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Timestamp ipo_date;

    /*
     *ISSUE_ANNOUNCEMENT
     */
    @JsonProperty(value = "ISSUE_ANNOUNCEMENT")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Timestamp issue_announcement;

    /*
     *CARRYDATE
     */
    @JsonProperty(value = "CARRYDATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Timestamp carrydate;

    /*
     *LASTDATE_CNBD
     */
    @JsonProperty(value = "LASTDATE_CNBD")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Timestamp lastdate_cnbd;

    /*
     *SOURCECONTRACTID
     */
    @JsonProperty(value = "SOURCECONTRACTID")
    private  String sourcecontractid;

    /*
     *NXCUPN
     */
    @JsonProperty(value = "NXCUPN")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Timestamp nxcupn;

    /*
     *CREDITRATING
     */
    @JsonProperty(value = "CREDITRATING")
    private  String creditrating;

    /*
     *MATURITYDATE
     */
    @JsonProperty(value = "MATURITYDATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Timestamp maturitydate;

    /*
     *REPO_LASTESTDATE
     */
    @JsonProperty(value = "REPO_LASTESTDATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Timestamp repo_lastestdate;

    /*
     *FULLNAME
     */
    @JsonProperty(value = "FULLNAME")
    private  String fullname;

    /*
     *ISSUE_LASTISSUE
     */
    @JsonProperty(value = "ISSUE_LASTISSUE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Timestamp issue_lastissue;

    /*
     *ISSUE_REGNUMBER
     */
    @JsonProperty(value = "ISSUE_REGNUMBER")
    private  String issue_regnumber;

    /*
     *EXCH_CITY
     */
    @JsonProperty(value = "EXCH_CITY")
    private  String exch_city;

    /*
     *SEC_STATUS
     */
    @JsonProperty(value = "SEC_STATUS")
    private  String sec_status;

    /*
     *INTERESTFREQUENCY
     */
    @JsonProperty(value = "INTERESTFREQUENCY")
    private  Integer interestfrequency;

    /*
     *AGENCY_GRNTTYPE
     */
    @JsonProperty(value = "AGENCY_GRNTTYPE")
    private  String agency_grnttype;

    /*
     *COUPONTXT
     */
    @JsonProperty(value = "COUPONTXT")
    private  String coupontxt;

    /*
     *NOTICENO
     */
    @JsonProperty(value = "NOTICENO")
    private  String noticeno;

    /*
     *主承销商(简称)
     */
    @JsonProperty(value = "AGENCY_LEADUNDERWRITERSN")
    private  String agency_leadunderwritersn;
    /*
     *发行人中文简称
     */
    @JsonProperty(value = "ISSUERSHORTENED")
    private  String issuershortened;
    /*
     *币种
     */
    @JsonProperty(value = "CURR")
    private  String curr;

}
