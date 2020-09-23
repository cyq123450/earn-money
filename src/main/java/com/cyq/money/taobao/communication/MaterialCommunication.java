package com.cyq.money.taobao.communication;

import com.cyq.money.vo.PageHelperParamVO;
import com.taobao.api.ApiException;

import java.util.List;
import java.util.Map;

/**
 * @author cyq
 * @description 物料类通信接口
 * @date 2020/9/16 21:54
 */
public interface MaterialCommunication {

    /**
     * 通过过滤条件获取精选物料数据
     * @param params
     * @return
     */
    public Map getOptimuMaterial(PageHelperParamVO params) throws Exception;

    /**
     * 通过关键字搜索物料池中的数据
     * @param params
     * @return
     * @throws ApiException
     */
    public Map getOptionalMaterial(PageHelperParamVO params) throws ApiException;

}
