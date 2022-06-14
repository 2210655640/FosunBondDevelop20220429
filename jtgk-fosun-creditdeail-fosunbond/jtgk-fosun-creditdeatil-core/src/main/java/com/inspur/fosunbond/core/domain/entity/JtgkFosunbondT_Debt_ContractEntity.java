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
//@Table(name="T_DEBT_CONTRACT")
@Table(name="v_debt_contract")//取视图数据
public class JtgkFosunbondT_Debt_ContractEntity
{
    /*
     *COUPONDATETXT
     */
    private  String coupondatetxt;

    /*
     *发行时主体评级
     */
    private  String issuer_rating;

    /*
     *REPO_LASTESTDATE
     */
    private  Date repo_lastestdate;

    /*
     *距下一付息日（天）
     */
    private  Integer nxcupn2;

    /*
     *债券余额
     */
    private  BigDecimal outstandingbalance;

    /*
     *AGENCY_GRNTTYPE
     */
    private  String agency_grnttype;

    /*
     *发行时债项评级
     */
    private  String creditrating;

    /*
     *到期日期
     */
    private  Date maturitydate;

    /*
     *发行方式
     */
    private  String issue_issuemethod;

    /*
     *修改时间
     */

    @Column(name = "updatetime")
    private Date updatetime;

    /*
     *Wind行业分类（明细）
     */
    private  String industry_gics;

    /*
     *债券代码
     */
    //@Id
    private  String windcode;

    /*
     *统计日期
     */
    private  Date sdate;

    /*
     *创建时间
     */
    private  Date createtime;

    /*
     *回售日
     */
    private  Date repurchasedate;

    /*
     *担保人
     */
    private  String agency_guarantor;

    /*
     *发行起始日
     */
    private  Date issue_firstissue;

    /*
     *中债隐含评级
     */
    private  String rate_latestmir_cnbd;

    /*
     *发行人委托评级机构
     */
    private  String rate_creditratingagency;

    /*
     *是否跨市场
     */
    private  String multimktornot;

    /*
     *起息日期
     */
    private  Date carrydate;

    /*
     *发行公告日
     */
    private  Date issue_announcement;

    /*
     *债券简称
     */
    private  String sec_name;

    /*
     *上市日期
     */
    private  Date ipo_date;

    /*
     *主键
     */
    @Id
    private  String id;

    /*
     *下一行权日
     */
    private  String nxoptiondate;

    /*
     *交易市场
     */
    private  String exch_city;

    /*
     *发行截止日
     */
    private  Date issue_lastissue;

    /*
     *特殊条款（缩写）
     */
    private  String clauseabbr;

    /*
     *息票品种
     */
    private  String coupon;

    /*
     *下一付息日
     */
    private  Date nxcupn;

    /*
     *债券类型
     */
    private  String bondtype;

    /*
     *LASTDATE_CNBD
     */
    private  Date lastdate_cnbd;

    /*
     *特殊条款
     */
    private  String clause;

    /*
     *主承销商
     */
    private  String agency_leadunderwriter;

    /*
     *发行价格（元）
     */
    private  BigDecimal issue_issueprice;

    /*
     *当前状态
     */
    //private  String currentstate;

    /*
     *LAST_TRADE_DAY
     */
    //private  Date last_trade_day;

    /*
     *债券全称
     */
    private  String fullname;

    /*
     *债券期限（文字）
     */
    private  String term2;

    /*
     *COMP_NAME
     */
    private  String comp_name;

    /*
     *剩余期限（年）
     */
    //private  BigDecimal ptmyear;
    private String termnote1;

    /*
     *票面利率（当期）
     */
    private  BigDecimal couponrate2;

    /*
     *注册文号
     */
    private  String issue_regnumber;

    /*
     *利率说明
     */
    private  String coupontxt;

    /*
     *增信情况
     */

    //private  String advancecredit_desc;

    /*
     *付息频率
     */
    private  Integer interestfrequency;

    /*
     *计息基准
     */
    private  String actualbenchmark;

    /*
     *发行人企业性质
     */
    private  String abs_industry1;

    /*
     *SEC_STATUS
     */
    private  String sec_status;

    /*
     *发行规模
     */
    private  BigDecimal issueamount;
    /*
     *主承销商(简称)
     */
    private  String agency_leadunderwritersn;
    /*
     *发行人中文简称
     */
    private  String issuershortened;
    /*
    *币种
     */
    private  String curr;
    /*
     *统一社会信用代码
     */
    private  String registernumber;
}

