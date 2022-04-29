package com.inspur.fosunbond.core.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="FOSUNREPAYMENTAPPSON")
public class FosunRepaymentAppSon1Entity
{
    /*
     *最后修改人
     */
    private  String lastmodifier;

    /*
     *主表ID
     */
    private  String mainid;

    /*
     *还息
     */
    private BigDecimal repayinterest;

    /*
     *汇总
     */
    private  String summary;

    /*
     *提款编号
     */
    private  String withdrawalnum;

    /*
     *最后修改时间
     */
    private Date lastmodifiedtime;

    /*
     *主键
     */
    @Id
    private  String id;

    /*
     *借款人
     */
    private  String borrower;

    /*
     *合同编号
     */
    private  String contractnum;

    /*
     *创建时间
     */
    private  Date createdtime;

    /*
     *还本
     */
    private  BigDecimal repayprincipal;

    /*
     *创建人
     */
    private  String creator;

    /*
     *金融机构
     */
    private  String financialinstitution;

    /*
     *计划表ID
     */
    private  String planbid;

    /*
     *状态
     */
    private  String status;

    /*
     *到期日期
     */
    private  Date duedate;

}