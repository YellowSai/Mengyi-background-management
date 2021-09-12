package com.jianzheng.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jianzheng.server.po.SysRole;
import org.apache.ibatis.annotations.Param;

/**
 * 表[sys_role]对应实体类
 *
 * @author tanghuang 2020年02月23日
 */
public interface SysRoleDao extends BaseMapper<SysRole> {

    /**
     * 分页搜索
     *
     * @param keywords
     * @param page
     * @return
     */
    Page<SysRole> pageSearch(@Param("keywords") String keywords, @Param("pg") Page page);

}