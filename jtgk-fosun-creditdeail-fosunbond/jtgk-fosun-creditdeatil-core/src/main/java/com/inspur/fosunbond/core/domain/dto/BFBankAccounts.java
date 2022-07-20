package com.inspur.fosunbond.core.domain.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author liuhui
 * @title
 * @date 2021/12/3 10:15
 */
@Data
public class BFBankAccounts {
    private Date ACCOUNT_DATE;//开户日期
    private String ACCOUNT_ID;//账户ID
    private String ACCOUNT_NAME;//户名
    private String ACCOUNT_NAME_CHT;//账户名称-繁体
    private String ACCOUNT_NAME_EN;//账户名称-英文
    private String ACCOUNT_NO;//账号
    private String ACCOUNT_SHORTNAME;//账户简称
    private String ACCOUNTING_UNIT_NO;//核算单位编号
    private String ACCOUNTPROPERTY_CODE;//账户性质编号
    private String ACCOUNTPROPERTY_NAME;//账户性质名称
    private Integer ACCOUNTSTATUS;//账户状态
    private String ACCOUNTUSE_CODE;//账户用途
    private String ACCOUNTUSE_NAME;//账户用途名称
    private String BALCHECK_UNIT_NO;//对账单位编号
    private String BANK_CNAPS_CODE;//开户行编号
    private String BANK_CNAPS_NAME;//开户行名称
    private String CITY_CODE;//城市编码
    private String CITY_NAME;//城市名称
    private String CLT_NAME;//开户单位名称
    private String CLTID;//开户单位ID
    private String CLTNO;//开户单位编号
    private String CORP_UNIT_NO;//法人单位编号
    private String COUNTRY_NAME;//国家名称
    private String COUNTRY_TWOCHARCODE;//国家编码
    private String CRNCY_CODE;//币种编码
    private String CRNCY_NAME;//币种名称
    private Integer DEADLINEBYMONTH;//使用期限(月)
    private Integer Direct_TYPE;//直联开通状态
    private Date DUEDATE;//到期日期
    private Integer EBACC;//是否电票户
    private String EBBANK_CODE;//电票对应接口
    private String EBBANK_NAME;//电票联行名称
    private String EBBANK_NO;//电票联行号
    private String EBCITY_CODE;//电票城市
    private Integer EBDirect;//是否开通电票直联
    private String EBPROVINCE_CODE;//电票省
    private String EBSETTLEMENT_ACCNO;//电票结算户账号
    private String IFZEROBALANCE;//是否零余额账户
    private Integer INCOMEOREXPENSE_CODE;//收支属性编号
    private Integer INOROUT;//账户种类
    private String ONLINEBANK_BNAME;//联行名称
    private String ONLINEBANK_CODE;//直联银行编号
    private String ONLINEBANK_NAME;//直联银行名称
    private String ONLINEBANK_NO;//联行号
    private String OPENINGEXPLAIN;//开户说明
    private String PROVINCE_CODE;//省编码
    private String PROVINCE_NAME;//省名称
    private Date START_DATE;//启用日期
    private String START_USERNAME;//启用人名称
    private Integer ZEROBALANCE;//是否零余额户
    private Date BANKCANCELLATION_DATE;//银行销户日期
    private String BANKCANCELLER; //银行经办人
    private Date CLOSED_DATE;//销户操作日期
    private String CLOSED_USERNAME;//销户操作人名称



    private List<BFBankAccountItems> bfBankAccountItemsList;

    private List<BFBankAccountAuthorized> bfBankAccountAuthorizedList;

}