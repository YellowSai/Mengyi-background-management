package com.jianzheng.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jianzheng.server.po.SysRole;
import com.jianzheng.server.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User对应Service
 * 
 * @author auto 2021年06月29日 
 */
public interface UserService extends IService<User> {
    /**
     * 分页搜索
     *
     * @param keywords
     * @param page
     * @return
     */
    Page<User> pageSearch(String keywords,String username, Page page);
}