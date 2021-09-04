package com.gxa.xly2021.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//layui的结果数据传输对象

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LayDto {
    private Integer code;
    private String msg;

    //数据的总的条数
    private Long count;

    private Object data;

    public LayDto(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public LayDto(Long count, Object data) {
        this.code=0;
        this.msg="";
        this.count = count;
        this.data = data;
    }
}
