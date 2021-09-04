package com.gxa.xly2021.controller;

import com.gxa.xly2021.dto.LayDto;
import com.gxa.xly2021.dto.ResultDto;
import com.gxa.xly2021.entity.Role;
import com.gxa.xly2021.param.EmplPageParam;
import com.gxa.xly2021.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色 表(Role)表控制层
 *
 * @author makejava
 * @since 2021-08-16 15:25:49
 */
@Controller
@RequestMapping("role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;


    //查询所有的角色（角色管理页面）
    @PostMapping("all/data")
    @ResponseBody
    public LayDto getAllRoles(EmplPageParam param)
    {
        return roleService.getAllRoles(param);

    }
    //查询所有的角色（员工修改页面）
    @PostMapping("all/datas")
    @ResponseBody
    public ResultDto getAllRoles(){
        return roleService.getAll();
    }

    //角色管理页面
    @GetMapping("/list/page")
    public String emplListPage(){
        return "role/role-list";
    }


    //角色授权页面
    @GetMapping("/empower/page")
    public String emplEditPage(){
        return "role/role-empower";
    }

    //角色详情
    @PostMapping("detail")
    @ResponseBody
    public ResultDto detal(Integer roleId){
        return  roleService.detal(roleId);
    }

    //查询所有的权限
    //查询所有的角色
//    @PostMapping("all/data")
//    @ResponseBody
//    public LayDto getAllRoles(EmplPageParam param)
//    {
//        return roleService.getAllRoles(param);
//
//    }



//    //角色的列表数据
//    @PostMapping("list/data")
//    @ResponseBody
//    public LayDto roleListData(EmplPageParam param){
//
//        return emplService.listData(param);
//    }


}
