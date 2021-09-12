package com.jianzheng.server;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jianzheng.server.po.Order;
import com.jianzheng.server.po.SysPermission;
import com.jianzheng.server.po.SysRole;
import com.jianzheng.server.po.User;
import com.jianzheng.server.service.SysPermissionService;
import com.jianzheng.server.service.SysRoleService;
import com.jianzheng.server.service.UserService;
import com.jianzheng.server.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import net.scode.commons.constant.Consts;
import net.scode.commons.core.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Test
    public void addUser() {
        //1622603856168
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String test = time.format(Long.valueOf("1622603856168"));
        User user = new User();
        user.setMobile("13801380138");
        user.setPassword(SecureUtil.md5("123456"));
        user.setName("管理员");

        user.setNickname("admin");
        user.setAvatar("");
        user.setCreateTime(DateUtil.date());
        userService.save(user);
    }

    @Test
    public void user() {
        QueryWrapper<SysRole> querys = new QueryWrapper<>();
        querys.eq("role_name","管理员");
        SysRole sysRole = sysRoleService.getOne(querys);
        System.out.println(sysRole);

        List<SysRole> list = sysRoleService.list();
        for (SysRole sysRole1 : list){
            System.out.println(sysRole1);
        }
    }


}
