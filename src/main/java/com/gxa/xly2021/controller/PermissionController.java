package com.gxa.xly2021.controller;

import com.gxa.xly2021.dao.PermissionDao;
import com.gxa.xly2021.dto.ResultDto;
import com.gxa.xly2021.entity.Permission;
import com.gxa.xly2021.service.PermissionService;
import com.gxa.xly2021.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;
    //查询所有的角色（员工修改页面）

    @PostMapping("all/datas")
    @ResponseBody
    public ResultDto getAllPermissions(){
        return permissionService.getAllPermission();
    }
}
