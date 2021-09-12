package com.jianzheng.server.service;

import com.jianzheng.server.po.SysPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * SysPermission对应Service
 *
 * @author tanghuang 2020年02月22日
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 分页搜索
     *
     * @param keywords
     * @param page
     * @return
     */
    Page<SysPermission> pageSearch(String keywords,String searchValue, Page page);


}