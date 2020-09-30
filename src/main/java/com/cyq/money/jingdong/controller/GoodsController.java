package com.cyq.money.jingdong.controller;

import com.cyq.money.commons.PageHelper;
import com.cyq.money.commons.ResponseResult;
import com.cyq.money.jingdong.service.GoodsService;
import com.cyq.money.utils.ResponseResultUtils;
import com.cyq.money.vo.PageHelperParamVO;
import com.jd.open.api.sdk.JdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 商品控制器
 */
@RestController
@RequestMapping("/jingdong/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 获取京东实时榜单信息
     * @param paramVO
     * @return
     */
    @GetMapping("/get-real-time")
    public Object getRealTimeList(@RequestBody PageHelperParamVO paramVO) {
        try {
            List<Map> realTimeList = goodsService.getRealTimeList(paramVO);
            return ResponseResultUtils.pageResultProcess(paramVO, realTimeList);
        } catch (JdException e) {
            e.printStackTrace();
            return ResponseResult.failed("京东实时榜单信息获取失败");
        }
    }

    /**
     * 获取商品的一级目录
     * @return
     */
    @GetMapping("/get-first-category")
    public Object getFirstCategory() {
        try {
            List firstCategory = goodsService.getFirstCategory();
            return ResponseResultUtils.resultProcess(firstCategory);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.failed("商品一级分类信息获取失败");
        }
    }


}
