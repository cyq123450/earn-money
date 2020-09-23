package com.cyq.money.controller;

import com.cyq.money.commons.ResponseResult;
import com.cyq.money.taobao.service.TaoBaoMaterialService;
import com.cyq.money.vo.PageHelperParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 主页控制器
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
        // 获取商品
        Integer goodCategory = paramVO.getGoodCategory();
        if (goodCategory == null) {
            goodCategory = 1;   // 默认查询淘宝数据
        }

        switch (goodCategory) {
            case 1:
                ; break;
            case 2:
                ; break;
            case 3:
                ; break;
            default:
                return ResponseResult.paramError();
        }

        return ResponseResult.nullData();

    }

}
