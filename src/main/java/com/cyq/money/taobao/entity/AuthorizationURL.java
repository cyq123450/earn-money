package com.cyq.money.taobao.entity;

/**
 * @author cyq
 * @description 授权URL实体类
 * @date 2020/9/13 19:36
 */
public class AuthorizationURL {

    // 等同于appkey
    private String client_id;
    // 授权类型
    private String response_type;
    // 回调地址参数
    private String redirect_uri;
    // 维持应用的状态
    private String state;
    // 页面样式
    private String view;

}
