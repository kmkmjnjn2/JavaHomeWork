package com.ebanma.cloud.usertestall.domain.common;

/**
 * @author WHY
 * @version $ Id: ErrorCode, v 0.1 2023/03/21 20:09 kmkmj Exp $
 */
public enum ErrorCode {

    SUCCESS("00000", "成功"),
    PARAM_ERROR("A0400", "请求参数错误"),
    SYSTEM_ERROR("B0001", "系统执行出错"),
    ;



    /**
     * 编码
     */
    private final String code;
    /**
     * 描述信息
     */
    private final String desc;

    ErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
    }