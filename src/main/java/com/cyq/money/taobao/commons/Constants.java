package com.cyq.money.taobao.commons;

import lombok.Getter;

/**
 * @author cyq
 * @description 淘宝常数类
 * @date 2020/9/13 18:08
 */
@Getter
public enum Constants {

    SIGN_METHOD_MD5("md5"), SIGN_METHOD_HMAC("hmac"),
    CHARSET_UTF8("UTF-8"),

    淘宝客_推广者_物料精选("taobao.tbk.dg.optimus.material")
    ;

    private String val;

    private Constants(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }
}
