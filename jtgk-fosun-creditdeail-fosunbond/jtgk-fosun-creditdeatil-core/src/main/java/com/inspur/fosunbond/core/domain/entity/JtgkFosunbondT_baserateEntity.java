package com.inspur.fosunbond.core.domain.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
@Data
@Entity
@Table(name="T_BASERATE")
public class JtgkFosunbondT_baserateEntity
{
    /*
     *VALUE
     */
    private  BigDecimal value;

    /*
     *ID
     */
    @Id
    private  String id;

    /*
     *NAME
     */
    private  String name;

    /*
     *SDATE
     */
    private  Date sdate;

    /*
     *UPDATETIME
     */
    private  Date updatetime;

    /*
     *CREATETIME
     */
    private  Date createtime;

    /*
     *CODE
     */
    private  String code;

}

