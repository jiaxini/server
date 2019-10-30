package com.service.jiaxini.common.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: Exception拦截器
 * @author: ZengGuangfu
 */

@Slf4j
@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultBody<String> catchException(HttpServletRequest request, Exception e){
        if (e instanceof GlobalException){
            GlobalException globalException = (GlobalException) e;
            log.error(GlobalException.class.getSimpleName()+ " : \\n" + globalException.getMessage());
            return ResultBody.error(globalException.getCodeMsg());
        }else if(e instanceof BindException){
            BindException bindException = (BindException) e;
            List<ObjectError> allErrors = bindException.getAllErrors();
            if (CollectionUtils.isEmpty(allErrors)){
                return ResultBody.error(CodeMsg.SERVER_ERROR);
            }
            log.error(BindException.class.getSimpleName()+ " : \\n" + bindException.getMessage());
            return ResultBody.error(CodeMsg.VALIDATION_ERROR.fillMsg(allErrors.get(0).getDefaultMessage()));
        }else if(e instanceof RuntimeException){
            RuntimeException runtimeException = (RuntimeException) e;
            log.error(RuntimeException.class.getSimpleName() + " : \\n" + runtimeException.getMessage());
            return ResultBody.error(CodeMsg.RUNTIME_ERROR.fillMsg(runtimeException.getMessage()));
        }
        return ResultBody.error(CodeMsg.SERVER_ERROR);
    }
}
