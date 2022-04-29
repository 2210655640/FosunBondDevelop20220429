package com.inspur.fosunbond.core.domain.result;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * @param <T>
 * @Description：响应信息
 * @Author：songxinqiang
 * @Date：2022.04.22
 */
@Data
@Slf4j
public class Result<T> implements Serializable {
//    private LangService langService = LangService.getInstance();
//    private String langFlag = "c8a30682-ef8b-c1e6-5cee-b6958906e167";

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    private boolean success = true;

    /**
     * 返回处理消息
     */
    private String message = "操作成功";

    /**
     * 返回代码
     */
    private Integer code = 0;

    /**
     * 返回数据对象 data
     */
    private T result;

    /**
     * 时间戳
     */
    private long Timestamp = System.currentTimeMillis();

    public Result() {
        //this.message = Optional.ofNullable(langService.getLanguageString(langFlag, "Operate_Success")).orElse("操作成功！");
        this.message ="操作成功！";
    }

    /**
     * 操作失败，无返回数据，自定义返回消息
     */
    public  Result<T> error500(String message) {
        String i18nMsg;
        try {
            //i18nMsg = Optional.ofNullable(langService.getLanguageString(langFlag, message)).orElse(message);
            i18nMsg = message;
        }catch (Exception e){
            i18nMsg=message;
        }
        this.message = i18nMsg;
        this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
        this.success = false;
        return this;
    }

    /**
     * 操作失败，无返回数据，自定义返回消息
     */
    public Result<T> error500(String message, Boolean isLang) {
        //this.message = isLang?Optional.ofNullable(langService.getLanguageString(langFlag, message)).orElse(message):message;
        this.message = message;
        this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
        this.success = false;
        return this;
    }

    /**
     * 操作失败，无返回数据，默认返回消息
     */
    public Result<T> error500() {
        //this.message = Optional.ofNullable(langService.getLanguageString(langFlag, "Operate_Fail")).orElse("操作失败！");
        this.message ="操作失败！";
        this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
        this.success = false;
        return this;
    }

    /**
     * 操作失败，无返回数据，默认返回消息
     */
    public Result<T> error() {
        //this.message = Optional.ofNullable(langService.getLanguageString(langFlag, "Operate_Fail")).orElse("操作失败！");
        this.message ="操作失败！";
        this.code = CommonConstant.SC_INTERNAL_SERVER_ERROR_500;
        this.success = false;
        return this;
    }

    /**
     * 401未授权，无返回数据，默认返回消息
     */
    public Result<T> error401() {
        //this.message = Optional.ofNullable(langService.getLanguageString(langFlag, "Operate_NoAuth")).orElse("您当前没有操作该资源的权限！");
        this.message ="您当前没有操作该资源的权限！";
        this.code = CommonConstant.SC_IGO_NO_AUTHZ;
        this.success = false;
        return this;
    }

    /**
     * 403 输入参数校验失败。
     */
    public Result<T> error403(String message) {
        //this.message = Optional.ofNullable(langService.getLanguageString(langFlag, "PutParamFail")).orElse("输入参数校验失败！\n") + Optional.ofNullable(langService.getLanguageString(langFlag, message)).orElse(message);
        this.message ="输入参数校验失败！\n"+message;
        this.code = CommonConstant.SC_IGO_VALIDFAILED;
        this.success = false;
        return this;
    }

    /**
     * 操作成功，无返回数据，默认返回消息
     */
    public Result<T> success() {
        this.result = null;
        this.code = CommonConstant.SC_OK_200;
        this.success = true;
        return this;
    }

    /**
     * 操作成功，无返回数据，自定义返回消息
     */
    public Result<T> success(String message) {
        this.result = null;
        //this.message = Optional.ofNullable(langService.getLanguageString(langFlag, message)).orElse(message);
        this.message =message;
        this.code = CommonConstant.SC_OK_200;
        this.success = true;
        return this;
    }

    /**
     * 操作成功，存在返回数据，自定义返回消息
     */
    public Result<T> success(String message, T data) {
        //this.message = Optional.ofNullable(langService.getLanguageString(langFlag, message)).orElse(message);
        this.message =message;
        this.result = data;
        this.code = CommonConstant.SC_OK_200;
        this.success = true;
        return this;
    }

    /**
     * 操作成功，存在返回值，使用默认的操作成功消息
     */
    public Result<T> success(T data) {
        this.result = data;
        this.code = CommonConstant.SC_OK_200;
        this.success = true;
        return this;
    }

    public static <T> Result<T> ok() {
        Result<T> r = new Result<T>();
        r.success();
        return r;
    }

    public static <T> Result<T> ok(String message, T data) {
        Result<T> r = new Result<>();
        r.success(message, data);
        return r;
    }

    public static <T> Result<T> ok(T data) {
        Result<T> r = new Result<>();
        r.success(data);
        return r;
    }

    public static <T> Result<T> error(String message) {
         Result<T> r = new Result<>();
        r.error500(message);
        return r;
    }

    @Deprecated
    public static Result<Object> error(int code, String message) {
        Result<Object> r = new Result<>();
        r.setCode(code);
        r.setMessage(message);
        r.setSuccess(false);
        return r;
    }

    @Deprecated
    /**
     * 无权限访问返回结果
     */
    public static Result<Object> noauth(String message) {
        return error(CommonConstant.SC_IGO_NO_AUTHZ, message);
    }
    public static <T> Result<T> of(Supplier<T> dataSupplier) {
        try {
            T data = dataSupplier.get();
            return Result.ok(data);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return error(e.getMessage());
        }
    }
}
