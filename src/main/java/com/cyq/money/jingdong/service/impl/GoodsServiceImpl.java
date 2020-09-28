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

    @Override
    public List getFirstCategory() throws JdException {
        CategoryResp[] categorys = new CategoryResp[0];
        try {
            categorys = goodsCommunication.getGoodsFirstCategory();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        // TODO(后期需要优化)

        paramVO.getParams().put("eliteId", JingDongPropertiesReader.getPros("jingdong.real-time-list"));
        JFGoodsResp[] jingFengGoods = goodsCommunication.getJingFengGoods(paramVO);

        return getSimpleProductForRealTimeList(jingFengGoods);
    }

    public List getJingFenGoodsByCondition(PageHelperParamVO paramVO) {
        // paramVO.getParams().put("eliteId", JingDongPropertiesReader.getPros("real-time-list"));
        return null;
    }

    public List getSaleVeryDay(PageHelperParamVO paramVO) throws JdException {
        paramVO.getParams().put("eliteId", JingDongPropertiesReader.getPros("jingdong.sale-every-day"));

        JFGoodsResp[] jingFengGoods = goodsCommunication.getJingFengGoods(paramVO);
        return getSimpleProductForRealTimeList(jingFengGoods);
    }

    /**
     * 实时榜单数据处理
     * @return
     */
    private List getSimpleProductForRealTimeList(JFGoodsResp[] jingFengGoods) {
        List list = new ArrayList<>();
        for(JFGoodsResp resp : jingFengGoods) {
            Map map = new HashMap<>();
            map.put("pict_url", resp.getImageInfo().getImageList()[0]); // 主图
            map.put("sub_title", resp.getSkuName());    // 标题
            map.put("coupon_amount","");    // 优惠券金额(京东优惠券比较特殊，里面存放的是集合数据)
            map.put("reserve_price", resp.getPriceInfo().getPrice());   // 一口价
            map.put("zk_final_price", resp.getPriceInfo().getLowestPrice());    // 折后价
            map.put("nick", resp.getShopInfo().getShopName());  // 店铺名称
            map.put("after_coupon_amount", resp.getPriceInfo().getLowestCouponPrice()); // 券后价
            map.put("item_id", resp.getSkuId());    // 商品Id
            list.add(map);
        }
        return list;
    }


}
