package com.gxa.xly2021.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 权限 表(Permission)实体类
 *
 * @author makejava
 * @since 2021-08-17 10:09:23
 */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 674239455972787117L;

    private Integer permissionId;
    /**
     * 权限 的名字
     */
    private String permissionName;
    /**
     * 权限的url
     */
    private String permissionUrl;
    /**
     * 1 是菜单 0 不是菜单
     */
    private Integer isMenu;
    /**
     * 权限的等 级
     */
    private Integer permissionLevel;
    /**
     * 父级ID
     */
    private Integer parentId;


    //显示优先级
    private Integer permissionOrder;


}
