package com.jianzheng.server.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderDTO {
    @ApiModelProperty(value = "ID（订单号）", dataType = "int", required = true)
    private String id;

    @ApiModelProperty(value = "客户公司", dataType = "String", required = true)
    private String customer;

    @ApiModelProperty(value = "接入时间", dataType = "java.util.Date", required = true)
    private java.util.Date startTime;

    @ApiModelProperty(value = "预计完成时间", dataType = "java.util.Date", required = true)
    private java.util.Date estimatedTime;

    @ApiModelProperty(value = "完成时间", dataType = "java.util.Date", required = true)
    private java.util.Date completeTime;

    @ApiModelProperty(value = "金额", dataType = "String", required = true)
    private double cost;

    @ApiModelProperty(value = "状态", dataType = "String", required = true)
    private String status;

    @ApiModelProperty(value = "制作", dataType = "String", required = true)
    private String producer;
}
