package com.service.jiaxini.controller;

import com.service.jiaxini.common.response.CodeMsg;
import com.service.jiaxini.common.response.GlobalException;
import com.service.jiaxini.common.response.ResultBody;
import com.service.jiaxini.expand.dto.LoginDTO;
import com.service.jiaxini.po.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

/**
 * <p>
 *
 * @description: 基础类
 * </p>
 * @author: ZengGuangfu
 * @date 2020/1/6
 * @return: com.service.jiaxini.controller
 */
public class BaseController {

    protected Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected LoginDTO getCurrentUser() {
        Object principal = null;
        try{
            principal = getSubject().getPrincipal();
        }catch (Exception e){
            throw new GlobalException(CodeMsg.NEED_LOGIN);
        }
        if (Objects.isNull(principal))      throw new GlobalException(CodeMsg.NEED_LOGIN);
        LoginDTO loginVO = new LoginDTO();
        BeanUtils.copyProperties(principal,loginVO);
        return loginVO;
    }

    protected Session getSession() {
        return getSubject().getSession();
    }

    protected Session getSession(Boolean flag) {
        return getSubject().getSession(flag);
    }

    protected void login(AuthenticationToken token) {
        getSubject().login(token);
    }

    protected void logout(){
        getSubject().logout();
    }

    protected boolean isAuthenticated(){
        return getSubject().isAuthenticated();
    }

    protected ResultBody<String> excuse(boolean base){
        if (base){
            return ResultBody.success();
        }else{
            return ResultBody.error(CodeMsg.OPERATION_FAILD);
        }
    }
}
