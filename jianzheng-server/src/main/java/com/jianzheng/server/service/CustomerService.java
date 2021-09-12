package com.jianzheng.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jianzheng.server.po.Customer;

/**
 * Customer对应Service
 *
 * @author auto 2021年06月29日
 */
public interface CustomerService extends IService<Customer> {
    /**
     * 分页搜索
     *
     * @param keywords
     * @param page
     * @return
     */
    Page<Customer> pageSearch(String keywords, Page page , Integer searchValue);
}
