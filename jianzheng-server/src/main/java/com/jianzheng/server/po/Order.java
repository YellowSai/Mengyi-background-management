package com.jianzheng.server.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[order]对应实体类
 * 
 * @author auto 2021年07月01日 
 */
@Data
@TableName(value = "`order`")
@ApiModel(value = "表[order]的实体类")
public class Order {


    /**
     * null
     */
    @ApiModelProperty(value = "id", dataType = "String")
    @TableField("`id`")
    @TableId(value="`id`",type = IdType.AUTO)
    private int id;


    /**
     * null
     */
    @ApiModelProperty(value = "顾客公司", dataType = "String")
    @TableField("`customer`")
    private String customer;

    /**
     * null
     */
    @ApiModelProperty(value = "开始时间", dataType = "java.util.Date")
    @TableField("`start_time`")
    private java.util.Date startTime;

    /**
     * null
     */
    @ApiModelProperty(value = "预计时间", dataType = "java.util.Date")
    @TableField("`estimated_time`")
    private java.util.Date estimatedTime;

    /**
     * null
     */
    @ApiModelProperty(value = "完成时间", dataType = "java.util.Date")
    @TableField("`complete_time`")
    private java.util.Date completeTime;

    /**
     * null
     */
    @ApiModelProperty(value = "金额", dataType = "double")
    @TableField("`cost`")
    private double cost;

    /**
     * null
     */
    @ApiModelProperty(value = "状态", dataType = "int")
    @TableField("`status`")
    private int status;

    /**
     * null
     */
    @ApiModelProperty(value = "制作人", dataType = "String")
    @TableField("`producer`")
    private String producer;

    /**
     * null
     */
    @ApiModelProperty(value = "更新时间", dataType = "java.util.Date")
    @TableField("`update_time`")
    private java.util.Date updateTime;

    /**
     * null
     */
    @ApiModelProperty(value = "最后操作人", dataType = "String")
    @TableField("`last_operator`")
    private String lastOperator;

    /**
     * null
     */
    @ApiModelProperty(value = "是否存在", dataType = "int")
    @TableField("`exist`")
    private int exist;

}