package com.gxa.xly2021.controller;

import com.gxa.xly2021.dto.ResultDto;
import com.gxa.xly2021.entity.Menu;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class MenuController {

    @PostMapping("/menu/list")
    public ResultDto getMenu(HttpSession session){
        List<Menu> menus=(List<Menu>) session.getAttribute("menus");
        return new ResultDto(200,"success",menus);
    }
}
