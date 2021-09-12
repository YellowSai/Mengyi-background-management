package com.jianzheng.server.service.impl;

import com.jianzheng.server.po.SysPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jianzheng.server.dao.SysPermissionDao;
import com.jianzheng.server.service.SysPermissionService;
import org.springframework.stereotype.Service;

/**
 * SysPermission对应Service实现
 *
 * @author tanghuang 2020年02月23日
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionDao, SysPermission> implements SysPermissionService {

    @Override
    public Page<SysPermission> pageSearch(String keywords,String searchValue, Page page) {
        return this.getBaseMapper().pageSearch(keywords,searchValue, page);
    }

}
