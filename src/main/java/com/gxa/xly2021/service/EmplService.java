package com.gxa.xly2021.service;

import com.gxa.xly2021.dto.LayDto;
import com.gxa.xly2021.dto.ResultDto;
import com.gxa.xly2021.entity.Empl;

import com.gxa.xly2021.param.EmplPageParam;
import com.gxa.xly2021.param.EmplPwdParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 员工表(Empl)表服务接口
 *
 * @author makejava
 * @since 2021-08-14 16:06:29
 */
public interface EmplService {

    /**
     * 通过ID查询单条数据
     *
     * @param emplId 主键
     * @return 实例对象
     */
    Empl queryById(Integer emplId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Empl> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param empl 实例对象
     * @param roleIds 角色ID数组
     * @return 实例对象
     */
    ResultDto insert(Empl empl, String[] roleIds);

    /**
     * 修改数据
     *
     * @param empl 实例对象
     * @return 实例对象
     */
    Empl update(Empl empl);

    /**
     * 通过主键删除数据
     *
     * @param emplId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer emplId);


    /**
     * 员工列表数据
     * @return
     */
    LayDto listData(EmplPageParam param);


    ResultDto updatePwd(EmplPwdParam param);

    //修改状态
    ResultDto changeStatus(Empl empl, HttpSession session);

    //员工详情
    ResultDto detail(Integer emplId);

    //编辑员工
    ResultDto edit(Empl empl,String[] roleIds,HttpSession session);

    ResultDto delete(String[] ids);

}
