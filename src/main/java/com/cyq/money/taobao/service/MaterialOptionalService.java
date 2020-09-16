package com.cyq.money.taobao.service;

import java.util.Map;

/**
 * @author cyq
 * @description 物料搜索服务层接口
 * @date 2020/9/16 21:47
 */
public interface MaterialOptionalService {

    public Map<String, Object> getProductByTitle(String titleName) throws Exception;

}
