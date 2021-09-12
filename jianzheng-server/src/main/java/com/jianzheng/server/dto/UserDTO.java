package com.jianzheng.server.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDTO {

    @ApiModelProperty(value = "手机号", dataType = "String", required = true)
    private String mobile;

    @ApiModelProperty(value = "密码,管理员使用", dataType = "String", required = true)
    private String password;

    @ApiModelProperty(value = "姓名", dataType = "String", required = true)
    private String name;

    @ApiModelProperty(value = "微信昵称", dataType = "String", required = true)
    private String nickname;

    @ApiModelProperty(value = "头像", dataType = "String", required = true)
    private String avatar;

    @ApiModelProperty(value = "最后登录时间", dataType = "java.util.Date", required = true)
    private java.util.Date lastTime;

    @ApiModelProperty(value = "最后登录ip", dataType = "String", required = true)
    private String lastIp;

    @ApiModelProperty(value = "创建时间", dataType = "java.util.Date", required = true)
    private java.util.Date createTime;

    @ApiModelProperty(value = "通用状态,2正常,3删除", dataType = "int", required = true)
    private String dataStatus;

    @ApiModelProperty(value = "角色", dataType = "String", required = true)
    private String character;

    @ApiModelProperty(value = "备注", dataType = "String", required = true)
    private String motto;
}
