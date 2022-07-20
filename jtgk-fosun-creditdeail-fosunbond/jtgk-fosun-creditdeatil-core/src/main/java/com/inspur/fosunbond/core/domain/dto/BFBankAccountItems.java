package com.inspur.fosunbond.core.domain.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liuhui
 * @title
 * @date 2021/12/3 10:28
 */
@Data
public class BFBankAccountItems {
    private String SUB_ID;
    private String SUB_CODE;
    private String SUB_NAME;
    private String CRNCY_CODE_SUB;
    private String CRNCY_NAME_SUB;
    private String ACCOUNT_TYPE_CODE;
    private String ACCOUNT_TYPE_NAME;
    private BigDecimal OVERDRAFT;
    private Integer OVERDRAFT_CRL_WAY;
    private BigDecimal SINGLE_PAY_LIMIT;
    private BigDecimal SINGLE_DAY_LIMIT;
    private BigDecimal POS_UPP_LIMIT;
    private BigDecimal POS_LOW_LIMIT;
    private Integer FREEZED_STATUS;
    private BigDecimal FREEZED_AMOUNT;
    private Date FREEZED_DATE;
    private Date UNFREEZED_DATE;

}
