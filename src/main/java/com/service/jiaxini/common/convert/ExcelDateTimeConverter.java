package com.service.jiaxini.common.convert;

import com.wuwenze.poi.convert.ReadConverter;
import com.wuwenze.poi.exception.ExcelKitReadConverterException;

import java.util.Date;

/**
 * <p>
 *
 * @description: 导入excel ，时间类型转换器
 * </p>
 * @author: ZengGuangfu
 * @since 2019/10/25
 */

/**
 * ReadConverter 读取文件的转换器
 */
public class ExcelDateTimeConverter implements ReadConverter {

    @Override
    public Object convert(Object o) throws ExcelKitReadConverterException {
        String value = (String)o;
        return new Date(value);
    }
}
