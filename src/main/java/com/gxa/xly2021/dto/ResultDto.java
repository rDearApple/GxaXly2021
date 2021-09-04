package com.gxa.xly2021.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {
    private Integer code;
    private String msg;
    private Object data;

    public ResultDto(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
