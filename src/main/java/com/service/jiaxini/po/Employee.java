package com.service.jiaxini.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author zengguangfu
 * @since 2019/10/25
 */

@Data
@TableName("e_employee")
@Excel(value = "用户信息")
public class Employee extends Model<Employee> {

    private static final long serialVersionUID = -728499564553926152L;

    @TableId(value = "id", type = IdType.AUTO)
    @ExcelField(value = "编号", width = 30, name = "id")
    private Integer id;

    /**
     * 名字
     */
    @NotNull
    @ExcelField(value = "名字", name = "name")
    private String name;

    /**
     * 登录名
     */
    @NotNull
    @ExcelField(value = "登录账号", required = true, name = "loginName")
    private String loginName;

    /**
     * 密码
     */
    @NotNull
    @ExcelField(value = "用户密码", required = true, name = "password")
    private String password;

    /**
     * 服务器
     */
    @ExcelField(value = "服务器区号", name = "serverNumber")
    private Integer serverNumber;

    /**
     * 出生州
     */
    @ExcelField(value = "出生州府", name = "born")
    private Integer born;

    /**
     * 同盟
     */
    private Integer teamId;

    /**
     * 状态(分在野、成员、官员、指挥官、副盟主和盟主)
     */
    @ExcelField(value = "成员等级", name = "roleId")
    private Integer roleId;

    /**
     * 电话号码
     */
    @ExcelField(value = "联系电话", name = "phone")
    private String phone;

    /**
     * QQ号
     */
    @ExcelField(value = "QQ", name = "tencent")
    private String tencent;

    /**
     * 曾用名
     */
    @ExcelField(value = "曾用名", name = "oldName")
    private String oldName;

    /**
     * 创建时间
     */
//    @ExcelField(value = "创建时间", name = "createTime", readConverter = ExcelDateTimeConverter.class)
//    private Date createTime;

    /**
     * 修改时间
     */
//    @ExcelField(value = "最近修改时间", required = false, name = "updateTime", readConverter = ExcelDateTimeConverter.class)
//    private Date updateTime;

}
