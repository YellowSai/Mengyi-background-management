package com.jianzheng.server.controller.admapi;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jianzheng.server.config.AppConfig;
import com.jianzheng.server.po.Order;
import com.jianzheng.server.service.OrderService;
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
import com.jianzheng.server.util.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/admapi/order")
public class OrderAdminController {
    @Autowired
    private OrderService orderService ;

    @Autowired
    private AppConfig appConfig;

    @ApiOperation(value = "获取客户列表", notes = "")
    @GetMapping("/get")

    public R pageOrder(
            @ApiParam(value = "搜索关键词")
            @RequestParam(required = false, defaultValue = "")
                    String keywords,
                    Page page,
                    String searchValueCustomer,
                    String searchValueStatus,
                    String searchStartDate,
                    String searchEndDate) {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date searchStartDateData;
        Date searchEndDateData;
        try {
            searchStartDateData = time.parse(time.format(Long.valueOf(searchStartDate)));
            searchEndDateData = time.parse(time.format(Long.valueOf(searchEndDate)));
        } catch (Exception e) {
            searchStartDateData = null;
            searchEndDateData = null;
        }
        Page<Order> pageData = orderService.pageSearch(
            keywords,
            page,
            searchStartDateData,
            searchEndDateData,
            searchValueCustomer,
            searchValueStatus);
        Map[] returnRecords = new Map[pageData.getRecords().size()];
        for (int i = 0 ; i <  returnRecords.length ; i++ ){
            Map <String,Object> map=new HashMap<>();
            Order record = pageData.getRecords().get(i);
            map.put("id",record.getId());
            map.put("customer",record.getCustomer());
            map.put("startTime",record.getStartTime());
            map.put("estimatedTime",record.getEstimatedTime());
            map.put("completeTime",record.getCompleteTime());
            map.put("cost",record.getCost());
            map.put("status",record.getStatus());
            map.put("producer",record.getProducer());
            returnRecords[i] = map;
        }
        Map <String,Object> returnList = util.ResponseLoading(returnRecords,pageData.getCurrent(),pageData.getPages(),pageData.getSize(),pageData.getTotal());
        return R.data(returnList);
    }

    @ApiOperation(value = "保存订单", notes = "")
    @PostMapping("/save")
    public R add(@RequestBody @Validated Order order, @RequestHeader ("authToken") String token) {
        Claims userTokenParse = JWTUtil.parseJWT(appConfig.getJwtSecret(),token);
        String mobile = userTokenParse.get("mobile").toString();

        if(order.getCompleteTime() == null)
            order.setStatus(0);
        else
            order.setStatus(1);
        if (order.getId() > 0) {
            order.setUpdateTime(DateUtil.date());
            order.setLastOperator(mobile);
            orderService.updateById(order);
            order.setExist(1);
            return R.ok("更新数据成功");
        } else {
//            QueryWrapper<Order> query = new QueryWrapper<>();
//            query.eq("id", order.getCustomer()).ne("exist",2);
//            Map<String, Object> orderQuery = orderService.getMap(query);
//            if(orderQuery != null){
//                return R.error("已存在该客户");
//            }
            order.setCustomer(Convert.notNull(order.getCustomer()));
            order.setProducer(Convert.notNull(order.getProducer()));
            order.setUpdateTime(DateUtil.date());
            order.setLastOperator(mobile);
            order.setExist(1);
            orderService.save(order);
            return R.ok("添加数据成功");
        }
    }

//    @ApiOperation(value = "添加客户", notes = "")
//    @PostMapping("/add")
//
//    public R add(@RequestBody @Validated OrderDTO orderDTO, @RequestHeader ("authToken") String token) {
//        QueryWrapper<Order> query = new QueryWrapper<>();
//        query.eq("id", orderDTO.getId()).ne("exist",2);
//        Map<String, Object> orderQuery = orderService.getMap(query);
//        if(orderQuery != null){
//            return R.error("订单已存在");
//        }
//
//        Claims userTokenParse = JWTUtil.parseJWT(appConfig.getJwtSecret(),token);
//        String mobile = userTokenParse.get("mobile").toString();
//
//        Order order = new Order();
//        order.setCustomer(orderDTO.getCustomer());
//        order.setStartTime(orderDTO.getStartTime());
//        order.setEstimatedTime(orderDTO.getEstimatedTime());
//        order.setCompleteTime(orderDTO.getCompleteTime());
//        order.setCost(orderDTO.getCost());
//        if(orderDTO.getCompleteTime().getTime() >= DateUtil.date().getTime())
//            order.setStatus(0);
//        else
//            order.setStatus(1);
//        order.setProducer(orderDTO.getProducer());
//
//        order.setUpdateTime(DateUtil.date());
//        order.setLastOperator(mobile);
//        order.setExist(1);
//        orderService.save(order);
//        return R.ok();
//    }

    @ApiOperation(value = "删除客户", notes = "")
    @DeleteMapping("/delete")

    public R delete(@RequestBody @Validated Order order, @RequestHeader ("authToken") String token) {
        QueryWrapper<Order> query = new QueryWrapper<>();
        query.eq("id", order.getId());;
        Order orderQuery = orderService.getOne(query);

        if(orderQuery.getExist() == 2)
            return R.error("删除失败，该条目已被删除");

        Claims userTokenParse = JWTUtil.parseJWT(appConfig.getJwtSecret(),token);
        String mobile = userTokenParse.get("mobile").toString();

        order.setUpdateTime(DateUtil.date());
        order.setLastOperator(mobile);
        order.setExist(2);
        orderService.update(order,query);
        return R.ok();
    }

//    @ApiOperation(value = "更新客户", notes = "")
//    @PutMapping("/update")
//
//    public R update(@RequestBody @Validated OrderDTO orderDTO, @RequestHeader ("authToken") String token) {
//        QueryWrapper<Order> query = new QueryWrapper<>();
//        query.eq("id", orderDTO.getId());
//
//        Date date = new Date();
//        Claims userTokenParse = JWTUtil.parseJWT(appConfig.getJwtSecret(),token);
//        String mobile = userTokenParse.get("mobile").toString();
//
//        Order order = new Order();
//        order.setCustomer(orderDTO.getCustomer());
//        order.setStartTime(orderDTO.getStartTime());
//        order.setEstimatedTime(orderDTO.getEstimatedTime());
//        order.setCompleteTime(orderDTO.getCompleteTime());
//        order.setCost(orderDTO.getCost());
//        order.setProducer(orderDTO.getProducer());
//        if(orderDTO.getCompleteTime().getTime() >= date.getTime())
//            order.setStatus(0);
//        else
//            order.setStatus(1);
//
//        order.setUpdateTime(DateUtil.date());
//        order.setLast_operator(mobile);
//        order.setExist(1);
//        orderService.update(order,query);
//        return R.ok();
//    }
}
