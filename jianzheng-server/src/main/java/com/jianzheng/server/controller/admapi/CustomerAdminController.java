package com.jianzheng.server.controller.admapi;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jianzheng.server.config.AppConfig;
import com.jianzheng.server.po.Customer;
import com.jianzheng.server.service.CustomerService;
import com.jianzheng.server.util.util;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.core.R;
import net.scode.commons.util.Convert;
import net.scode.commons.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/admapi/customer")
public class CustomerAdminController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AppConfig appConfig;

    @ApiOperation(value = "获取客户列表", notes = "")
    @GetMapping("/get")

    public R pageCustomer(@ApiParam(value = "搜索关键词") @RequestParam(required = false, defaultValue = "") String keywords , Integer type , Page page ,Integer searchValue) {
        try {
            Page<Customer> pageData = customerService.pageSearch(keywords, page ,searchValue);
            Map[] returnRecords = new Map[pageData.getRecords().size()];
            for (int i = 0 ; i < returnRecords.length ; i++ ){
                Map <String,Object> map=new HashMap<>();
                Customer record = pageData.getRecords().get(i);
                map.put("id",record.getId());
                map.put("customer",record.getCustomer());
                if(type == 1)
                    map.put("director",record.getDirector());
                returnRecords[i] = map;
            }
            Map <String,Object> returnList = util.ResponseLoading(returnRecords,pageData.getCurrent(),pageData.getPages(),pageData.getSize(),pageData.getTotal());
            return R.data(returnList);
        }
        catch (Exception e){
            log.debug(String.valueOf(e));
            return R.error("发生内部错误");
        }
    }

    @ApiOperation(value = "保存客户", notes = "id大于0表示修改")
    @PostMapping("/save")
    public R save(@RequestBody Customer customer, @RequestHeader ("authToken") String token) {
        try{
            Claims userTokenParse = JWTUtil.parseJWT(appConfig.getJwtSecret(),token);
            String mobile = userTokenParse.get("mobile").toString();
            if (customer.getId() > 0) {
                customer.setUpdateTime(DateUtil.date());
                customer.setLastOperator(mobile);
                customer.setExist(1);
                customerService.updateById(customer);
                return R.ok("更新数据成功");
            } else {
                QueryWrapper<Customer> query = new QueryWrapper<>();
                query.eq("customer", customer.getCustomer()).ne("exist",2);
                Map<String, Object> customerQuery = customerService.getMap(query);
                if(customerQuery != null){
                    return R.error("已存在该客户");
                }
                customer.setCustomer(Convert.notNull(customer.getCustomer()));
                customer.setDirector(Convert.notNull(customer.getDirector()));
                customer.setAddTime(DateUtil.date());
                customer.setUpdateTime(DateUtil.date());
                customer.setLastOperator(mobile);
                customer.setExist(1);
                customerService.save(customer);
                return R.ok("添加数据成功");
            }
        }
        catch (Exception e){
            log.debug(String.valueOf(e));
            return R.error("发生内部错误");
        }
    }

    @ApiOperation(value = "删除客户", notes = "")
    @DeleteMapping("/delete")

    public R delete(@RequestBody @Validated Customer customer , @RequestHeader ("authToken") String token) {
        try {
            QueryWrapper<Customer> query = new QueryWrapper<>();
            query.eq("id", customer.getId());;
            Customer customerQuery = customerService.getOne(query);

            if(customerQuery.getExist() == 2)
                return R.error("删除失败，该条目已被删除");

            Claims userTokenParse = JWTUtil.parseJWT(appConfig.getJwtSecret(),token);
            String mobile = userTokenParse.get("mobile").toString();

            customer.setUpdateTime(DateUtil.date());
            customer.setLastOperator(mobile);
            customer.setExist(0);
            customerService.update(customer,query);
            return R.ok();
        }
        catch (Exception e){
            log.debug(String.valueOf(e));
            return R.error("发生内部错误");
        }

    }
}
