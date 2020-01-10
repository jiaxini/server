package com.service.jiaxini.util;

import com.service.jiaxini.common.response.CodeMsg;
import com.service.jiaxini.common.response.GlobalException;

/**
 * <p>
 *
 * @description: 通用判断
 * </p>
 * @author: ZengGuangfu
 * @since 2019/10/25
 */
public class JudgeUtils {

    public static void nonNull(Object...args){
        if (args == null) {
            throw new GlobalException(CodeMsg.PARAM_NOT_NULL);
        } else {
            Object[] var4 = args;
            int var3 = args.length;

            for(int var2 = 0; var2 < var3; ++var2) {
                Object obj = var4[var2];
                if (obj == null) {
                    throw new GlobalException(CodeMsg.PARAM_NOT_NULL);
                }
            }

        }
    }
}
