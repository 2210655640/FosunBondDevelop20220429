package com.inspur.fosunbond.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
@Data
@Entity
@Table(name="FOSUNBONDRPAYTPLANS")
public class FosunbondrpaytplansEntity
{
    /*
     *修改人
     */
    @JsonProperty(value="LASTMODIFIER")
    private  String lastmodifier;

    /*
     *现金流发生日
     */
    @JsonProperty(value = "CASH_FLOWS_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name="CASH_FLOWS_DATE")
    private Date cashflowsdate;

    /*
     *计划应付本金
     */
    @JsonProperty(value = "ACCRUED_PRINCIPAL_PER_CNY100PAR")
    private  BigDecimal accrued_principal_per_cny100par;

    /*
     *创建时间
     */
    @JsonProperty(value = "CREATEDTIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Date createdtime;

    /*
     *来源系统
     */
    @JsonProperty(value = "SOURCESYSTEM")
    private  String sourcesystem;

    /*
     *债券代码
     */
    @JsonProperty(value = "WINDCODE")
    private  String windcode;

    /*
     *票面利率
     */
    @JsonProperty(value = "COUPON_RATE")
    private  BigDecimal coupon_rate;

    /*
     *状态
     */
    @JsonProperty(value = "STATSUS")
    private  String statsus;

    /*
     *资金系统计划总额
     */
    @JsonProperty(value = "ZJCASH_FLOWS_PER_CNY100PAR")
    private  BigDecimal zjcash_flows_per_cny100par;

    /*
     *修改时间
     */
    @JsonProperty(value = "LASTMODIFIEDTIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private  Date lastmodifiedtime;

    /*
     *实际已还本金
     */
    @JsonProperty(value = "HADRPAYPRINCIPAL")
    private  BigDecimal hadrpayprincipal;

    /*
     *ID
     */
    @Id
    @JsonProperty(value = "ID")
    private  String id;

    /*
     *实际已还总额
     */
    @JsonProperty(value = "HADRPAYTTOTALAMT")
    private  BigDecimal hadrpayttotalamt;

    /*
     *资金系统计划应付本金
     */
    @JsonProperty(value = "ZJACCRUED_PRINCIPAL_PER_CNY100PAR")
    private  BigDecimal zjaccrued_principal_per_cny100par;

    /*
     *现金流类型
     */
    @JsonProperty(value = "CF_TYPE")
    private  String cf_type;

    /*
     *计划总额
     */
    @JsonProperty(value = "CASH_FLOWS_PER_CNY100PAR")
    private  BigDecimal cash_flows_per_cny100par;

    /*
     *资金系统计划应付利息
     */
    @JsonProperty(value = "ZJACCRUED_INTEREST_PER_CNY100PAR")
    private  BigDecimal zjaccrued_interest_per_cny100par;

    /*
     *创建人
     */
    @JsonProperty(value = "CREATOR")
    private  String creator;

    /*
     *删除区分
     */
    @JsonProperty(value = "DELFLAG")
    private  String delflag;

    /*
     *资金系统现金流发生日
     */
    @JsonProperty(value = "ZJCASH_FLOWS_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name="ZJCASH_FLOWS_DATE")
    private  Date zjcashflowsdate;

    /*
     *实际已还利息
     */
    @JsonProperty(value = "HADRPAYINTEREST")
    private  BigDecimal hadrpayinterest;

    /*
     *计划应付利息
     */
    @JsonProperty(value = "ACCRUED_INTEREST_PER_CNY100PAR")
    private  BigDecimal accrued_interest_per_cny100par;

    /*
     *源数据ID
     */
    @JsonProperty(value = "SOURCEID")
    private  String sourceid;

    /*
     *初始或执行汇率
     */
    @JsonProperty(value = "ORIGINALOREXERATE")
    private  String originalorexerate;

    /*
     *币种
     */
    @JsonProperty(value = "CURR")
    private  String curr;

}
