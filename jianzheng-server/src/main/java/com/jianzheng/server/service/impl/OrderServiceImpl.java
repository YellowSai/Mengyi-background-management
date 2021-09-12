package com.jianzheng.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jianzheng.server.dao.OrderDao;
import com.jianzheng.server.po.Customer;
import com.jianzheng.server.po.Order;
import com.jianzheng.server.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * Order对应service实现
 * 
 * @author auto 2021年07月01日 
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao,Order> implements OrderService {
    @Override
    public Page<Order> pageSearch(String keywords,
                                  Page page,
                                  java.util.Date searchStartDate,
                                  java.util.Date searchEndDate,
                                  String searchValueCustomer,
                                  String searchValueStatus) {
        return this.getBaseMapper().pageSearch(
                keywords,
                page,
                searchStartDate,
                searchEndDate,
                searchValueCustomer,
                searchValueStatus);
    }
}