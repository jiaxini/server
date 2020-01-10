package com.service.jiaxini.expand.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 *
 * @description: 用户登录信息
 * </p>
 * @author: ZengGuangfu
 * @since 2019-09-2019/9/18
 */

@Data
public class LoginDTO {

    /** 登录名 */
    @NotEmpty
    private String loginName;

    /** 密码 */
    @NotEmpty
    private String password;

    /** 验证码 */
    @NotEmpty
    private String captcha;

    /** 记住我 */
    private boolean rememberMe;
}
