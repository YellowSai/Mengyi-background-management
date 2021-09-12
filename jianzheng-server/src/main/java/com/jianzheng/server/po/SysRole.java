package com.jianzheng.server.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 表[sys_role]对应实体类
 *
 * @author tanghuang 2020年02月23日
 */
@Data
@ApiModel(value = "表[sys_role]的实体类")
public class SysRole {

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id", dataType = "int")
    private int id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", dataType = "String")
    private String roleName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", dataType = "String")
    private String remark;

    /**
     * 权限id，多个以逗号分隔
     */
    @ApiModelProperty(value = "权限id，多个以逗号分隔", dataType = "String")
    private String permissions;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", dataType = "java.util.Date")
    private java.util.Date createTime;

    /**
     * 通用状态,2正常,3删除
     */
    @ApiModelProperty(value = "通用状态,2正常,3删除", dataType = "int")
    private int dataStatus;

}