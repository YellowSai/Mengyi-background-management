package com.jianzheng.server.controller.admapi;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jianzheng.server.dto.SysRoleDTO;
import com.jianzheng.server.po.SysRole;
import com.jianzheng.server.service.SysRoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.constant.Consts;
import net.scode.commons.core.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/admapi/sysRole")
public class SysRoleAdminController {
    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value = "获取角色列表")
    @GetMapping("/rolelist")
    public R pageRole(@ApiParam(value = "搜索关键词") @RequestParam(required = false, defaultValue = "") String keywords, Page page) {
        Page<SysRole> pageData = sysRoleService.pageSearch(keywords, page);
        return R.data(pageData);
    }

    @ApiOperation(value = "获取角色")
    @GetMapping("/role")
    public R role(){
        List<SysRole> list = sysRoleService.list();
        return R.ok().put("role",list);
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping("/{id}")
    public R getInfoById(@PathVariable("id") int id) {
        return R.ok().put("sysRole",sysRoleService.getById(id));
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/delete/{id}")
    public R del(@ApiParam(value = "权限id") @PathVariable("id") int id){
        sysRoleService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "更新角色")
    @PutMapping("/update/")
    public R upd(@RequestBody SysRole sysRole){
        UpdateWrapper<SysRole> query = new UpdateWrapper<>();
        query.eq("id", sysRole.getId());
        sysRoleService.update(sysRole,query);
        return R.ok();
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("/add")
    public R add(@RequestBody SysRoleDTO sysRoleDTO){
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        query.eq("role_name", sysRoleDTO.getRoleName());
        SysRole sysRole = sysRoleService.getOne(query);
        if (sysRole != null){
            return R.error(Consts.FAILED_CODE, "该角色已存在");
        }
        SysRole sysRoles = new SysRole();
        sysRoles.setRoleName(sysRoleDTO.getRoleName());
        sysRoles.setPermissions(sysRoleDTO.getPermissions());
        sysRoleService.save(sysRoles);
        return R.ok();
    }

    @ApiOperation(value = "添加", notes = "id大于0表示修改")
    @PostMapping("/save")
    public R save(@RequestBody SysRole sysRole){
        QueryWrapper<SysRole> query = new QueryWrapper<>();
        query.eq("role_name", sysRole.getRoleName());
        SysRole sysRoles = sysRoleService.getOne(query);
        if (sysRoles == null){
            if (sysRole.getId() > 0){
                sysRoleService.updateById(sysRole);
            }else {
                sysRoleService.save(sysRole);
            }
            return R.ok();
        }
        return R.error(Consts.FAILED_CODE, "该角色已存在");
    }
}
