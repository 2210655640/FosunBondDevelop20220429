package com.inspur.fosunbond.core.domain.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
@Entity
@Table(name="BFBLINTERESTRATEVER")
public class JtgkFosunBondBfblinterestrateverEntity
{
    /*
     *OPERATORNAME
     */
    private  String operatorname;

    /*
     *VERSIONNO
     */
    private  int versionno;

    /*
     *OPERATEDATE
     */
    private  Date operatedate;

    /*
     *ID
     */
    @Id
    private  String id;

    /*
     *TIMESTAMP_LASTCHANGEDON
     */
    private  Date timestamp_lastchangedon;

    /*
     *TIMESTAMP_CREATEDBY
     */
    private  String timestamp_createdby;

    /*
     *TIMESTAMP_LASTCHANGEDBY
     */
    private  String timestamp_lastchangedby;

    /*
     *OPERATOR
     */
    private  String operator;

    /*
     *ANNUALIR
     */
    private  BigDecimal annualir;

    /*
     *PARENTID
     */
    private  String parentid;

    /*
     *STARTDATE
     */
    private  Date startdate;

    /*
     *REMARK
     */
    private  String remark;

    /*
     *TIMESTAMP_CREATEDON
     */
    private  Date timestamp_createdon;

    /*
     *ENDDATE
     */
    private  Date enddate;

}
