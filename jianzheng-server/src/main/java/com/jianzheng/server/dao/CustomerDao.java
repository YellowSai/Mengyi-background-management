package com.jianzheng.server.dao;

import com.jianzheng.server.po.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * 表[user]对应实体类
 *
 * @author auto 2021年06月29日
 */
public interface CustomerDao extends BaseMapper<Customer> {

    /**
     * 分页搜索
     *
     * @param keywords
     * @param page
     * @return
     */
    Page<Customer> pageSearch(@Param("keywords") String keywords,
                              @Param("pg") Page page,
                              @Param("searchValue") Integer searchValue);
}
