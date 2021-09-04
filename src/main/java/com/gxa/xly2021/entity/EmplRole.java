package com.gxa.xly2021.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * 员工角色关系表(EmplRole)实体类
 *
 * @author makejava
 * @since 2021-08-16 15:13:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmplRole implements Serializable {
    private static final long serialVersionUID = -73680047098301490L;

    private Integer id;
    /**
     * 员工ID
     */
    private Integer emplId;
    /**
     * 角色的ID
     */
    private Integer roleId;


    public EmplRole(Integer emplId, Integer roleId) {
        this.emplId = emplId;
        this.roleId = roleId;
    }
}