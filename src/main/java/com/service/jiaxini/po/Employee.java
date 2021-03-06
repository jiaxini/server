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
 * 用户表
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
@Builder
@Data
@TableName("j_employee")
public class Employee extends Model<Employee> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String telphone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 男0  女1
     */
    private Integer sex;

    /**
     * 生日
     */
    private Date birth;

    /**
     * 0 正常 1 锁定 2 删除 3 冻结
     */
    private Integer status;

    /**
     * 0 普通 1 1级会员 2 2级会员 ...
     */
    private Integer vip;

    /**
     * 积分
     */
    private BigDecimal score;

    /**
     * 用户余额
     */
    private BigDecimal surplus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 0 买家，1卖家
     */
    private Integer roleType;

    /**
     * 头像
     */
    private String headImage;

}
