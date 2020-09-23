package com.cyq.money.jingdong.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author cyq
 * @description 京东相关配置类
 * @date 2020/9/16 22:00
 */
public class JingDongPropertiesReader {

    private static final Properties pros = new Properties();
    static {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream((JingDongPropertiesReader.class.getResource("/") + "conf/jingdong-config.properties").replace("file:/", ""));
            pros.load(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取京东配置文件的属性值
     * @param key
     * @return
     */
    public static String getPros(String key) {
        return pros.getProperty(key);
    }

}
