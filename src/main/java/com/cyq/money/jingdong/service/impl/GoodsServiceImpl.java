package com.cyq.money.jingdong.service.impl;

import com.cyq.money.jingdong.commons.JingDongPropertiesReader;
import com.cyq.money.jingdong.communication.GoodsCommunication;
import com.cyq.money.jingdong.service.GoodsService;
import com.cyq.money.vo.PageHelperParamVO;
import com.jd.open.api.sdk.JdException;
import jd.union.open.category.goods.get.response.CategoryResp;
import jd.union.open.goods.jingfen.query.response.JFGoodsResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * 商品服务层接口实现类
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsCommunication goodsCommunication;

    @Autowired
    private JingDongPropertiesReader jingDongPropertiesReader;

    @Override
    public List getFirstCategory() throws JdException, IOException {
        CategoryResp[] categorys = goodsCommunication.getGoodsFirstCategory();
        List datas = new ArrayList<>();
        for(CategoryResp categoryResp : categorys) {
            Map<String, String> parm = new HashMap<>();
            parm.put(categoryResp.getId()+"", categoryResp.getName());
            datas.add(parm);
        }
        return datas;
    }

    @Override
    public List<Map> getRealTimeList(PageHelperParamVO paramVO) throws JdException {
        List<Map<String, String>> params = paramVO.getParams();
        Map map = new HashMap<>();
        map.put("key", "eliteId");
        map.put("val", jingDongPropertiesReader.getVal("real-time-list"));
        params.add(map);

        JFGoodsResp[] jingFengGoods = goodsCommunication.getJingFengGoods(paramVO);

        return processResultSimple(jingFengGoods);
    }

    @Override
    public List searchGood(PageHelperParamVO paramVO) {

        return null;
    }

    /**
     * 简单处理结果集
     * @param jingFengGoods
     * @return
     */
    private List processResultSimple(JFGoodsResp[] jingFengGoods) {
        List list = new ArrayList<>();
        for(JFGoodsResp resp : jingFengGoods) {
            Map map = new HashMap<>();
            map.put("pictUrl", resp.getImageInfo().getImageList()[0]);                  // 主图
            map.put("shortTitle", resp.getSkuName());                                   // 标题
            map.put("couponAmount","");                                                 // 优惠券金额(京东优惠券比较特殊，里面存放的是集合数据)
            map.put("reservePrice", resp.getPriceInfo().getPrice());                    // 一口价
            map.put("zkFinalPrice", resp.getPriceInfo().getLowestPrice());              // 折扣价
            map.put("nick", resp.getShopInfo().getShopName());                          // 卖家昵称
            map.put("afterCouponAmount", resp.getPriceInfo().getLowestCouponPrice());   // 券后价
            map.put("itemId", resp.getSkuId());                                         // 商品Id
            list.add(map);
        }
        return list;
    }


}
