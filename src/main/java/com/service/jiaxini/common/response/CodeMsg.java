package com.service.jiaxini.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 结果集
 * @author: ZengGuangfu
 */
@Getter
@AllArgsConstructor
public class CodeMsg {

    private Integer code;
    private String msg;

    public static final CodeMsg SUCCESS = new CodeMsg(200 , "SUCCESS");

    public static final CodeMsg CUSTOM_ERROR = new CodeMsg(600, "%s");

    public static final CodeMsg SERVER_ERROR = new CodeMsg(500001 , "服务器异常");
    public static final CodeMsg OPERATION_FAILD = new CodeMsg(500001 , "操作失败");
    public static CodeMsg REQUEST_ILLEGLE = new CodeMsg(500103, "请求异常");
    public static CodeMsg VALIDATION_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    public static CodeMsg RUNTIME_ERROR = new CodeMsg(500105, "运行时异常：%s");

    public static CodeMsg GLOBAL_ERROR = new CodeMsg(500102, "全局异常：%s");
    public static CodeMsg CAPTCHA_WRONG = new CodeMsg(500201, "验证码有误");
    public static CodeMsg NEED_LOGIN = new CodeMsg(500202, "需要登录才能操作");
    public static CodeMsg USER_NOT_EXIT = new CodeMsg(500203, "用户名或密码错误");
    public static CodeMsg LOGIN_TOO_MUCH = new CodeMsg(500204, "登录次数超过限制，请半个小时后再次尝试");
    public static CodeMsg SESSION_MANAGER_ERROR = new CodeMsg(500205, "SessionManager 取Session 失败");
    public static CodeMsg UPLOAD_SAVEBATCH = new CodeMsg(500206, "上传文件后，存库出错");

    public static CodeMsg TEAM_ERROR = new CodeMsg(600001, "登录用户同盟与操作数据不符");
    public static CodeMsg NOT_LEADER = new CodeMsg(600002, "无权操作");
    public static CodeMsg NO_AUTH = new CodeMsg(600003, "无权操作");

    public CodeMsg fillMsg(String... args){
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }
}
