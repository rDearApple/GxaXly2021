package com.gxa.xly2021.controller;

import com.gxa.xly2021.dto.ResultDto;
import com.gxa.xly2021.entity.Empl;
import com.gxa.xly2021.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/page")
    public String loginPage(){
        return "login";
    }

    @ResponseBody
    @PostMapping("/do")
    public ResultDto loginDo(Empl empl,HttpSession session){
        return loginService.login(empl, session);
    }
}
