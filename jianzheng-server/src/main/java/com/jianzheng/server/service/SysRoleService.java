package com.jianzheng.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jianzheng.server.po.SysRole;

/**
 * SysRole对应Service
 *
 * @author tanghuang 2020年02月23日
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页搜索
     *
     * @param keywords
     * @param page
     * @return
     */
    Page<SysRole> pageSearch(String keywords, Page page);
}
