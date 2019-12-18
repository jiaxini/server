package com.service.jiaxini.po;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

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
@TableName("j_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户主键
     */
    private Long empId;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 未计算折扣时的商品总金额
     */
    private BigDecimal orderMoney;

    /**
     * 运费
     */
    private BigDecimal deliverMoney;

    /**
     * 订单总金额(包括运费)
     */
    private BigDecimal totalMoney;

    /**
     * 实际订单总金额
     */
    private BigDecimal realTotalMoney;

    /**
     * 支付方式
     */
    private Integer payType;

    /**
     * 是否支付
     */
    private Integer isPay;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收获地址
     */
    private String receiverAddress;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 积分累计
     */
    private BigDecimal orderScore;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 是否退款
     */
    private Integer isRefund;

    /**
     * 是否有评论
     */
    private Integer isDiscuss;

    /**
     * 取消原因
     */
    private String cancelReason;

    /**
     * 拒收原因
     */
    private String rejectReason;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     * 收获时间
     */
    private Date receiveTime;
}
