package com.gxa.xly2021.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 组织结构表(部门表)(Dept)实体类
 *
 * @author makejava
 * @since 2021-08-14 16:01:59
 */
@Data
public class Dept implements Serializable {
    private static final long serialVersionUID = -33982596044381500L;

    private Integer deptId;
    /**
     * 部门编码
     */
    private String deptCode;
    /**
     * 部门简称
     */
    private String deptSimpleName;
    /**
     * 部门全称
     */
    private String deptFullName;
    /**
     * 父级部门ID
     */
    private Integer parentId;
    /**
     * 部门的描述
     */
    private String deptDesc;
    /**
     * 部门备注
     */
    private String deptRemark;
    /**
     * 操作人的ID
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
     * 是否删除： 1 删除  0 未删除
     */
    private Integer isDeleted;


}
