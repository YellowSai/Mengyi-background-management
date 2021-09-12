package com.jianzheng.server.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysRoleDTO {
    @ApiModelProperty(value = "角色名称", dataType = "String", required = true)
    private String roleName;

    @ApiModelProperty(value = "权限id，多个以逗号分隔", dataType = "String", required = true)
    private String permissions;
}
