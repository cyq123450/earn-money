package com.cyq.money.taobao.service.impl;

import com.cyq.money.taobao.commons.Constants;
import com.cyq.money.taobao.commons.TaoBaoConstant;
import com.cyq.money.taobao.service.CommodityCategoryService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgOptimusMaterialRequest;
import com.taobao.api.request.TbkShopGetRequest;
import com.taobao.api.response.TbkDgOptimusMaterialResponse;
import com.taobao.api.response.TbkShopGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cyq
 * @description 商品类目服务层接口实现类
 * @date 2020/9/14 21:55
 */
@Service
public class CommodityCategoryServiceImpl implements CommodityCategoryService {

    @Autowired
    private TaoBaoConstant taoBaoConstant;

    private static final String ALIBABA_WHOLESALE_CATEGORY_GET = "alibaba.wholesale.category.get";

    @Override
    public String getCategory() {


        try {
        TaobaoClient client = new DefaultTaobaoClient("https://eco.taobao.com/router/rest", taoBaoConstant.getAppKey(), taoBaoConstant.getAppSecret());
        TbkDgOptimusMaterialRequest req = new TbkDgOptimusMaterialRequest();
        req.setPageSize(20L);
        req.setAdzoneId(123L);
        req.setPageNo(1L);
        req.setMaterialId(34616L);
        TbkDgOptimusMaterialResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
        String body = rsp.getBody();
        return body;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }


}
