package com.gxa.xly2021.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxa.xly2021.dto.LayDto;
import com.gxa.xly2021.dto.ResultDto;
import com.gxa.xly2021.entity.Empl;
import com.gxa.xly2021.entity.Role;
import com.gxa.xly2021.dao.RoleDao;
import com.gxa.xly2021.param.EmplPageParam;
import com.gxa.xly2021.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色 表(Role)表服务实现类
 *
 * @author makejava
 * @since 2021-08-16 15:25:49
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Integer roleId) {
        return this.roleDao.queryById(roleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Role> queryAllByLimit(int offset, int limit) {
        return this.roleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role insert(Role role) {
        this.roleDao.insert(role);
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
        this.roleDao.update(role);
        return this.queryById(role.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer roleId) {
        return this.roleDao.deleteById(roleId) > 0;
    }

    @Override
    public LayDto getAllRoles(EmplPageParam param) {
        PageHelper.startPage(param.getPage(),param.getLimit());
        // 先去查询数据库
        List<Role> roles = this.roleDao.findAll();
        //将结果集包装成pageInfo
        PageInfo<Role> pageInfo=new PageInfo<>(roles);
        return new LayDto(pageInfo.getTotal(), roles);
    }

    @Override
    public ResultDto getAll() {
        List<Role> roles = this.roleDao.findAll();
        return new ResultDto(200, "success", roles);
    }

    @Override
    public ResultDto detal(Integer roleId) {
        Role role=roleDao.queryById(roleId);
        return new ResultDto(200,"success",role);
    }
}
