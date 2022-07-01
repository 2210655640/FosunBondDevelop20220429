package com.inspur.fosunbond.core.domain.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@Entity
@Table(name="FOSUNBONDREGISNOATTACH")
public class JtgkFosunBondFosunbondregisnoattachEntity
{
    /*
     *CREATEDTIME
     */
    private Date createdtime;

    /*
     *注册文号
     */
    @Id
    private  String id;

    /*
     *LASTMODIFIER
     */
    private  String lastmodifier;

    /*
     *CREATOR
     */
    private  String creator;

    /*
     *附件关联
     */
    private  String attachlen;

    /*
     *LASTMODIFIEDTIME
     */
    private  Date lastmodifiedtime;

}

