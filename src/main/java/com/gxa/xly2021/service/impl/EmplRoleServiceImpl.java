package com.gxa.xly2021.service.impl;

import com.gxa.xly2021.entity.EmplRole;
import com.gxa.xly2021.dao.EmplRoleDao;
import com.gxa.xly2021.service.EmplRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工 角色关系表(EmplRole)表服务实现类
 *
 * @author makejava
 * @since 2021-08-16 16:27:13
 */
@Service("emplRoleService")
public class EmplRoleServiceImpl implements EmplRoleService {
    @Resource
    private EmplRoleDao emplRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public EmplRole queryById(Integer id) {
        return this.emplRoleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<EmplRole> queryAllByLimit(int offset, int limit) {
        return this.emplRoleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param emplRole 实例对象
     * @return 实例对象
     */
    @Override
    public EmplRole insert(EmplRole emplRole) {
        this.emplRoleDao.insert(emplRole);
        return emplRole;
    }

    /**
     * 修改数据
     *
     * @param emplRole 实例对象
     * @return 实例对象
     */
    @Override
    public EmplRole update(EmplRole emplRole) {
        this.emplRoleDao.update(emplRole);
        return this.queryById(emplRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.emplRoleDao.deleteById(id) > 0;
    }
}
