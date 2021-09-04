package com.gxa.xly2021.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 角色 表(Role)实体类
 *
 * @author makejava
 * @since 2021-08-16 15:25:49
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -73726330965408982L;

    //表示这个角色所拥有的权限
    private List<Permission> permissions;


    private Integer roleId;

    private  Integer isSuper;

    private String roleCode;
    /**
     * 角色的名 字
     */
    private String roleName;
    /**
     * 部门的ID
     */
    private Integer deptId;
    /**
     * 角色的备 注信息
     */
    private String roleRemark;
    /**
     * 操作人ID
     */
    private Integer operatorId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 0 未删除， 1 删 除
     */
    private Integer isDeleted;


}
