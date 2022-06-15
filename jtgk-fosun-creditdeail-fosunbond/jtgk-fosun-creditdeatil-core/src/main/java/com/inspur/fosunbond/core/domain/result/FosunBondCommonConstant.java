package com.inspur.fosunbond.core.domain.result;

/**
 * @Description：响应信息代码
 * @Author：wangshunan
 * @Date：2020.04.21
 */
public interface FosunBondCommonConstant {

    /**
     * {@code 500 Server Error} (HTTP/1.0 - RFC 1945)
     */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /**
     * {@code 200 OK} (HTTP/1.0 - RFC 1945)
     */
    public static final Integer SC_OK_200 = 200;

    public static final Integer SC_IGO_VALIDFAILED = 403;

    /**
     * 访问权限认证未通过 401
     */
    public static final Integer SC_IGO_NO_AUTHZ = 401;

    /**
     * 系统日志类型： 登录
     */
    public static final int LOG_TYPE_1 = 1;

    /**
     * 系统日志类型： 操作
     */
    public static final int LOG_TYPE_2 = 2;

}
