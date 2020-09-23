package com.cyq.money.commons;

import lombok.Getter;

/**
 * @author cyq
 * @description 响应信息
 * @date 2020/9/12 15:21
 */
@Getter
public enum  ResponseMsg {

    SUCCESS("操作成功"), FAILED("操作失败"), NULL_DATA("空数据"), PARAM_ERROR("参数错误");

    private String msg;

    private ResponseMsg() {

    }

    private ResponseMsg(String msg) {
        this.msg = msg;
    }

}
