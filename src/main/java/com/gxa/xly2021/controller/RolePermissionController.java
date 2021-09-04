package com.gxa.xly2021.controller;

import com.gxa.xly2021.dto.ResultDto;
import com.gxa.xly2021.service.RolePermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("role-permission")
public class RolePermissionController {

    @Resource
    private RolePermissionService rolePermissionService;

    @PostMapping("detail")
    @ResponseBody
    public ResultDto detal(Integer roleId){
        return rolePermissionService.rolePermissionDetal(roleId);
    }
}
