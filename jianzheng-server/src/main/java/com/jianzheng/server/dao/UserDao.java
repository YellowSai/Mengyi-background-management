package com.jianzheng.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jianzheng.server.po.SysRole;
import com.jianzheng.server.po.User;
import org.apache.ibatis.annotations.Param;

/**
 * 表[user]对应实体类
 * 
 * @author auto 2021年06月29日 
 */
public interface UserDao extends BaseMapper<User> {
    /**
     * 分页搜索
     *
     * @param keywords
     * @param page
     * @return
     */
    Page<User> pageSearch(@Param("keywords") String keywords,@Param("username") String username, @Param("pg") Page page);
}