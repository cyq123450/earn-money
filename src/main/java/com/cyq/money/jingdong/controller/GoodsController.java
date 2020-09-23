package com.cyq.money.jingdong.controller;

import com.cyq.money.commons.PageHelper;
import com.cyq.money.commons.ResponseResult;
import com.cyq.money.jingdong.service.GoodsService;
import com.cyq.money.vo.PageHelperParamVO;
import com.jd.open.api.sdk.JdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/get-real-time")
    public Object getRealTimeList(@RequestBody PageHelperParamVO paramVO) {
        try {
            List<Map> realTimeList = goodsService.getRealTimeList(paramVO);
            PageHelper<Object> pageHelper = new PageHelper<>();
            pageHelper.setPageNum(paramVO.getPageNum());
            pageHelper.setPageSize(paramVO.getPageSize());
            pageHelper.setData(realTimeList);
            return ResponseResult.success(pageHelper);
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
            return ResponseResult.success(firstCategory);
        } catch (JdException e) {
            e.printStackTrace();
            return ResponseResult.failed("商品一级分类信息获取失败");
        }
    }

    /**
     * 通过过滤条件获取京粉商品信息
     * @return
     */
    public Object getJingFenGoodsByCondition(PageHelperParamVO paramVO) {

        return null;
    }

    /**
     * 获取今日必抢数据
     * @return
     */
    @RequestMapping("/get/sale-day")
    public Object getSaleVeryDay(@RequestBody PageHelperParamVO paramVO) {
        try {
            List saleVeryDay = goodsService.getSaleVeryDay(paramVO);
            PageHelper<Object> pageHelper = new PageHelper<>();
            pageHelper.setPageNum(paramVO.getPageNum());
            pageHelper.setPageSize(paramVO.getPageSize());
            pageHelper.setData(saleVeryDay);
            return ResponseResult.success(pageHelper);
        } catch (JdException e) {
            e.printStackTrace();
            return ResponseResult.failed("获取今日必抢数据失败");
        }

    }

}
