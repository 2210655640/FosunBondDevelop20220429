package com.inspur.fosunbond.core.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="FOSUNDEBTVALUATION")
public class FosunDebtValuation1Entity
{
    /*
     *NET_CNBD
     */
    private BigDecimal net_cnbd;

    /*
     *ID
     */
    @Id
    private  String id;

    /*
     *UPDATETIME
     */
    private Date updatetime;

    /*
     *CREATETIME
     */
    private  Date createtime;

    /*
     *NET_CNBD2
     */
    private  BigDecimal net_cnbd2;

    /*
     *WINDCODE
     */
    private  String windcode;

    /*
     *SDATE
     */
    private  Date sdate;

    /*
     *YIELD_CNBD2
     */
    private  BigDecimal yield_cnbd2;

    /*
     *YIELD_CNBD
     */
    private  BigDecimal yield_cnbd;

}
