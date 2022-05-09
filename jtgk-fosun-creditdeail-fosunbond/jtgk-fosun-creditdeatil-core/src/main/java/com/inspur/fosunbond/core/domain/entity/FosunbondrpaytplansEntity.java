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
     *ID
     */
    @Id
    private  String id;

    /*
     *计划应付利息
     */
    private  BigDecimal accrued_interest_per_cny100par;

    /*
     *实际已还利息
     */
    private  BigDecimal hadrpayinterest;

    /*
     *创建人
     */
    private  String creator;

    /*
     *计划总额
     */
    private  BigDecimal cash_flows_per_cny100par;

    /*
     *现金流类型
     */
    private  String cf_type;

    /*
     *实际已还总额
     */
    private  BigDecimal hadrpayttotalamt;

    /*
     *修改人
     */
    private  String lastmodifier;

    /*
     *实际已还本金
     */
    private  BigDecimal hadrpayprincipal;

    /*
     *修改时间
     */
    private  Date lastmodifiedtime;

    /*
     *状态
     */
    private  String statsus;

    /*
     *票面利率
     */
    private  BigDecimal coupon_rate;

    /*
     *债券代码
     */
    private  String windcode;

    /*
     *创建时间
     */
    private  Date createdtime;

    /*
     *计划应付本金
     */
    private  BigDecimal accrued_principal_per_cny100par;

    /*
     *现金流发生日
     */
    private  Date cash_flows_date;

}

