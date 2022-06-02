package com.inspur.fosunbond.core.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="T_DEBT_VALUATION")
public class JtgkFosunbondT_Debt_ValuationEntity
{
    /*
     *ID
     */
    @Id
    private  String id;

    /*
     *WINDCODE
     */

    private  String windcode;

    /*
     *YIELD_CNBD2
     */
    private BigDecimal yield_cnbd2;

    /*
     *YIELD_CNBD
     */
    private  BigDecimal yield_cnbd;

    /*
     *CREATETIME
     */
    private Date createtime;

    /*
     *SDATE
     */
    private  Date sdate;

    /*
     *NET_CNBD2
     */
    private  BigDecimal net_cnbd2;

    /*
     *UPDATETIME
     */
    private  Date updatetime;

    /*
     *NET_CNBD
     */
    private  BigDecimal net_cnbd;

}

