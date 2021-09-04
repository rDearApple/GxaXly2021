package com.gxa.xly2021.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 员工表(Empl)实体类
 *
 * @author makejava
 * @since 2021-08-14 16:06:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empl implements Serializable {
    private static final long serialVersionUID = 643026436100665482L;

    private Integer emplId;
    /**
     * 员工编码
     */
    private String emplCode;
    /**
     * 员工姓名
     */
    private String emplName;
    /**
     * 员工登录账号
     */
    private String emplAccount;
    /**
     * 员工的登录密码
     */
    private String emplPwd;
    /**
     * 手机号
     */
    private Long emplPhone;
    /**
     * 性别： 1 男  2 女
     */
    private Integer emplGender;
    /**
     * 状态 1 正常  0 禁用
     */
    private Integer emplStatus;
    /**
     * 所属部门ID
     */
    private Integer deptId;
    /**
     * 操作人ID
     */
    private Integer operatorId;
    /**
     * 备注信息
     */
    private String emplRemark;
    /**
     * 最后登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp lastLoginTime;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Integer isDeleted;

    //员工所属的部门
    private Dept dept;

    //员工拥有的角色
    private List<Role> roles;

    public Empl(Integer emplId, Timestamp lastLoginTime) {
        this.emplId = emplId;
        this.lastLoginTime = lastLoginTime;
    }

    public Empl(Integer emplId, String emplPwd, Timestamp updateTime) {
        this.emplId = emplId;
        this.emplPwd = emplPwd;
        this.updateTime = updateTime;
    }
}
