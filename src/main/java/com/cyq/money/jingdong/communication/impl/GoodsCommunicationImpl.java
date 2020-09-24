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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 商品类信息对接京东服务接口实现类
 */
@Service
public class GoodsCommunicationImpl implements GoodsCommunication {

    @Autowired
    private JingDongPropertiesReader jingDongPropertiesReader;

    @Override
    public CategoryResp[] getGoodsFirstCategory() throws JdException {

        JdClient client = new DefaultJdClient(jingDongPropertiesReader.getVal("jingdong.url"),null, jingDongPropertiesReader.getVal("jingdong.app-key"), jingDongPropertiesReader.getVal("jingdong.app-secret"));
        UnionOpenCategoryGoodsGetRequest request = new UnionOpenCategoryGoodsGetRequest();
        CategoryReq req = new CategoryReq();
        req.setParentId(0);
        req.setGrade(0);
        request.setReq(req);
        UnionOpenCategoryGoodsGetResponse response = client.execute(request);

        CategoryResp[] data = response.getData();

        return data;
    }

    @Override
    public JFGoodsResp[] getJingFengGoods(PageHelperParamVO paramVO) throws JdException {

        JdClient client=new DefaultJdClient(jingDongPropertiesReader.getVal("jingdong.url"),null, jingDongPropertiesReader.getVal("jingdong.app-key"), jingDongPropertiesReader.getVal("jingdong.app-secret"));
        UnionOpenGoodsJingfenQueryRequest request=new UnionOpenGoodsJingfenQueryRequest();
        JFGoodsReq goodsReq=new JFGoodsReq();

        // 参数设置
        jingFengGoodsRequestParamReBuild(paramVO, goodsReq);

        request.setGoodsReq(goodsReq);
        UnionOpenGoodsJingfenQueryResponse response = client.execute(request);
        JFGoodsResp[] data = response.getData();
        return data;
    }

    /**
     * 京粉精选商品查询接口对接参数封装
     * @param paramVO
     * @param req
     */
    private void jingFengGoodsRequestParamReBuild(PageHelperParamVO paramVO, JFGoodsReq req) {
        List<Map<String, String>> params = paramVO.getParams();

        if (params != null && params.size() > 0) {
            for(Map<String, String> map : params) {
                String key = map.get("key");
                String val = map.get("val");
                switch (key) {
                    case "eliteId":
                        req.setEliteId(Integer.valueOf(val)); break;  // 京粉eliteId
                    default:
                        break;
                }
            }
        }
        req.setPageIndex((int)(paramVO.getPageNum()));   // 页数
        req.setPageSize((int)(paramVO.getPageSize()));   // 每页数量
    }


}
