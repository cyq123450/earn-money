package com.cyq.money.jingdong.commons;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author cyq
 * @description 京东相关配置类
 * @date 2020/9/16 22:00
 */
@Setter
@Getter
@Component
@PropertySource(value = {"classpath:conf/jingdong-config.properties"})
@ConfigurationProperties(prefix = "jingdong.pros")
public class JingDongPropertiesReader {

    private Map<String, String> map;

    /**
     * 获取京东配置文件的属性值
     * @param key
     * @return
     */
    public String getVal(String key) {
        return map.get(key);
    }
}
