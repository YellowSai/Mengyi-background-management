package com.jianzheng.server.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class AdminLoginDTO {
    @ApiModelProperty(value = "手机号", dataType = "String", required = true)
    @Pattern(regexp = "^\\d{11}$", message = "请输入11为手机号码！")
    private String mobile;

    @ApiModelProperty(value = "用户名", dataType = "String", required = true)
    @Pattern(regexp = "^[A-Za-z0-9]{4,20}$", message = "账号只能由字母、数字构成,最少4位最高20位！")
    private String username;

    @ApiModelProperty(value = "密码", dataType = "String", required = true)
    @Pattern(regexp = "^[A-Za-z0-9$@#%^&]{6,20}$", message = "密码只能由字母、数字、$、@、#、%、^、&构成,最少6位最高20位！")
    private String password;

    @ApiModelProperty(value = "角色", dataType = "String", required = true)
    private String characters;
}
