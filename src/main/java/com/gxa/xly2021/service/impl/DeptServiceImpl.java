package com.gxa.xly2021.service.impl;

import com.gxa.xly2021.dto.ResultDto;
import com.gxa.xly2021.entity.Dept;
import com.gxa.xly2021.dao.DeptDao;
import com.gxa.xly2021.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 组织结构表(部门表)(Dept)表服务实现类
 *
 * @author makejava
 * @since 2021-08-14 16:02:05
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptDao deptDao;

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    @Override
    public Dept queryById(Object deptId) {
        return this.deptDao.queryById(deptId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Dept> queryAllByLimit(int offset, int limit) {
        return this.deptDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dept 实例对象
     * @return 实例对象
     */
    @Override
    public Dept insert(Dept dept) {
        this.deptDao.insert(dept);
        return dept;
    }

    /**
     * 修改数据
     *
     * @param dept 实例对象
     * @return 实例对象
     */
    @Override
    public Dept update(Dept dept) {
        this.deptDao.update(dept);
        return this.queryById(dept.getDeptId());
    }

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object deptId) {
        return this.deptDao.deleteById(deptId) > 0;
    }

    @Override
    public ResultDto getAllDept() {
        List<Dept> depts =this.deptDao.findAll();
        return new ResultDto(200,"success",depts);
    }
}
