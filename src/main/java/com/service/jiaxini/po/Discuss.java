package com.service.jiaxini.po;

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
 * 评论表
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
@Builder
@Data
@TableName("j_discuss")
public class Discuss extends Model<Discuss> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 评论内容
     */
    private String context;

    /**
     * 评论等级
     */
    private Integer disLevel;

    /**
     * 商品主键
     */
    private Long goodId;

    /**
     * 订单主键
     */
    private Long orderId;

    /**
     * 是否匿名
     */
    private Integer isAnonymous;

    /**
     * 订单图片
     */
    private String disImage;

    /**
     * 是否首评
     */
    private Integer isFirst;

    /**
     * 如果是追评，那么它的首评主键
     */
    private Long parentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 标签
     */
    private String lables;
}
