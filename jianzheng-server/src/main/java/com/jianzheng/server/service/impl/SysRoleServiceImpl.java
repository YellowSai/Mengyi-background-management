package com.jianzheng.server.service.impl;

import com.jianzheng.server.dao.SysRoleDao;
import com.jianzheng.server.po.SysRole;
import com.jianzheng.server.service.SysPermissionService;
import com.jianzheng.server.service.SysRoleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SysRole对应service实现
 *
 * @author tanghuang 2020年02月23日
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    public Page<SysRole> pageSearch(String keywords, Page page) {
        return this.getBaseMapper().pageSearch(keywords, page);
    }

}
