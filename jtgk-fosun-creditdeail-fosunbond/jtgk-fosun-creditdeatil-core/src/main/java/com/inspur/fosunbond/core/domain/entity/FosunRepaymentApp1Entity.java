package com.inspur.fosunbond.core.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name="FOSUNREPAYMENTAPP")
public class FosunRepaymentApp1Entity
{
    /*
     *汇总
     */
    private  String summary;

    /*
     *主键
     */
    @Id
    private  String id;

    /*
     *创建时间
     */
    private Date createdtime;

    /*
     *还款申请编号
     */
    private  String code;

    /*
     *还息金额
     */
    private BigDecimal interestamount;

    /*
     *状态
     */
    private  String status;

    /*
     *创建人
     */
    private  String creator;

    /*
     *还本金额
     */
    private  BigDecimal principalamount;

    /*
     *预计还款日期
     */
    private  Date repaydate;

    /*
     *最后修改人
     */
    private  String lastmodifier;

    /*
     *最后修改时间
     */
    private  Date lastmodifiedtime;

    /*
     *金额合计
     */
    private  BigDecimal sumamount;

}


