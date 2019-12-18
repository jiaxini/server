package com.service.jiaxini.po;

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
@TableName("j_product_boss")
public class ProductBoss extends Model<ProductBoss> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品主键
     */
    private Long goodsId;

    /**
     * 店家主键
     */
    private Long bossId;

}
