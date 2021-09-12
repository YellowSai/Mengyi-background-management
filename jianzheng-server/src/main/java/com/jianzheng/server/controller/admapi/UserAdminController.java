package com.jianzheng.server.controller.admapi;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jianzheng.server.dto.AdminLoginDTO;
import com.jianzheng.server.po.User;
import com.jianzheng.server.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.constant.Consts;
import net.scode.commons.core.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/admapi/user")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/list")
    public R pageUser(@ApiParam(value = "搜索关键词") @RequestParam(required = false, defaultValue = "") String keywords,String username, Page page) {
        Page<User> pageData = userService.pageSearch(keywords,username, page);
        return R.data(pageData);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/save")
    public R add(@RequestBody User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        User users = userService.getOne(queryWrapper);
        if (user.getId() > 0) {
            if (users.getDataStatus().equals("3")){
                return R.error(Consts.FAILED_CODE, "该账号已停用,无法进行操作");
            }
            user.setPassword(SecureUtil.md5(user.getPassword()));
            userService.updateById(user);
        }else {
            if (users != null){
                return R.error(Consts.FAILED_CODE, "账号已存在");
            }
            user.setPassword(SecureUtil.md5(user.getPassword()));
            userService.save(user);
        }
        return R.ok();
    }

    @ApiOperation(value = "更新用户")
    @PutMapping("/update/")
    public R update(@RequestBody User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",user.getId());
        User users = userService.getOne(queryWrapper);
        if (users.getDataStatus().equals("3")){
            return R.error(Consts.FAILED_CODE, "该账号已停用,无法进行操作");
        }
        user.setPassword(SecureUtil.md5(user.getPassword()));
        userService.update(user,queryWrapper);
        return R.ok();
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping("/{id}")
    public R getInfoById(@PathVariable("id") int id) {
        return R.ok().put("user",userService.getById(id));
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public R delete(@ApiParam(value = "用户id") @PathVariable("id") int id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        User user = userService.getOne(queryWrapper);
        if (user.getDataStatus().equals("3")){
            return R.error(Consts.FAILED_CODE, "该账号已停用,无法进行操作");
        }
        userService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/pwd/")
    public R changePwd(@RequestBody @Validated AdminLoginDTO loginDTO) {
        log.debug("loginDTO:{}", loginDTO);
        UpdateWrapper<User> query = new UpdateWrapper<>();
        query.eq("username", loginDTO.getUsername()).set("password",SecureUtil.md5(loginDTO.getPassword()));
        User user = userService.getOne(query);
        if (user == null) {
            return R.error(Consts.FAILED_CODE, "账号不存在");
        }
        userService.update(null,query);
        return R.ok();
    }

    @ApiOperation(value = "停用")
    @GetMapping("/stop/{id}")
    public R stop(@PathVariable("id") int id) {
        System.out.println(id);
        UpdateWrapper<User> queryWrapper = new UpdateWrapper<>();
        queryWrapper.eq("id",id).set("data_status",3);
        User users = userService.getOne(queryWrapper);
        if (users.getDataStatus().equals("3")){
            queryWrapper.eq("id",id).set("data_status",2);
            userService.update(null,queryWrapper);
        }
        userService.update(null,queryWrapper);
        return R.ok();
    }

}
