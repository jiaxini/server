package com.service.jiaxini.expand.vo;

import java.util.List;

/**
 * <p>
 *
 * @description: 商品分类信息
 * </p>
 * @author: ZengGuangfu
 * @date 2020/1/6
 * @return: com.service.jiaxini.expand.vo.GoodsTypeVO
 */
public class GoodsTypeVO {

    /** 主键 */
    private Long id;

    /** 分类简称 */
    private String typeName;

    /** 子分类 */
    List<GoodsTypeVO> children;
}
