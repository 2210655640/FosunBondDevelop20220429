package com.inspur.fosunbond.core.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="FOSUNIDENTICALISSUER")
public class FosunIdenticalissUer1Entity
{
    /*
     *ID
     */
    @Id
    private  String id;

    /*
     *BONDTYPE
     */
    private  String bondtype;

    /*
     *OUTSTANDINGBALANCE
     */
    private BigDecimal outstandingbalance;

    /*
     *BONDRATING
     */
    private  String bondrating;

    /*
     *CREATETIME
     */
    private Date createtime;

    /*
     *WINDCODE
     */
    private  String windcode;

    /*
     *CORPORATERATING
     */
    private  String corporaterating;

    /*
     *REMAININGMATURITY
     */
    private  String remainingmaturity;

    /*
     *sec_status
     */
    private  String sec_status;

    /*
     *CURRENTCOUPONRATE
     */
    private  BigDecimal currentcouponrate;

    /*
     *ISSUEDATE
     */
    private  Date issuedate;

    /*
     *UPDATETIME
     */
    private  Date updatetime;

    /*
     *SECNAME
     */
    private  String secname;

}

