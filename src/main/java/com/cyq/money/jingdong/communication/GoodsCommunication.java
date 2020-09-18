package com.cyq.money.jingdong.communication;

import com.cyq.money.vo.PageHelperParamVO;
import com.jd.open.api.sdk.JdException;
import jd.union.open.category.goods.get.response.CategoryResp;
import jd.union.open.goods.jingfen.query.response.JFGoodsResp;

/**
 * 商品类数据对接京东服务接口
 */
public interface GoodsCommunication {

    /**
     * 获取商品一级目录信息
     * @return
     * @throws JdException
     */
    public CategoryResp[] getGoodsFirstCategory() throws JdException;

    /**
     * 获取京粉商品信息
     * @param paramVO
     * @throws JdException
     */
    public JFGoodsResp[] getJingFengGoods(PageHelperParamVO paramVO) throws JdException;

}
