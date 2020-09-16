package com.cyq.money.taobao.service.impl;

import com.cyq.money.taobao.communication.OptimuMaterialCommunication;
import com.cyq.money.taobao.service.MaterialOptionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cyq
 * @description 物料搜索服务层接口实现类
 * @date 2020/9/16 21:48
 */
@Service
public class MaterialOptionalServiceImpl implements MaterialOptionalService {

    @Autowired
    private OptimuMaterialCommunication optimuMaterialCommunication;

    @Override
    public Map<String, Object> getProductByTitle(String titleName) throws Exception {

        Map<String, String> params = new HashMap<>();
        params.put("q", titleName);
        return optimuMaterialCommunication.getOptimuMaterial(params);
    }


}
