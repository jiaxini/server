package com.service.jiaxini.expand.param;

import com.service.jiaxini.common.inter.SaveGroup;
import lombok.Data;

import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * @description: 新增或修改商品信息
 * </p>
 * @author: ZengGuangfu
 * @date 2020/1/6
 * @return: com.service.jiaxini.expand.param.SaveGoodsParam
 */

@Data
public class SaveGoodsParam {

    /** 商品主键 */
    private Long id;

    /** 商品分类 */
    @NotNull
    private Long goodsTypeId;

    /** 商品品牌 */
    @NotNull
    private Long brandId;

    /** 商品编号 */
    @NotEmpty
    @Size(max = 20, message = "商品编号过长")
    private String productNo;

    /** 商品名 */
    @NotEmpty( message = "商品名不能为空")
    @Size(max = 200, message = "商品名过长")
    private String name;

    /** 价格 */
    @DecimalMin("0.01")
    @DecimalMax("9999999999.99")
    private BigDecimal price;

    /** 简介 */
    @Size(max = 500, message = "商品简介过长")
    private String synopsis;

    /** 售后支持信息 */
    @Size(max = 200, message = "售后支持信息过长")
    private String surport;

    /** 商品图片地址 */
    @Size(max = 1000, message = "商品图片地址过长")
    private String imageAddress;

    /** 商品库存 */
    @NotNull
    private BigDecimal goodsStock;

    /** 预警库存 */
    @NotNull
    private BigDecimal warnStock;

    /** 是否新产品 */
    private Integer isNew;

    /** 是否热门产品 */
    private Integer isHot;

    /** SEO 关键字 */
    @Size(max = 1000, message = "SEO 关键字文本过长")
    private String seoKeys;

    /** 排序号 */
    private Integer sort;

}
