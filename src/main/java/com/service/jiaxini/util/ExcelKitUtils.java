package com.service.jiaxini.util;

import com.google.common.collect.Lists;
import com.service.jiaxini.common.response.CodeMsg;
import com.service.jiaxini.common.response.GlobalException;
import com.wuwenze.poi.ExcelKit;
import com.wuwenze.poi.handler.ExcelReadHandler;
import com.wuwenze.poi.pojo.ExcelErrorField;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 *
 * @description: Excel 工具类
 * </p>
 * @author: ZengGuangfu
 * @since 2019/10/25
 */
public class ExcelKitUtils {

    /**
     * 下载文档
     * @param clazz
     * @param response
     * @param target
     * @param isTemplate
     */
    public static void download(Class clazz, HttpServletResponse response, List target, boolean isTemplate){
        if (Objects.isNull(clazz) || CollectionUtils.isEmpty(target)){
            return;
        }
        ExcelKit.$Export(clazz,response).downXlsx(target, isTemplate);
    }

    /**
     * 上传文档
     */
    public static <T> List<T> upload(Class T, MultipartFile file) throws IOException {
        ArrayList<T> successList = Lists.newArrayList();
        ArrayList<Map<String, Object>> errorList = Lists.newArrayList();
        ExcelKit.$Import(T).readXlsx(file.getInputStream(), new ExcelReadHandler<T>() {
            @Override
            public void onSuccess(int sheetIndex, int rowIndex, T t) {
                successList.add(t);  // 单行读取成功，加入入库队列。
            }

            @Override
            public void onError(int sheetIndex, int rowIndex, List<ExcelErrorField> errorFields) {
                // 读取数据失败，记录了当前行所有失败的数据
                HashMap<String, Object> map = new HashMap<>();
                map.put("sheetIndex", sheetIndex);
                map.put("rowIndex", rowIndex);
                map.put("errorFields", errorFields);
                errorList.add(map);
            }
        });

        if ( !CollectionUtils.isEmpty(errorList)){
            throw new GlobalException(CodeMsg.UPLOAD_SAVEBATCH);
        }

        return successList;
    }
}
