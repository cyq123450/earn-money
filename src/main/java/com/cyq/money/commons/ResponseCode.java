package com.cyq.money.commons;

import lombok.Getter;

/**
 * @author cyq
 * @description 响应码
 * @date 2020/9/12 15:15
 */
@Getter
public enum ResponseCode {

    SUCCESS(200), FAILED(500);

    // 响应码
    private Integer code;

    private ResponseCode() {

    }

    private ResponseCode(Integer code){
        this.code = code;
    }

}
