package com.cyq.money.pinduoduo.service;

import com.cyq.money.vo.PageHelperParamVO;

import java.util.List;
import java.util.Map;

/**
 * @author cyq
 * @description 拼多多服务层接口
 * @date 2020/9/28 21:37
 */
public interface GoodsService {

    /**
     * 通过关键字搜索商品信息
     * @param paramVO
     * @return
     */
    List<Map> searchGoods(PageHelperParamVO paramVO);

}
