package com.inspur.fosunbond.core.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Data
@Entity
@Table(name="FOSUNBONDRPAYTPLANS")
public class FosunbondrpaytplansEntity
{
    /*
     *修改人
     */
    private  String lastmodifier;

    /*
     *现金流发生日
     */
    private  Date cash_flows_date;

    /*
     *计划应付本金
     */
    private  BigDecimal accrued_principal_per_cny100par;

    /*
     *创建时间
     */
    private  Date createdtime;

    /*
     *来源系统
     */
    private  String sourcesystem;

    /*
     *债券代码
     */
    private  String windcode;

    /*
     *票面利率
     */
    private  BigDecimal coupon_rate;

    /*
     *状态
     */
    private  String statsus;

    /*
     *资金系统计划总额
     */
    private  BigDecimal zjcash_flows_per_cny100par;

    /*
     *修改时间
     */
    private  Date lastmodifiedtime;

    /*
     *实际已还本金
     */
    private  BigDecimal hadrpayprincipal;

    /*
     *ID
     */
    private  String id;

    /*
     *实际已还总额
     */
    private  BigDecimal hadrpayttotalamt;

    /*
     *资金系统计划应付本金
     */
    private  BigDecimal zjaccrued_principal_per_cny100par;

    /*
     *现金流类型
     */
    private  String cf_type;

    /*
     *计划总额
     */
    private  BigDecimal cash_flows_per_cny100par;

    /*
     *资金系统计划应付利息
     */
    private  BigDecimal zjaccrued_interest_per_cny100par;

    /*
     *创建人
     */
    private  String creator;

    /*
     *删除区分
     */
    private  String delflag;

    /*
     *资金系统现金流发生日
     */
    private  Date zjcash_flows_date;

    /*
     *实际已还利息
     */
    private  BigDecimal hadrpayinterest;

    /*
     *计划应付利息
     */
    private  BigDecimal accrued_interest_per_cny100par;

}
