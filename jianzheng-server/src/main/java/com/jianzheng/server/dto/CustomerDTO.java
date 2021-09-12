package com.jianzheng.server.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {
    @ApiModelProperty(value = "客户公司", dataType = "String", required = true)
    private String customer;

    @ApiModelProperty(value = "负责人", dataType = "String", required = true)
    private String director;

    @ApiModelProperty(value = "ID", dataType = "int", required = true)
    private String id;
}
