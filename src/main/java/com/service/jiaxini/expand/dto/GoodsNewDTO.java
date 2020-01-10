package com.service.jiaxini.expand.dto;

import lombok.Builder;
import lombok.Data;

/**
 * <p>
 *
 * @description: 验证商品不能重复
 * </p>
 * @author: ZengGuangfu
 * @date 2020/1/6
 * @return: com.service.jiaxini.expand.dto
 */
@Builder
@Data
public class GoodsNewDTO {

    private Long id;

    /** 商品编号 */
    private String productNo;

    /** 商品名 */
    private String name;

    /** 创建人登录账号 */
    private String createLoginName;
}
