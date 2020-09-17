package com.cyq.money.taobao.service.impl;

import com.cyq.money.taobao.commons.TaoBaoPropertiesReader;
import com.cyq.money.taobao.communication.MaterialCommunication;
import com.cyq.money.taobao.service.MaterialService;
import com.cyq.money.vo.PageHelperParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cyq
 * @description 物料类服务层接口实现类
 * @date 2020/9/16 21:48
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialCommunication materialCommunication;

    @Override
    public Map<String, Object> getProductByTitle(PageHelperParamVO paramVO) throws Exception {
        return materialCommunication.getOptimuMaterial(paramVO);
    }

    @Override
    public List seachProjectByMaterialId(PageHelperParamVO paramVO) throws Exception {
        // 接口调用
        Map dataMaps = materialCommunication.getOptimusMaterial(paramVO);

        Map<String, Object> optimusMaterial = (Map<String, Object>)dataMaps.get("tbk_dg_optimus_material_response");
        Map<String, Object> resultList = (Map<String, Object>)optimusMaterial.get("result_list");
        List<Map<String, Object>> mapDataList = (List<Map<String, Object>>)resultList.get("map_data");

        List datas = new ArrayList();
        for (Map<String, Object> map : mapDataList) {
            Map map1 = new HashMap<>();
            map1.put("pict_url", map.get("pict_url"));  // 主图
            map1.put("sub_title", map.get("sub_title"));    // 子标题
            map1.put("coupon_amount", map.get("coupon_amount"));    // 优惠券金额
            map1.put("reserve_price", map.get("reserve_price"));    // 一口价
            map1.put("zk_final_price", map.get("zk_final_price"));   // 折后价
            map1.put("nick", map.get("nick"));  // 卖家昵称
            double zk_final_price = Double.valueOf((String) map.get("zk_final_price")).doubleValue();
            double coupon_amount = ((Integer)map.get("coupon_amount")).doubleValue();
            DecimalFormat df = new DecimalFormat("#.00");
            map1.put("after_coupon_amount", df.format(zk_final_price-coupon_amount)); // 券后价
            map1.put("item_id", map.get("item_id"));    // 商品itemId
            datas.add(map1);
        }
        return datas;
    }


}
