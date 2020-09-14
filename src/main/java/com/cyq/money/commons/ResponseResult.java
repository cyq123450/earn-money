package com.cyq.money.commons;

import lombok.Getter;
import lombok.Setter;

/**
 * @author cyq
 * @description 响应实体类
 * @date 2020/9/12 15:09
 */
@Setter
@Getter
public class ResponseResult<T> {

    // 响应码
    private Integer code;

    // 响应消息
    private String msessage;

    // 响应数据
    private T datas;

    private ResponseResult(){

    }

    private ResponseResult(Integer code, String message, T datas){
        this.code = code;
        this.msessage = message;
        this.datas = datas;
    }

    public static <T> ResponseResult<T> success() {
        return success(null);
    }

    public static <T> ResponseResult<T> success(T datas) {
        return success(ResponseMsg.SUCCESS.getMsg(), datas);
    }

    public static <T> ResponseResult<T> success(String message, T datas) {
        return success(ResponseCode.SUCCESS.getCode(), message, datas);
    }

    public static <T> ResponseResult<T> success(Integer code, String message, T datas) {
        return new ResponseResult<T>(code, message, datas);
    }

    public static <T> ResponseResult<T> failed() {
        return failed(ResponseMsg.FAILED.getMsg());
    }

    public static <T> ResponseResult<T> failed(String message) {
        return failed(ResponseCode.FAILED.getCode(), message);
    }

    public static <T> ResponseResult<T> failed(Integer code, String message) {
        return new ResponseResult<T>(code, message, null);
    }

}
