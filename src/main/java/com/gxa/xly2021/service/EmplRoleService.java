package com.gxa.xly2021.service;

import com.gxa.xly2021.entity.EmplRole;

import java.util.List;

/**
 * 员工 角色关系表(EmplRole)表服务接口
 *
 * @author makejava
 * @since 2021-08-16 16:27:13
 */
public interface EmplRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EmplRole queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<EmplRole> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param emplRole 实例对象
     * @return 实例对象
     */
    EmplRole insert(EmplRole emplRole);

    /**
     * 修改数据
     *
     * @param emplRole 实例对象
     * @return 实例对象
     */
    EmplRole update(EmplRole emplRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
