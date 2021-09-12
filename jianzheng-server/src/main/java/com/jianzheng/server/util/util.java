package com.jianzheng.server.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jianzheng.server.po.Customer;

import java.util.HashMap;
import java.util.Map;

public class util {
    public static Map<String,Object> ResponseLoading(Map[] returnRecords,long current,long pages,long size,long total){
        Map<String,Object> returnList = new HashMap<>();
        returnList.put("current",current);
        returnList.put("pages",pages);
        returnList.put("records",returnRecords);
        returnList.put("size",size);
        returnList.put("total",total);
        return returnList;
    }
}
