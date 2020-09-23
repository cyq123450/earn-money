package com.cyq.money.taobao.commons;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author cyq
 * @description 淘宝相关配置类
 * @date 2020/9/16 22:00
 */
@Setter
@Getter
@Component
@PropertySource(value = {"classpath:conf/taobao-config.properties"})
@ConfigurationProperties(prefix = "taobao.pros")
public class TaoBaoPropertiesReader {

    private Map<String, String> map;

    /**
     * 获取淘宝配置文件的属性值
     * @param key
     * @return
     */
    public String getVal(String key) {
        return map.get(key);
    }

}
