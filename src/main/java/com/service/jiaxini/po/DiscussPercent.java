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
 * 评论积分表
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
@Builder
@Data
@TableName("j_discuss_percent")
public class DiscussPercent extends Model<DiscussPercent> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评论主键
     */
    private Long discussId;

    /**
     * 商品主键
     */
    private Long goodId;

    /**
     * 好评率
     */
    private BigDecimal wellPercent;

    /**
     * 中评率
     */
    private BigDecimal middlePercent;

    /**
     * 好评率
     */
    private BigDecimal negativePercent;

    /**
     * 星数积分
     */
    private BigDecimal starNum;

}
