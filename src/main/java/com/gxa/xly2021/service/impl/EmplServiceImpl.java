package com.gxa.xly2021.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxa.xly2021.dao.EmplRoleDao;
import com.gxa.xly2021.dto.LayDto;
import com.gxa.xly2021.dto.ResultDto;
import com.gxa.xly2021.entity.Empl;
import com.gxa.xly2021.dao.EmplDao;
import com.gxa.xly2021.param.EmplPageParam;
import com.gxa.xly2021.param.EmplPwdParam;
import com.gxa.xly2021.entity.EmplRole;
import com.gxa.xly2021.service.EmplService;
import com.gxa.xly2021.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * 员工表(Empl)表服务实现类
 *
 * @author makejava
 * @since 2021-08-14 16:06:29
 */
@Service("emplService")
public class EmplServiceImpl implements EmplService {
    @Resource
    private EmplDao emplDao;

    @Resource
    private EmplRoleDao emplRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param emplId 主键
     * @return 实例对象
     */
    @Override
    public Empl queryById(Integer emplId) {
        return this.emplDao.queryById(emplId);
    }



    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Empl> queryAllByLimit(int offset, int limit) {
        return this.emplDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param empl 实例对象
     * @param roleIds 角色ID数组
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultDto insert(Empl empl, String[] roleIds) {
        // 先处理员工自增的编号 =》 先找到最新的一个员工，获取员工编号=》自增
        empl.setEmplCode(getNewEmplCode());
        // 再处理员工的初始密码
        empl.setEmplPwd(MD5Util.MD55("111111"));
        // 再处理员工的添加时间
        empl.setCreateTime(new Timestamp(System.currentTimeMillis()));
        this.emplDao.insert(empl);

        // 添加员工和角色的关系数据
        // 一个数据是员工的ID
        // 一个数据是角色ID
        for (String roleId : roleIds) {
            emplRoleDao.insert(new EmplRole(empl.getEmplId(), Integer.valueOf(roleId)));
        }

        return new ResultDto(200, "添加成功！");

    }

    /**
     * 获取最新的员工编号的方法
     * @return
     */
    private String getNewEmplCode(){
        // 查询最新的员工
        Empl lastEmpl = this.emplDao.findLastEmpl();
        // 获取员工编号
        String oldEmplCode = lastEmpl.getEmplCode();
        // 先截取数字部分  EMPL_1001
        Integer code = Integer.valueOf(oldEmplCode.substring(5));
        // 自增
        code++;
        return "EMPL_"+code;
    }

    /**
     * 修改数据
     *
     * @param empl 实例对象
     * @return 实例对象
     */
    @Override
    public Empl update(Empl empl) {
        this.emplDao.update(empl);
        return this.queryById(empl.getEmplId());
    }

    /**
     * 通过主键删除数据
     *
     * @param emplId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer emplId) {
        return this.emplDao.deleteById(emplId) > 0;
    }



    /**
     * 员工列表数据
     * @return
     */
    @Override
    public LayDto listData(EmplPageParam param) {
        PageHelper.startPage(param.getPage(),param.getLimit());
        // 先去查询数据库
        List<Empl> empls = this.emplDao.findAll();
        //将结果集包装成pageInfo
        PageInfo<Empl> pageInfo=new PageInfo<>(empls);
        return new LayDto(pageInfo.getTotal(), empls);
    }

    /**
     * 修改密码
     *
     * @param param
     * @return
     */
    @Override
    public ResultDto updatePwd(EmplPwdParam param) {
        if (StringUtils.isEmpty(param.getNewPwd()) || StringUtils.isEmpty(param.getRePwd())) {
            return new ResultDto(1009, "新密码不能为空！");
        }
        // 先判断新密码和确认密码是否相等
        if (!param.getNewPwd().equals(param.getRePwd())) {
            return new ResultDto(1008, "两次密码不一致！");
        }
        // 先通过账号去查询数据
        Empl dbEmpl = this.emplDao.findByAccount(param.getEmplAccount());
        // 判断是否为空
        if (dbEmpl == null) {
            return new ResultDto(1006, "该用户不存在！");
        }
        // 判断旧密码是否正确
        if (!dbEmpl.getEmplPwd().equals(MD5Util.MD55(param.getOldPwd()))) {
            return new ResultDto(1007, "旧密码错误！");
        }

        // 更新密码
        this.emplDao.update(new Empl(dbEmpl.getEmplId(),
                MD5Util.MD55(param.getNewPwd()),
                new Timestamp(System.currentTimeMillis())));

        return new ResultDto(200, "修改成功！");
    }

    @Override
    public ResultDto changeStatus(Empl empl, HttpSession session) {
        empl.setOperatorId((Integer) session.getAttribute("emplId"));
        //设置修改时间
        empl.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        //直接更新操作
        this.emplDao.update(empl);
        return new ResultDto(200,"修改成功");
    }


    //查询员工的详细信息
    @Override
    public ResultDto detail(Integer emplId) {
        //验证id

        Empl empl=emplDao.queryById(emplId);
        return new ResultDto(200,"success",empl);
    }

    //编辑员工
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ResultDto edit(Empl empl, String[] roleIds, HttpSession session) {
        //处理操作员
        empl.setOperatorId((Integer)session.getAttribute("emplId"));
        empl.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        //更新
        this.emplDao.update(empl);
        //处理员工和角色的关系
        //先删除原来的关系
        this.emplRoleDao.delectByEmplId(empl.getEmplId());
        //再添加新的关系
        for (String roleId : roleIds) {
            this.emplRoleDao.insert(new EmplRole(empl.getEmplId(),Integer.valueOf(roleId)));
        }

        return new ResultDto(200,"编辑成功！");
    }

    @Override
    public ResultDto delete(String[] ids) {
        this.emplDao.deleteAllByIds(ids);
        return new ResultDto(200,"删除成功！");
    }
}