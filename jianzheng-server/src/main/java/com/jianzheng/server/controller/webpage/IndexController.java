package com.jianzheng.server.controller.webpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * 首页
 *
 * @author tanghuang 2020年02月21日
 */
@Controller
public class IndexController {

    /**
     * 首页测试
     *
     * @return
     */
    @RequestMapping("/index")
    public String index(HashMap<String, Object> map) {
        map.put("message", "测试信息");
        return "index";
    }

}
