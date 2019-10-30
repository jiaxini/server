package com.service.jiaxini.common.enums;

/**
 * <p>
 * @description: 肤质
 * </p>
 * @author: ZengGuangfu
 * @date 2019/10/29
 */
public enum SkinEnum {
    DRY_SKIN(1, "干性肤质"),
    NEUTRAL_SKIN(2, "中性肤质"),
    OILINESS_SKIN(3, "油性肤质"),
    MIX_SKIN(4, "混合性肤质"),
    SENSITIVE_SKIN(5, "敏感性肤质"),
    ;

    private int code;
    private String msg;

    SkinEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
