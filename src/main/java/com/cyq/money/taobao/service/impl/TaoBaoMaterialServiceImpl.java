package com.cyq.money.taobao.service.impl;

import com.cyq.money.taobao.cache.TaoBaoCache;
import com.cyq.money.taobao.commons.TaoBaoPropertiesReader;
import com.cyq.money.taobao.communication.MaterialCommunication;
import com.cyq.money.taobao.service.TaoBaoMaterialService;
import com.cyq.money.vo.PageHelperParamVO;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author cyq
 * @description 淘宝物料类服务层接口实现类
 * @date 2020/9/16 21:48
 */
@Service
public class TaoBaoMaterialServiceImpl implements TaoBaoMaterialService {

    @Autowired
    private MaterialCommunication materialCommunication;

    @Autowired
    private TaoBaoPropertiesReader taoBaoPropertiesReader;


    @Override
    public List getFirstCategory() {
        return TaoBaoCache.getFirstCategory();
    }

    @Override
    public List<Map> searchGoods(PageHelperParamVO paramVO) throws ApiException {
        Map optionalMaterial = materialCommunication.getOptionalMaterial(paramVO);
        return processOptionalResultSimple(optionalMaterial);
    }

    @Override
    public List<Map> getSaleVeryDayGoods(PageHelperParamVO paramVO) throws Exception {
        // 添加今日必抢物料池D
        String saleEveryDay = taoBaoPropertiesReader.getVal("sale-every-day");
        return processMaterialId(paramVO, saleEveryDay);
    }

    @Override
    public List<Map> GetYouMayLike(PageHelperParamVO paramVO) throws Exception {
        // 添加猜你喜欢物料池ID
        String youMayLike = taoBaoPropertiesReader.getVal("you-may-like");
        return processMaterialId(paramVO, youMayLike);
    }

    @Override
    public List<Map> getRealTimeList(PageHelperParamVO paramVO) throws Exception {
        // 添加实时榜单物料池ID
        String realTimeList = taoBaoPropertiesReader.getVal("real-time-list");
        return processMaterialId(paramVO, realTimeList);
    }

    /**
     * 处理物料池Id
     * @param paramVO
     * @param materialId
     * @return
     * @throws Exception
     */
    private List<Map> processMaterialId(PageHelperParamVO paramVO, String materialId) throws Exception {
        List params = paramVO.getParams();
        Map<String, String> param = new HashMap<>();
        param.put("key", "materialId");
        param.put("val", materialId);
        params.add(param);

        // 如果是今日必抢会有categoryId，需要调用物料搜索服务
        for(Map map : (List<Map>)params) {
            String key = (String)map.get("key");
            if (key == null) {
                continue;
            }
            if (key.equals("categoryId")) {
                Map optionalMaterial = materialCommunication.getOptionalMaterial(paramVO);
                return processOptimusResultSimple(optionalMaterial);
            }
        }

        Map optimaMaterial = materialCommunication.getOptimuMaterial(paramVO);
        return processOptimusResultSimple(optimaMaterial);
    }

    /**
     * 淘宝精选物料池结果集处理成简图
     * @param data
     * @return
     */
    private List processOptimusResultSimple(Map data) {
        if (data == null) {
            return null;
        }
        Map optimumMaterialResponse = (Map)data.get("tbk_dg_optimus_material_response");
        if (optimumMaterialResponse == null) {
            return null;
        }
        Map resultList = (Map) optimumMaterialResponse.get("result_list");
        if (resultList == null) {
            return null;
        }
        List<Map> mapData = (List<Map>) resultList.get("map_data");
        if (mapData == null && mapData.size() <= 0) {
            return null;
        }

        List dataList = new ArrayList<Map>();
        // 数据过滤
        for(Map map : mapData) {
            Map subMap = new HashMap<>();
            subMap.put("pictUrl", map.get("pict_url"));                     // 主图地址
            subMap.put("shortTitle", map.get("title"));               // 商品标题
            subMap.put("couponAmount", map.get("coupon_amount"));           // 优惠券金额
            subMap.put("reservePrice", map.get("reserve_price"));           // 一口价
            subMap.put("zkFinalPrice", map.get("zk_final_price"));          // 折扣价
            subMap.put("nick", map.get("nick"));                            // 卖家昵称
            String zk_final_price = (String) map.get("zk_final_price");
            if (zk_final_price == null || zk_final_price.equals("")) {
                zk_final_price = "0";
            }
            String coupon_amount = (String) map.get("coupon_amount");
            if (coupon_amount == null || coupon_amount.equals("")) {
                coupon_amount = "0";
            }
            Float zkFinalPrice = Float.valueOf(zk_final_price);
            Float couponAmount = Float.valueOf(coupon_amount);
            Float afterCouponAmount = zkFinalPrice - couponAmount;
            DecimalFormat decimalFormat=new DecimalFormat(".00");
            subMap.put("afterCouponAmount", decimalFormat.format(afterCouponAmount));// 券后价
            subMap.put("itemId", map.get("item_id"));                       // 商品ID
            dataList.add(subMap);
        }
        return dataList;
    }

    /**
     * 淘宝搜索物料池结果集处理成简图
     * @param data
     * @return
     */
    private List processOptionalResultSimple(Map data) {
        if (data == null) {
            return null;
        }
        Map optimumMaterialResponse = (Map)data.get("tbk_dg_material_optional_response");
        if (optimumMaterialResponse == null) {
            return null;
        }
        Map resultList = (Map) optimumMaterialResponse.get("result_list");
        if (resultList == null) {
            return null;
        }
        List<Map> mapData = (List<Map>) resultList.get("map_data");
        if (mapData == null && mapData.size() <= 0) {
            return null;
        }

        List dataList = new ArrayList<Map>();
        // 数据过滤
        for(Map map : mapData) {
            Map subMap = new HashMap<>();
            subMap.put("pictUrl", map.get("pict_url"));                     // 主图地址
            subMap.put("shortTitle", map.get("title"));               // 商品标题
            subMap.put("couponAmount", map.get("coupon_amount"));           // 优惠券金额
            subMap.put("reservePrice", map.get("reserve_price"));           // 一口价
            subMap.put("zkFinalPrice", map.get("zk_final_price"));          // 折扣价
            subMap.put("nick", map.get("nick"));                            // 卖家昵称
            String zk_final_price = (String) map.get("zk_final_price");
            if (zk_final_price == null || zk_final_price.equals("")) {
                zk_final_price = "0";
            }
            Float zkFinalPrice = Float.valueOf(zk_final_price);
            String coupon_amount = (String) map.get("coupon_amount");
            if (coupon_amount == null || coupon_amount.equals("")) {
                coupon_amount = "0";
            }
            Float couponAmount = Float.valueOf(coupon_amount);
            Float afterCouponAmount = zkFinalPrice - couponAmount;
            DecimalFormat decimalFormat=new DecimalFormat(".00");
            subMap.put("afterCouponAmount", decimalFormat.format(afterCouponAmount));// 券后价
            subMap.put("itemId", map.get("item_id"));                       // 商品ID
            dataList.add(subMap);
        }
        return dataList;
    }

}
