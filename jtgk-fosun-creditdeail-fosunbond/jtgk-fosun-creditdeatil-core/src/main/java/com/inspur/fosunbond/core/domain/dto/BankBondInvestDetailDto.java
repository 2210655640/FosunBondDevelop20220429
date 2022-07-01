package com.inspur.fosunbond.core.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BankBondInvestDetailDto {
    private String secname;
    private String accountno;
    private String accountname;
    private String shortname;
    private String clasicname;
    private String investname;
    private BigDecimal amt;
    private BigDecimal DEBTOCCUPY;
    private BigDecimal UNDERWRITEOCCUPY;
    private String id;
    private String code;
    private String remark;
    //private String comp_name;
    //private Date zydate;

}
