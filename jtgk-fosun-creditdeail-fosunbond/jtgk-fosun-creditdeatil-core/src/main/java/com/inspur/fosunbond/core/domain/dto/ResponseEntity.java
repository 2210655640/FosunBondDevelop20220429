package com.inspur.fosunbond.core.domain.dto;

import lombok.Data;

@Data
public class ResponseEntity {
    /**
     * 结果标识：-1失败，0不处理，1成功
     */
    private Integer RET_CODE;
    /**
     * 提示信息
     */
    private String RET_MSG;
    /**
     * 返回体
     */
    private Object RET_BODY;
}
