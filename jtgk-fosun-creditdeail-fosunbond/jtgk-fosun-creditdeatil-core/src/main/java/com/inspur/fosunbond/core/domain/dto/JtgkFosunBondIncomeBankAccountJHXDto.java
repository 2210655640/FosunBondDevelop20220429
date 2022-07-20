package com.inspur.fosunbond.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import java.util.Date;

@Data
public class JtgkFosunBondIncomeBankAccountJHXDto {

    //币种
    @JsonProperty(value = "CURRENCYNO")
    private String CURRENCYNO;
    //账号
    @JsonProperty(value = "ACCOUNTNO")
    private String ACCOUNTNO;
    //区域名称
    @JsonProperty(value = "AREANAME")
    private String AREANAME;
    //单位名称
    @JsonProperty(value = "CLTNAME")
    private String CLTNAME;
    //销户日期
    @JsonProperty(value = "CANCELDATE")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date CANCELDATE;
    //创建时间
    @JsonProperty(value = "CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date CREATE_TIME;
    //账户申请流水号
    @JsonProperty(value = "APPLYID")
    private String APPLYID;
    //账户代码
    @JsonProperty(value = "ACCOUNTCODE")
    private String ACCOUNTCODE;
    @JsonProperty(value = "USAGEID")
    private String USAGEID;
    //联网方式
    @JsonProperty(value = "ASSOCIATEFLAG")
    private String ASSOCIATEFLAG;
    //所属金融机构
    @JsonProperty(value = "BANKNO")
    private String BANKNO;
    //创建人
    @JsonProperty(value = "CREATEUSER")
    private String CREATEUSER;
    //账户性质
    @JsonProperty(value = "CTID")
    private String CTID;
    //开户日期
    @JsonProperty(value = "OPENACCOUNTDATE")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date OPENACCOUNTDATE;
    //地区编码（开户地址）
    @JsonProperty(value = "REGNO")
    private String REGNO;
    //单位编号
    @JsonProperty(value = "CLTNO")
    private String CLTNO;
    //销户备注
    @JsonProperty(value = "CANCELREMARK")
    private String CANCELREMARK;
    //账户类别
    @JsonProperty(value = "NATUREID")
    private String NATUREID;
    //地区名称
    @JsonProperty(value = "REGNAME")
    private String REGNAME;
    //是否多币种账户
    @JsonProperty(value = "ISCURRENCY")
    private String ISCURRENCY;
    //区域编码
    @JsonProperty(value = "AREAID")
    private String AREAID;
    //账户状态
    @JsonProperty(value = "ACNTSTATE")
    private String ACNTSTATE;
    //销户原因
    @JsonProperty(value = "CANCELREASON")
    private String CANCELREASON;
    //备注
    @JsonProperty(value = "CNREMARK")
    private String CNREMARK;
    //最后修改时间
    @JsonProperty(value = "UPDATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date UPDATE_TIME;
    //户名
    @JsonProperty(value = "ACCOUNTNAME")
    private String ACCOUNTNAME;
    //户流水号
    @JsonProperty(value = "ACCOUNTID")
    private String ACCOUNTID;
    @JsonProperty(value = "CHANNEL")
    private String CHANNEL;
    //合作金融网点流水号
    @JsonProperty(value = "ASSID")
    private String ASSID;
    //账户关系标识
    @JsonProperty(value = "RelationID")
    private String RelationID;
    //境内外
    @JsonProperty(value = "ISABROAD")
    private String ISABROAD;








    //账户用途
    //private String UASGEID;














}
