package com.inspur.fosunbond.core.domain.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
@Data
@Entity
@Table(name="BFBLINTERESTRATE")
public class JtgkFosunBondBfblinterestrateEntity
{
    /*
     *TIMESTAMP_CREATEDON
     */
    private  Date timestamp_createdon;

    /*
     *ANNUALIR
     */
    private  BigDecimal annualir;

    /*
     *RATESTATUS
     */
    private  int ratestatus;

    /*
     *TIMESTAMP_LASTCHANGEDBY
     */
    private  String timestamp_lastchangedby;

    /*
     *TIMESTAMP_LASTCHANGEDON
     */
    private  Date timestamp_lastchangedon;

    /*
     *VERSION
     */
    private  Date version;

    /*
     *REMARK
     */
    private  String remark;

    /*
     *DELFLAG
     */
    private  String delflag;

    /*
     *IRDATE
     */
    private  Date irdate;

    /*
     *ID
     */
    @Id
    private  String id;

    /*
     *TIMESTAMP_CREATEDBY
     */
    private  String timestamp_createdby;

    /*
     *IRTERM
     */
    private  String irterm;

    /*
     *OPERATEDATE
     */
    private  Date operatedate;

    /*
     *OPERATORNAME
     */
    private  String operatorname;

    /*
     *OPERATOR
     */
    private  String operator;

    /*
     *IRTYPE
     */
    private  String irtype;

    /*
     *ANNUALDAYS
     */
    private  int annualdays;

}

