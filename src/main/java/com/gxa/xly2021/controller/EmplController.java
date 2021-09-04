package com.gxa.xly2021.controller;


import com.gxa.xly2021.dto.LayDto;
import com.gxa.xly2021.dto.ResultDto;
import com.gxa.xly2021.entity.Empl;
import com.gxa.xly2021.param.EmplPageParam;
import com.gxa.xly2021.param.EmplPwdParam;
import com.gxa.xly2021.service.EmplService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 员工表(Empl)表控制层
 *
 * @author makejava
 * @since 2021-08-14 16:06:29
 */
@Controller
@RequestMapping("empl")
public class EmplController {
    /**
     * 服务对象
     */
    @Resource
    private EmplService emplService;

    /**
     * 员工的管理页面
     * @return
     */
    @GetMapping("list/page")
    public String emplListPage(){
        return "empl/empl-list";
    }


    /**
     * 员工的列表数据
     * @return
     */
    @PostMapping("list/data")
    @ResponseBody
    public LayDto emplListData(EmplPageParam param){
        return emplService.listData(param);
    }

    /**
     * 员工的添加页面
     * @return
     */
    @GetMapping("add/page")
    public String emplAddPage(){
        return "empl/empl-add";
    }

    /**
     * 员工的编辑页面
     * @return
     */
    @GetMapping("edit/page")
    public String emplEditPage(){
        return "empl/empl-edit";
    }


    /**
     * 添加的接口
     * @param empl
     * @param roleIds
     * @return
     */
    @PostMapping("add/do")
    @ResponseBody
    public ResultDto emplAdd(Empl empl,
                             @RequestParam(name = "roleIds[]") String[] roleIds){
        return emplService.insert(empl, roleIds);
    }

    //跳转修改密码
    @GetMapping("change/pwd/page")
    public String emplChangePwdPage(){
        return "empl/empl-change-pwd";
    }

    //修改密码
    @PostMapping("change/pwd/do")
    @ResponseBody
    public ResultDto emplChangePwd(EmplPwdParam param){
       return  emplService.updatePwd(param);
    }

    //修改状态
    @PostMapping("change/status/do")
    @ResponseBody
    public ResultDto emplChangeStatus(Empl empl, HttpSession session){
        return  emplService.changeStatus(empl,session);
    }


    //员工详情
    @PostMapping("detail")
    @ResponseBody
    public ResultDto detal(Integer emplId){
        return  emplService.detail(emplId);
    }

    /**
     * 编辑的接口
     * @param empl
     * @param roleIds
     * @return
     */
    @PostMapping("edit/do")
    @ResponseBody
    public ResultDto emplEdit(Empl empl, @RequestParam(name = "roleIds[]") String[] roleIds,HttpSession session){
        return emplService.edit(empl, roleIds,session);
    }

    //员工详情
    //ids:需要删除的id数组
    @PostMapping("delete")
    @ResponseBody
    public ResultDto delete(@RequestParam(name = "ids[]") String[] ids){

        return  emplService.delete(ids);
    }
}
