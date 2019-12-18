package com.service.jiaxini.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
@Builder
@Data
@TableName("j_brand")
public class Brand extends Model<Brand> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品主键
     */
    private String goodId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌logo
     */
    private String brandLogo;

    /**
     * 品牌介绍
     */
    private String brandContext;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 国家
     */
    private Long country;

}
