package com.service.jiaxini.conf.response;

import lombok.Getter;

/**
 * @description: 自定义Exception
 * @author: ZengGuangfu
 */

@Getter
public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 7709144595255161553L;

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg){
        this.codeMsg = codeMsg;
    }

    public GlobalException(String...error){
        codeMsg = CodeMsg.CUSTOM_ERROR.fillMsg(error);
    }

}
