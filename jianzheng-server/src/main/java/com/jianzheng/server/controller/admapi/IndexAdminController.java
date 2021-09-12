package com.jianzheng.server.controller.admapi;

import cn.hutool.crypto.SecureUtil;
import com.jianzheng.server.config.AppConfig;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jianzheng.server.dto.AdminLoginDTO;
import com.jianzheng.server.po.SysPermission;
import com.jianzheng.server.po.SysRole;
import com.jianzheng.server.po.User;
import com.jianzheng.server.service.SysPermissionService;
import com.jianzheng.server.service.SysRoleService;
import com.jianzheng.server.service.UserService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.constant.Consts;
import net.scode.commons.core.R;
import net.scode.commons.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/admapi/")
public class IndexAdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @ApiOperation(value = "管理员登录", notes = "登陆成功则携带token返回")
    @PostMapping("/login")
    //主要用于远程调用等需要大量传输对象的地方。
    public R login(@RequestBody @Validated AdminLoginDTO loginDTO) {
        log.debug("loginDTO:{}", loginDTO);
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("mobile", loginDTO.getMobile());
        User user = userService.getOne(query);
        System.out.println(user);
        if (user == null) {
            return R.error(Consts.FAILED_CODE, "账号不存在");
        }
        System.out.println(SecureUtil.md5(loginDTO.getPassword()));
        if (!user.getPassword().equals(SecureUtil.md5(loginDTO.getPassword()))) {
            return R.error(Consts.FAILED_CODE, "密码错误");
        }
        //生成jwt
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("mobile", user.getMobile());
        claims.put("username", user.getUsername());
        claims.put("characters", user.getCharacters());
        //生成密钥   用户信息    面向用户的类型    加密解密
        String token = JWTUtil.createJWT(claims, appConfig.getJwtSubject(), appConfig.getJwtSecret(), appConfig.getJwtLife());
        return R.ok().put("token", token);
    }

    @ApiOperation(value = "获取信息" , notes = "需要登录状态才能获取信息")
    @GetMapping("/user/info")
    //需要做信息返回
    public R userInfo(@RequestHeader ("authToken") String token) {
        //解析jwt
        Claims userTokenParse = JWTUtil.parseJWT(appConfig.getJwtSecret(),token);
        System.out.println(userTokenParse);
        String mobile = userTokenParse.get("mobile").toString();

        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("mobile", mobile);
        User user = userService.getOne(query);

        Map <String,Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("avatar", user.getAvatar());
        data.put("username",user.getUsername());

        QueryWrapper<SysRole> querys = new QueryWrapper<>();
        querys.eq("id",data.get("id"));
        SysRole sysRole = sysRoleService.getOne(querys);
        data.put("characters",sysRole.getRoleName());
        System.out.println(sysRole);
        String[] s = sysRole.getPermissions().split(String.valueOf(','));

        List<SysPermission> list = new ArrayList<>();
        for (int i = 0; i< s.length; i++){
            QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",s[i]);
            SysPermission sysPermission = sysPermissionService.getOne(queryWrapper);
            list.add(sysPermission);
        }
        data.put("premission",list);
        return R.ok().put("data",data);
    }
}
