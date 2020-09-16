package com.cyq.money.taobao.communication;

import java.util.Map;

/**
 * @author cyq
 * @description 精选物料通信接口
 * @date 2020/9/16 21:54
 */
public interface OptimuMaterialCommunication {

    /**
     * 通过过滤条件获取精选物料数据
     * @param params
     * @return
     */
    public Map<String, Object> getOptimuMaterial(Map<String, String> params) throws Exception;

}
