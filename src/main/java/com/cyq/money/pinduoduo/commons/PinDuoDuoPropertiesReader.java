package com.cyq.money.pinduoduo.commons;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author cyq
 * @description 拼多多相关配置类
 * @date 2020/9/16 22:00
 */
@Setter
@Getter
@Component
@PropertySource(value = {"classpath:conf/pinduoduo-config.properties"})
@ConfigurationProperties(prefix = "pinduoduo.pros")
public class PinDuoDuoPropertiesReader {

    private Map<String, String> map;

    /**
     * 获取拼多多配置文件的属性值
     * @param key
     * @return
     */
    public String getVal(String key) {
        return map.get(key);
    }
}
