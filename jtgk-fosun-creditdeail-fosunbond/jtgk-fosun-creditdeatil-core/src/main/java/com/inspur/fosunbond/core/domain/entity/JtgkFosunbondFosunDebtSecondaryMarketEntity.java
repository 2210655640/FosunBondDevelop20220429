package com.inspur.fosunbond.core.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="FOSUNDEBTSECONDARYMARKET")
public class JtgkFosunbondFosunDebtSecondaryMarketEntity
{
    /*
     *YTM_B
     */
    private BigDecimal ytm_b;

    /*
     *UPDATETIME
     */
    private Date updatetime;

    /*
     *AMT
     */
    private  BigDecimal amt;

    /*
     *SDATE
     */
    private  Date sdate;

    /*
     *CLEANPRICE
     */
    private  BigDecimal cleanprice;

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
     *CREATETIME
     */
    private  Date createtime;

    /*
    *sourceid
     */
    private String sourceid;

    private  BigDecimal yield_cnbd;

    private  BigDecimal yield_cnbd2;

}
