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
@TableName("j_address")
public class Address extends Model<Address> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private String empId;

    /**
     * 省
     */
    private Integer province;

    /**
     * 市
     */
    private Integer city;

    /**
     * 区县
     */
    private Integer area;

    /**
     * 详细地址
     */
    private String detailed;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 收件人姓名
     */
    private String addressee;

    /**
     * 手机
     */
    private String telphone;

    /**
     * 是否默认地址
     */
    private Integer isDefault;

    /**
     * 状态
     */
    private Integer status;

}
