package com.service.jiaxini.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
@Builder
@Data
@TableName("j_goods")
public class Goods extends Model<Goods> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品分类主键
     */
    private Long goodsTypeId;

    /**
     * 品牌主键
     */
    private Long brandId;

    /**
     * 商品编号
     */
    private String productNo;

    /**
     * 商品名
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 简介
     */
    private String synopsis;

    /**
     * 是否促销
     */
    private Integer isOnSale;

    /**
     * 促销价格
     */
    private BigDecimal salePrice;

    /**
     * 售后支持信息
     */
    private String surport;

    /**
     * 商品图片地址
     */
    private String imageAddress;

    /**
     * 商品库存
     */
    private BigDecimal goodsStock;

    /**
     * 预警库存
     */
    private BigDecimal warnStock;

    /**
     * 是否上架
     */
    private Integer issale;

    /**
     * 是否新产品
     */
    private Integer isNew;

    /**
     * 是否热门产品
     */
    private Integer isHot;

    /**
     * SEO 关键字
     */
    private String seoKeys;

    /**
     * 访问数
     */
    private BigDecimal viewNum;

    /**
     * 评价数量
     */
    private BigDecimal appraiseNum;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 销量
     */
    private BigDecimal saleNumber;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
