package com.jianzheng.server.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jianzheng.server.po.Customer;
import com.jianzheng.server.po.Order;

/**
 * Order对应Service
 * 
 * @author auto 2021年07月01日 
 */
public interface OrderService extends IService<Order> {
    /**
     * 分页搜索
     *
     * @param keywords
     * @param page
     * @return
     */
    Page<Order> pageSearch(String keywords,
                           Page page,
                           java.util.Date searchStartDate,
                           java.util.Date searchEndDate,
                           String searchValueCustomer,
                           String searchValueStatus);
}