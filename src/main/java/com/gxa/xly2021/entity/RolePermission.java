package com.gxa.xly2021.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色 权限关系表(RolePermission)实体类
 *
 * @author makejava
 * @since 2021-08-17 10:09:54
 */
@Data
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 343437664586874341L;

    private Integer id;
    /**
     * 角色ID
     */
    private Integer roleId;
    /**
     * 权限ID
     */
    private Integer permissionId;


}
