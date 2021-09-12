package com.jianzheng.server.po;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 表[sys_permission]对应实体类
 *
 * @author tanghuang 2020年02月22日
 */
@Data
@ApiModel(value = "表[sys_permission]的实体类")
public class SysPermission {

    /**
     * 自增id
     */
    @ApiModelProperty(value = "自增id", dataType = "int")
    private int id;

    /**
     * 父权限ID，一级菜单为0
     */
    @ApiModelProperty(value = "父权限ID，一级菜单为0", dataType = "int")
    private int parentId;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称", dataType = "String")
    private String permissionName;

    /**
     * 标识名
     */
    @ApiModelProperty(value = "标识名", dataType = "String")
    private String keyName;

    /**
     * 组件名
     */
    @ApiModelProperty(value = "组件名", dataType = "String")
    private String component;

    /**
     * 类别，1目录，2菜单，3权限
     */
    @ApiModelProperty(value = "类别，1目录，2菜单，3权限", dataType = "int")
    private int permissionType;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    @ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)", dataType = "String")
    private String permits;

    /**
     * 权限URL，菜单用
     */
    @ApiModelProperty(value = "权限URL，菜单用", dataType = "String")
    private String url;

    /**
     * 权限图标，菜单用
     */
    @ApiModelProperty(value = "权限图标，菜单用", dataType = "String")
    private String icon;

    /**
     * 排序权重
     */
    @ApiModelProperty(value = "排序权重", dataType = "int")
    private int sort;

    /**
     * 是否隐藏,1显示,2隐藏
     */
    @ApiModelProperty(value = "是否隐藏,1显示,2隐藏", dataType = "int")
    private int hidden;

    /**
     * 通用状态,2正常,3删除
     */
    @ApiModelProperty(value = "通用状态,2正常,3删除", dataType = "int")
    private int dataStatus;

    /**
     * 用于保存一个菜单的子菜单
     */
    @TableField(exist = false)
    private List<SysPermission> treeMenu;

}