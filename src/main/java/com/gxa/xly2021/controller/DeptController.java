package com.gxa.xly2021.controller;

import com.gxa.xly2021.dto.ResultDto;
import com.gxa.xly2021.entity.Dept;
import com.gxa.xly2021.service.DeptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 组织结构表(部门表)(Dept)表控制层
 *
 * @author makejava
 * @since 2021-08-14 16:02:06
 */
@Controller
@RequestMapping("dept")
public class DeptController {
    /**
     * 服务对象
     */
    @Resource
    private DeptService deptService;


    //获取所有的部门数据
    @PostMapping("/all/data")
    @ResponseBody
    public ResultDto getAllDept(){
        return  deptService.getAllDept();
    }


}
