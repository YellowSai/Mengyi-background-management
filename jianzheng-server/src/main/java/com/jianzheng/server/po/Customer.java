package com.jianzheng.server.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[customer]对应实体类
 *
 * @author huangyuhan 2021年06月29日
 */
@Data
@TableName(value = "`customer`")
@ApiModel(value = "表[customer]的实体类")
public class Customer {

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id", dataType = "int")
    @TableField("`id`")
    @TableId(value="`id`",type = IdType.AUTO)
    private int id;

    /**
     * 客户公司
     */
    @ApiModelProperty(value = "客户公司", dataType = "String")
    @TableField("`customer`")
    private String customer;

    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人", dataType = "String")
    @TableField("`director`")
    private String director;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间", dataType = "java.util.Date")
    @TableField("`add_time`")
    private java.util.Date addTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", dataType = "java.util.Date")
    @TableField("`update_time`")
    private java.util.Date updateTime;

    /**
     * 最后操作人
     */
    @ApiModelProperty(value = "最后操作人", dataType = "String")
    @TableField("`last_operator`")
    private String lastOperator;

    /**
     * 该条是否存在，1存在，2已删除
     */
    @ApiModelProperty(value = "该条是否存在，1存在，2已删除", dataType = "int")
    @TableField("`exist`")
    private int exist;
}
