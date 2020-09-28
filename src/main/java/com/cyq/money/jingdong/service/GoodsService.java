package com.cyq.money.jingdong.service;

import com.cyq.money.vo.PageHelperParamVO;
import com.jd.open.api.sdk.JdException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 商品服务层接口
 */
public interface GoodsService {

    /**
     * 获取商品的一级目录
     * @return
     */
    public List getFirstCategory() throws JdException, IOException;

    /**
     * 获取实时热榜商品数据
     * @param paramVO
     * @return
     */
    public List<Map> getRealTimeList(PageHelperParamVO paramVO) throws JdException;

    /**
     * 搜索商品
     * @param paramVO
     * @return
     */
    public List searchGood(PageHelperParamVO paramVO);

}
