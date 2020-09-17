package com.cyq.money.taobao.service;

import com.cyq.money.vo.PageHelperParamVO;

import java.util.List;
import java.util.Map;

/**
 * @author cyq
 * @description 物料搜索服务层接口
 * @date 2020/9/16 21:47
 */
public interface MaterialService {

    public Map<String, Object> getProductByTitle(PageHelperParamVO paramVO) throws Exception;

    public List seachProjectByMaterialId(PageHelperParamVO paramVO) throws Exception;

}
