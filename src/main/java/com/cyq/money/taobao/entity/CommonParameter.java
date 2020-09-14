package com.cyq.money.taobao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author cyq
 * @description 淘宝公共参数实体类
 * @date 2020/9/12 16:01
 */
@Setter
@Getter
@NoArgsConstructor
public class CommonParameter {

    // API接口名称
    private String method;
    // TOP分配给应用的AppKey
    private String app_key;
    // 授权信息
    private String session;
    // 时间戳
    private String timestamp;
    // 响应格式
    private String format;
    // API协议版本
    private String v;
    // 合作伙伴身份标识
    private String partner_id;
    // 被调用的目标AppKey
    private String target_app_key;
    // 是否采用精简JSON返回格式
    private Boolean simplify;
    // 签名的摘要算法
    private String sign_method;
    // API输入参数签名结果
    private String sign;

}
