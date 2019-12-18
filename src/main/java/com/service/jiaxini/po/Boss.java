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
 * 店主信息表
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
@Builder
@Data
@TableName("j_boss")
public class Boss extends Model<Boss> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 店主用户主键
     */
    private Long bossId;

    /**
     * 店主姓名
     */
    private String bossName;

    /**
     * 店主地址
     */
    private String bossAddress;

    /**
     * 店主联系方式
     */
    private String bossPhone;

    /**
     * 证件类型
     */
    private Integer identityType;

    /**
     * 证件号码
     */
    private String identityNum;

    /**
     * 账号状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
