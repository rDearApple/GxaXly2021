package com.gxa.xly2021.service;

import com.gxa.xly2021.dto.ResultDto;
import com.gxa.xly2021.entity.Permission;

import java.util.List;

/**
 * 权限 表(Permission)表服务接口
 *
 * @author makejava
 * @since 2021-08-17 10:09:24
 */
public interface PermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param permissionId 主键
     * @return 实例对象
     */
    Permission queryById(Integer permissionId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Permission> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission insert(Permission permission);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 实例对象
     */
    Permission update(Permission permission);

    /**
     * 通过主键删除数据
     *
     * @param permissionId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer permissionId);

    ResultDto getAllPermission();

}
