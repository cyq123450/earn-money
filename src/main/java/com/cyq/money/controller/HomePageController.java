package com.cyq.money.controller;

import com.cyq.money.commons.ResponseResult;
import com.cyq.money.taobao.service.TaoBaoMaterialService;
import com.cyq.money.utils.ResponseResultUtils;
import com.cyq.money.vo.PageHelperParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * 首页控制器
 */
@RestController
@RequestMapping("/home-page")
public class HomePageController {

    @Autowired
    private TaoBaoMaterialService taoBaoMaterialService;

    /**
     * 首页的搜索
     * @return
     */
    @PostMapping("/search")
    public Object search(@RequestBody PageHelperParamVO paramVO) {
        try{
            // 获取商品平台id
            Integer goodCategory = paramVO.getGoodCategory();
            if (goodCategory == null) {
                goodCategory = 1;   // 默认查询淘宝数据
            }

            // 存储结果集
            List<Map> list = null;
            switch (goodCategory) {
                case 1:
                    list = taoBaoMaterialService.searchGoods(paramVO);  // 淘宝
                    break;
                case 2:
                    ; break;
                case 3:
                    ; break;
                default:
                    return ResponseResult.paramError();
            }

            // 封装结果集数据
            return ResponseResultUtils.pageResultProcess(paramVO, list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.failed("商品查询失败");
        }

    }

    /**
     * 今日必抢(暂定查询淘宝平台数据)
     * @param paramVO
     * @return
     */
    @PostMapping("/sale-very-day")
    public Object getSaleVeryDay(@RequestBody PageHelperParamVO paramVO) {
        try {
            List<Map> saleVeryDayGoods = taoBaoMaterialService.getSaleVeryDayGoods(paramVO);
            // 处理结果集
            return ResponseResultUtils.pageResultProcess(paramVO, saleVeryDayGoods);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.failed();
        }
    }

    /**
     * 猜你喜欢(暂定查询淘宝平台数据)
     * @param paramVO
     * @return
     */
    @PostMapping("/you-may-like")
    public Object GetYouMayLike(@RequestBody PageHelperParamVO paramVO) {
       try {
           List<Map> list = taoBaoMaterialService.GetYouMayLike(paramVO);
           // 结果集处理
           return ResponseResultUtils.pageResultProcess(paramVO, list);
       } catch (Exception e) {
           e.printStackTrace();
           return ResponseResult.failed();
       }
    }

}
