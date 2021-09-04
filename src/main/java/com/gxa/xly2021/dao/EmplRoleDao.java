package com.gxa.xly2021.dao;

import com.gxa.xly2021.entity.EmplRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工 角色关系表(EmplRole)表数据库访问层
 *
 * @author makejava
 * @since 2021-08-16 16:27:13
 */
public interface EmplRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EmplRole queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<EmplRole> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param emplRole 实例对象
     * @return 对象列表
     */
    List<EmplRole> queryAll(EmplRole emplRole);

    /**
     * 新增数据
     *
     * @param emplRole 实例对象
     * @return 影响行数
     */
    int insert(EmplRole emplRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<EmplRole> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<EmplRole> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<EmplRole> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<EmplRole> entities);

    /**
     * 修改数据
     *
     * @param emplRole 实例对象
     * @return 影响行数
     */
    int update(EmplRole emplRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    //通过员工id删除数据
    void delectByEmplId(@Param("emplId") Integer emplId);

}

