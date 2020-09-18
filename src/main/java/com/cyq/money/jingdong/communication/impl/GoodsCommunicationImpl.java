package com.cyq.money.jingdong.communication.impl;

import com.cyq.money.jingdong.commons.JingDongPropertiesReader;
import com.cyq.money.jingdong.communication.GoodsCommunication;
import com.cyq.money.vo.PageHelperParamVO;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import jd.union.open.category.goods.get.request.CategoryReq;
import jd.union.open.category.goods.get.request.UnionOpenCategoryGoodsGetRequest;
import jd.union.open.category.goods.get.response.CategoryResp;
import jd.union.open.category.goods.get.response.UnionOpenCategoryGoodsGetResponse;
import jd.union.open.goods.jingfen.query.request.JFGoodsReq;
import jd.union.open.goods.jingfen.query.request.UnionOpenGoodsJingfenQueryRequest;
import jd.union.open.goods.jingfen.query.response.JFGoodsResp;
import jd.union.open.goods.jingfen.query.response.UnionOpenGoodsJingfenQueryResponse;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * 商品类信息对接京东服务接口实现类
 */
@Service
public class GoodsCommunicationImpl implements GoodsCommunication {

    private static final String SERVER_URL = JingDongPropertiesReader.getPros("jingdong.url");
    private static final String APP_KEY = JingDongPropertiesReader.getPros("jingdong.app-key");
    private static final String APP_SECRET = JingDongPropertiesReader.getPros("jingdong.app-secret");

    public CategoryResp[] getGoodsFirstCategory() throws JdException {

        JdClient client = new DefaultJdClient(SERVER_URL,null, APP_KEY, APP_SECRET);
        UnionOpenCategoryGoodsGetRequest request = new UnionOpenCategoryGoodsGetRequest();
        CategoryReq req = new CategoryReq();
        req.setParentId(0);
        req.setGrade(0);
        request.setReq(req);
        UnionOpenCategoryGoodsGetResponse response = client.execute(request);

        CategoryResp[] data = response.getData();

        return data;
    }


    public JFGoodsResp[] getJingFengGoods(PageHelperParamVO paramVO) throws JdException {
        JdClient client=new DefaultJdClient(SERVER_URL,null, APP_KEY, APP_SECRET);
        UnionOpenGoodsJingfenQueryRequest request=new UnionOpenGoodsJingfenQueryRequest();
        JFGoodsReq goodsReq=new JFGoodsReq();

        // 过来参数设置
        Map<String, String> params = paramVO.getParams();
        for(String key : params.keySet()) {
            if (Objects.isNull(key)) {
                continue;
            }

            switch (key) {
                case "eliteId" : goodsReq.setEliteId(Integer.valueOf(params.get(key))); break;
                default: break;
            }
        }

        // 设置分页数据
        goodsReq.setPageIndex((int)paramVO.getPageNum());
        goodsReq.setPageSize((int)paramVO.getPageSize());

        request.setGoodsReq(goodsReq);
        UnionOpenGoodsJingfenQueryResponse response = client.execute(request);
        JFGoodsResp[] data = response.getData();
        return data;
    }


}
