package com.service.jiaxini.common.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @description: 包装结果集
 * @author: ZengGuangfu
 */
@Data
public class ResultBody<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    public ResultBody(){
        this.code = CodeMsg.SUCCESS.getCode();
        this.msg = CodeMsg.SUCCESS.getMsg();
    }

    public ResultBody(T data){
        this.code = CodeMsg.SUCCESS.getCode();
        this.msg = CodeMsg.SUCCESS.getMsg();
        this.data = data;
    }

    public ResultBody(CodeMsg codeMsg){
        if (Objects.isNull(codeMsg)){
            return;
        }
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    public static <T> ResultBody<T> success(T data){
        return new ResultBody(data);
    }

    public static <T> ResultBody<T> success(){
        return new ResultBody();
    }

    public static ResultBody error(CodeMsg codeMsg){
        return new ResultBody(codeMsg);
    }

}
