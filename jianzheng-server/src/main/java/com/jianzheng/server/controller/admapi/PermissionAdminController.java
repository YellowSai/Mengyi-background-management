package com.jianzheng.server.controller.admapi;

import com.jianzheng.server.po.SysPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jianzheng.server.po.SysRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import com.jianzheng.server.service.SysPermissionService;
import com.jianzheng.server.service.SysRoleService;
import net.scode.commons.constant.DataStatus;
import net.scode.commons.core.R;
import net.scode.commons.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.stream.Collectors;

/**
 * 权限与角色管理
 *
 * @author tanghuang 2020年02月23日
 */
@Slf4j
@Api(tags = {"权限与角色管理"})
@RestController
@RequestMapping("/admapi/permission")
public class PermissionAdminController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 获取权限列表
     *
     * @param keywords
     * @param page
     * @return
     */
    @ApiOperation(value = "获取权限列表")
    @GetMapping("/page")

    public R pagePermission(@ApiParam(value = "搜索关键词") @RequestParam(required = false, defaultValue = "") String keywords,String searchValue, Page page) {
        log.debug(searchValue);
        Page<SysPermission> pageData = sysPermissionService.pageSearch(keywords,searchValue, page);
        return R.data(pageData);
    }
    /**
     * 保存权限<br>
     * id大于0表示修改
     *
     * @param sysPermission
     * @return
     */
    @ApiOperation(value = "保存权限", notes = "id大于0表示修改")
    @PostMapping("/save")
    public R save(@RequestBody SysPermission sysPermission) {
        if (sysPermission.getId() > 0) {
            sysPermissionService.updateById(sysPermission);
        } else {
            sysPermission.setUrl(Convert.notNull(sysPermission.getUrl()));
            sysPermission.setKeyName(Convert.notNull(sysPermission.getKeyName()));
            sysPermission.setPermits(Convert.notNull(sysPermission.getPermits()));
            sysPermission.setComponent(Convert.notNull(sysPermission.getComponent()));
            sysPermission.setIcon(Convert.notNull(sysPermission.getIcon()));
            sysPermission.setDataStatus(DataStatus.NORMAL.getValue());
            sysPermissionService.save(sysPermission);
        }
        return R.ok();
    }

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除权限")
    @DeleteMapping("/{id}")
    public R del(@ApiParam(value = "权限id") @PathVariable("id") int id) {
        if (id > 0) {
            sysPermissionService.removeById(id);
        }
        return R.ok();
    }

    /**
     * 获取角色列表
     *
     * @param keywords
     * @param page
     * @return
     */
    @ApiOperation(value = "获取角色列表")
    @GetMapping("/role/page")
    public R pageRole(@ApiParam(value = "搜索关键词") @RequestParam(required = false, defaultValue = "") String keywords, Page page) {
        Page<SysRole> pageData = sysRoleService.pageSearch(keywords, page);
        return R.data(pageData);
    }

    /**
     * 保存角色<br>
     * id大于0表示修改
     *
     * @param role
     * @return
     */
    @ApiOperation(value = "保存角色", notes = "id大于0表示修改")
    @PostMapping("/role")
    public R saveRole(@RequestBody SysRole role) {
        if (role.getId() > 0) {
            sysRoleService.updateById(role);
        } else {
            role.setDataStatus(DataStatus.NORMAL.getValue());
            sysRoleService.save(role);
        }
        return R.ok();
    }

    @GetMapping("/allow")
    public R listWithTree() {
        // 查找所有菜单数据
        List<SysPermission> lists = sysPermissionService.list();
        // 把数据组合成树形结构
        List<SysPermission> list = lists.stream()
                // 查找第一级菜单 过滤
                .filter(sysPermission -> sysPermission.getParentId() == 0)
                // 查找子菜单并放到第一级菜单中
                .map(sysRoleService -> {
                    sysRoleService.setTreeMenu(getChildren(sysRoleService, lists));
                    return sysRoleService;
                })
                // 把处理结果收集成一个 List 集合
                .collect(Collectors.toList());
        return R.ok().put("list",list);
    }

    public List<SysPermission> getChildren(SysPermission root, List<SysPermission> all) {
        List<SysPermission> children = all.stream()
                // 根据 父菜单 ID 查找当前菜单 ID，以便于找到 当前菜单的子菜单
                .filter(sysPermission -> sysPermission.getParentId() == root.getId())
                // 递归查找子菜单的子菜单
                .map((sysPermission) -> {
                    sysPermission.setTreeMenu(getChildren(sysPermission, all));
                    return sysPermission;
                })
                // 把处理结果收集成一个 List 集合
                .collect(Collectors.toList());
        return children;
    }
}
