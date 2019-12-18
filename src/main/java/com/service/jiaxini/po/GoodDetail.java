package com.service.jiaxini.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

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
@TableName("j_good_detail")
public class GoodDetail extends Model<GoodDetail> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品主键
     */
    private Long goodId;

    /**
     * 毛重
     */
    private BigDecimal weight;

    /**
     * 产地
     */
    private Long origin;

    /**
     * 性别推荐
     */
    private Integer sex;

    /**
     * 功效
     */
    private String effect;

    /**
     * 是否起泡
     */
    private Integer isBlister;

    /**
     * 适合肤质
     */
    private Integer skinTexture;

}
