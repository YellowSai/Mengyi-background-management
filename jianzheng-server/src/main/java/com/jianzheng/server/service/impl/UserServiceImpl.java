package com.jianzheng.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jianzheng.server.dao.UserDao;
import com.jianzheng.server.po.SysRole;
import com.jianzheng.server.po.User;
import com.jianzheng.server.service.UserService;
import org.springframework.stereotype.Service;

/**
 * User对应service实现
 * 
 * @author auto 2021年06月29日 
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao,User> implements UserService {

    @Override
    public Page<User> pageSearch(String keywords, String username, Page page) {
        return this.getBaseMapper().pageSearch(keywords, username, page);
    }
}