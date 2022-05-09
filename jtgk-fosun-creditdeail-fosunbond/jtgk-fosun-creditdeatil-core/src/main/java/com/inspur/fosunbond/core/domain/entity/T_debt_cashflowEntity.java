package com.inspur.fosunbond.core.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="T_DEBT_CASHFLOW")
public class T_debt_cashflowEntity
{
    /*
     *更新时间
     */
    private Date updatetime;

    /*
     *计划总额
     */
    private BigDecimal cash_flows_per_cny100par;

    /*
     *现金流类型
     */
    private  String cf_type;

    /*
     *计划应付本金
     */
    private  BigDecimal accrued_principal_per_cny100par;

    /*
     *计划应付利息
     */
    private  BigDecimal accrued_interest_per_cny100par;

    /*
     *现金流发生日
     */
    private  Date cash_flows_date;

    /*
     *主键
     */
    @Id
    private  String id;

    /*
     *创建时间
     */
    private  Date createtime;

    /*
     *票面利率
     */
    private  BigDecimal coupon_rate;

    /*
     *债券代码
     */
    private  String windcode;

}

