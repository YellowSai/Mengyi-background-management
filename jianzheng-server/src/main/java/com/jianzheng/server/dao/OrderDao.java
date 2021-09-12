package com.jianzheng.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jianzheng.server.po.Customer;
import com.jianzheng.server.po.Order;
import org.apache.ibatis.annotations.Param;

/**
 * 表[order]对应实体类
 * 
 * @author auto 2021年07月01日 
 */
public interface OrderDao extends BaseMapper<Order> {
    /**
     * 分页搜索
     *
     * @param keywords
     * @param page
     * @return
     */
    Page<Order> pageSearch(@Param("keywords") String keywords,
                           @Param("pg") Page page,
                           @Param("startDate") java.util.Date searchStartDate,
                           @Param("endDate") java.util.Date searchEndDate,
                           @Param("customer") String searchValueCustomer,
                           @Param("status") String searchValueStatus
    );
}