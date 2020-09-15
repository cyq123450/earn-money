package com.cyq.money.taobao.commons;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 淘宝信息
 */
@Component
public class TaoBaoConstant {
    @Value("${taobao.app-key}")
    private String appKey;

    @Value("${taobao.app-secret}")
    private String appSecret;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
