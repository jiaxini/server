package com.service.jiaxini.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * <p>
 *
 * @description: 敏感词校验
 * <p>
 * </p>
 * @author: ZengGuangfu
 * @since 2019/10/18
 */
public class SensitiveUtils {
    public static String[] sensitives;
    static {
        ResourceBundle rb = ResourceBundle.getBundle("sensitive", Locale.CHINESE);
        sensitives = rb.getString("sensitive").split("\\|");
    }

    /**
     * 敏感词判断,将含有敏感词的判定替换为***
     * @param content 替换前的字符串
     * @return 替换后的字符串
     */
    public static String replaceSensitive(String content){
        if(StringUtils.isBlank(content)){
            return "";
        }
        for(String sen : sensitives){
            if(content.contains(sen)){
                content = content.replace(sen, "***");
            }
        }
        return content;
    }

}
