package com.gxa.xly2021.service;

import com.gxa.xly2021.dto.LayDto;
import com.gxa.xly2021.dto.ResultDto;
import com.gxa.xly2021.entity.Role;
import com.gxa.xly2021.param.EmplPageParam;

import java.util.List;

/**
 * 角色 表(Role)表服务接口
 *
 * @author makejava
 * @since 2021-08-16 15:25:49
 */
public interface RoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    Role queryById(Integer roleId);

    //回显角色数据
     ResultDto detal(Integer roleId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Role> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer roleId);


    //查询所有的角色

    LayDto getAllRoles(EmplPageParam param);


    ResultDto getAll();



}
