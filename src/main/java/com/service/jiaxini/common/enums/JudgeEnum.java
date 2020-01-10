package com.service.jiaxini.common.enums;

import lombok.Getter;

/**
 * <p>
 *
 * @description: 判断用的枚举
 * </p>
 * @author: ZengGuangfu
 * @date 2020/1/6
 * @return: com.service.jiaxini.common.enums
 */
@Getter
public enum JudgeEnum {

    NO(0, "否"),
    YES(1, "是"),
    ;

    private Integer code;

    private String message;

    JudgeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
