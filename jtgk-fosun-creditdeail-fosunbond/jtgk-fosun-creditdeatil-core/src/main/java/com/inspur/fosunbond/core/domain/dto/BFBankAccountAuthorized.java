package com.inspur.fosunbond.core.domain.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author liuhui
 * @title
 * @date 2021/12/3 10:30
 */

@Data
public class BFBankAccountAuthorized {
    private String AUTH_ID;
    private String AUTH_UNIT_ID;
    private String AUTH_UNIT_NO;
    private String AUTH_UNIT_NAME;
    private Integer AUTH_STATUS;
}
