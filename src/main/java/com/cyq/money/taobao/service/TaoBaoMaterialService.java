package com.cyq.money.taobao.service;

import com.cyq.money.vo.PageHelperParamVO;

import java.util.List;
import java.util.Map;

/**
 * @author cyq
 * @description 淘宝物料服务层接口
 * @date 2020/9/16 21:47
 */
public interface TaoBaoMaterialService {

    public Map<String, Object> getProductByTitle(PageHelperParamVO paramVO) throws Exception;

    public List seachProjectByMaterialId(PageHelperParamVO paramVO) throws Exception;

    /**
     * 获取商品的一级目录
     * @return
     */
    public List getFirstCategory();

    /**
     * 查询物料池中的商品
     * @param paramVO
     * @return
     */
    public List<Map> searchGoods(PageHelperParamVO paramVO);

}
