package com.inspur.fosunbond.core.domain.dto;

import lombok.Data;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import java.util.Date;

@Data
public class JtgkFosunBondIncomeBankAccountJHXDto {

    //户流水号
    private String ACCOUNTID;
    //账户申请流水号
    private String APPLYID;
    //账户代码
    private String ACCOUNTCODE;
    //单位编号
    private String CLTNO;
    //单位名称
    private String CLTNAME;
    //合作金融网点流水号
    private String ASSID;
    //所属金融机构
    private String BANKNO;
    //境内外
    private String ISABROAD;
    //地区编码（开户地址）
    private String REGNO;
    //地区名称
    private String REGNAME;
    //区域编码
    private String AREAID;
    //区域名称
    private String AREANAME;
    //开户日期
    private Date OPENACCOUNTDATE;
    //账号
    private String ACCOUNTNO;
    //户名
    private String ACCOUNTNAME;
    //币种
    private String CURRENCYNO;
    //账户用途
    private String UASGEID;
    //账户类别
    private String NATUREID;
    //账户性质
    private String CTID;
    //联网方式
    private String ASSOCIATEFLAG;
    //备注
    private String CNREMARK;
    //账户状态
    private String ACNTSTATE;
    //账户关系标识
    private String RelationID;
    //是否多币种账户
    private String ISCURRENCY;
    //销户日期
    private Date CANCELDATE;
    //销户备注
    private String CANCELREMARK;
    //销户原因
    private String CANCELREASON;
    //创建人
    private String CREATEUSER;
    //创建时间
    private String CREATE_TIME;
    //最后修改时间
    private String UPDATE_TIME;


}
