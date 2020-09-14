package com.cyq.money.taobao.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;

/**
 * @author cyq
 * @description 淘宝请求签名工具类
 * @date 2020/9/13 17:42
 */
public class SignTopRequest {

    private static Logger logger = LoggerFactory.getLogger(SignTopRequest.class);

    /**
     * 请求设置签名
     * @param params
     * @param secret
     * @param signMethod
     * @throws Exception
     */
    public static String signTopRequest(Map<String, String> params, String secret, String signMethod) throws Exception {
        // 第一步：检查参数是否已排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        // 第二步：把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        query.append(secret);

        for(String key : keys) {
            String value = params.get(key);
            if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
                query.append(key).append(value);
            }
        }

        query.append(secret);

        // 第三步：使用MD5进行加密
        String signVal = MD5Encoder.encode(query.toString().getBytes());

        logger.info("淘宝签名为:" + signVal);
        return signVal;
    }

}
