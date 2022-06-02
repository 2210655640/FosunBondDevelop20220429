package com.inspur.fosunbond.core.domain.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="T_DEBT_SECONDARYMARKET")
public class JtgkFosunbondT_Debt_SecondaryMarketEntity
{
    /*
     *SDATE
     */
    private Date sdate;

    /*
     *CLEANPRICE
     */
    private BigDecimal cleanprice;

    /*
     *ID
     */
    @Id
    private  String id;

    /*
     *UPDATETIME
     */
    private  Date updatetime;

    /*
     *AMT
     */
    @Column(name ="amt")
    private  BigDecimal amt;

    /*
     *YTM_B
     */
    private  BigDecimal ytm_b;

    /*
     *WINDCODE
     */

    private  String windcode;

    /*
     *CREATETIME
     */
    private  Date createtime;

    private  BigDecimal yield_cnbd;

    private  BigDecimal yield_cnbd2;

}
