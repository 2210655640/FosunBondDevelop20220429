package com.inspur.fosunbond.core.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class PrimaryKey implements Serializable {
    private String id;
    private Timestamp historyversiondate;
}
