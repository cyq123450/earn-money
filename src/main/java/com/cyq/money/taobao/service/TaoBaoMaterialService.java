package com.cyq.money.taobao.service;

import com.cyq.money.vo.PageHelperParamVO;
import com.taobao.api.ApiException;

import java.util.List;
import java.util.Map;

/**
 * @author cyq
 * @description 淘宝物料服务层接口
 * @date 2020/9/16 21:47
 */
public interface TaoBaoMaterialService {

    /**
     * 获取商品的一级目录
     * @return
     */
    List getFirstCategory();

    /**
     * 查询物料池中的商品
     * @param paramVO
     * @return
     */
    List<Map> searchGoods(PageHelperParamVO paramVO) throws ApiException;

    /**
     * 获取今日必抢商品
     * @param paramVO
     * @return
     */
    List<Map> getSaleVeryDayGoods(PageHelperParamVO paramVO) throws Exception;

    /**
     * 获取猜你喜欢商品
     * @param paramVO
     * @return
     */
    List<Map> GetYouMayLike(PageHelperParamVO paramVO) throws Exception;

    /**
     * 获取实时榜单数据
     * @param paramVO
     * @return
     */
    List<Map> getRealTimeList(PageHelperParamVO paramVO) throws Exception;

}
