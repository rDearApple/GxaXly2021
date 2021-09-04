package com.gxa.xly2021.param;

import lombok.Data;

@Data
public class EmplPwdParam {
    private String emplAccount;

    private String oldPwd;

    private String newPwd;

    private String rePwd;
}
