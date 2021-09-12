package com.jianzheng.server.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 表[user]对应实体类
 *
 * @author auto 2020年06月01日
 */
@Data
@TableName(value = "`user`")
@ApiModel(value = "表[user]的实体类")
public class User {

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id", dataType = "int")
    @TableField("`id`")
    @TableId(value="`id`",type = IdType.AUTO)
    private int id;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", dataType = "String")
    @TableField("`mobile`")
    private String mobile;

    /**
     * 密码,管理员使用
     */
    @ApiModelProperty(value = "密码,管理员使用", dataType = "String")
    @TableField("`password`")
    private String password;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名", dataType = "String")
    @TableField("`name`")
    private String name;

    /**
     * 微信昵称
     */
    @ApiModelProperty(value = "微信昵称", dataType = "String")
    @TableField("`nickname`")
    private String nickname;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像", dataType = "String")
    @TableField("`avatar`")
    private String avatar;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value = "最后登录时间", dataType = "java.util.Date")
    @TableField("`last_time`")
    private java.util.Date lastTime;

    /**
     * 最后登录ip
     */
    @ApiModelProperty(value = "最后登录ip", dataType = "String")
    @TableField("`last_ip`")
    private String lastIp;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", dataType = "java.util.Date")
    @TableField("`create_time`")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;

    /**
     * 通用状态,2正常,3删除,4停用
     */
    @ApiModelProperty(value = "通用状态,2正常,3删除,4停用", dataType = "int")
    @TableField("`data_status`")
    private String dataStatus;

    /**
     * 角色
     */
    @ApiModelProperty(value = "角色", dataType = "String")
    @TableField("`characters`")
    private String characters;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", dataType = "String")
    @TableField("`motto`")
    private String motto;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", dataType = "String")
    @TableField("`username`")
    private String username;
}