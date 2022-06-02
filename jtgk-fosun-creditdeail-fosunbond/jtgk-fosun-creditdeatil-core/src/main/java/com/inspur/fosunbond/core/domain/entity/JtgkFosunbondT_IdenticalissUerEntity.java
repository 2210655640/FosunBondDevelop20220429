package com.inspur.fosunbond.core.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="T_IDENTICALISSUER")
public class JtgkFosunbondT_IdenticalissUerEntity
{
    /*
     *修改时间
     */
    private Date updatetime;

    /*
     *BONDRATING
     */
    private  String bondrating;

    /*
     *债券类型
     */
    private  String bondtype;

    /*
     *REMAININGMATURITY
     */
    private  String remainingmaturity;

    /*
     *创建时间
     */
    private  Date createtime;

    /*
     *存续状态
     */
    //private  String sec_status;

    private  String currentstate;
    /*
     *发行日期
     */
    private  Date issuedate;

    /*
     *CORPORATERATING
     */
    private  String corporaterating;

    /*
     *主键
     */
    @Id
    private  String id;

    /*
     *CURRENTCOUPONRATE
     */
    private BigDecimal currentcouponrate;

    /*
     *债券代码
     */
    private  String windcode;

    /*
     *债券余额
     */
    private  BigDecimal outstandingbalance;

    /*
     *债券简称
     */
    private  String secname;

}
