package com.gxa.xly2021.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private Integer menuId;

    private String menuName;

    private String menuUrl;

    private Integer menuLevel;

    private Integer order;


    private List<Menu> subMenus=new ArrayList<>();
    public Menu(Integer menuId, String menuName, String menuUrl, Integer menuLevel,Integer order) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuLevel = menuLevel;
        this.order=order;
    }
}
