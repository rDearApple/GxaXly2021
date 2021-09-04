package com.gxa.xly2021.param;

import lombok.Data;

@Data
public class EmplPageParam {

    /**
     * 当前页
     */
    private Integer page = 1;
    /**
     * 页容量
     */
    private Integer limit = 10;


}
