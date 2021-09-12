package com.jianzheng.server.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jianzheng.server.dao.CustomerDao;
import com.jianzheng.server.po.Customer;
import com.jianzheng.server.service.CustomerService;
import org.springframework.stereotype.Service;

/**
 * Customer对应service实现
 *
 * @author huangyuhan 2021年06月29日
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, Customer> implements CustomerService {
    @Override
    public Page<Customer> pageSearch(String keywords, Page page , Integer searchValue) {
        return this.getBaseMapper().pageSearch(keywords, page , searchValue);
    }

}
