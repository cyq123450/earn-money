package com.cyq.money.taobao.controller;

import com.cyq.money.commons.ResponseResult;
import com.cyq.money.taobao.service.CommodityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyq
 * @description 商品类目控制器
 * @date 2020/9/14 21:52
 */
@RestController
@RequestMapping("/taobao/category")
public class CommodityCategoryController {

    @Autowired
    private CommodityCategoryService commodityCategoryService;

    @GetMapping("/get-category")
    public Object getCategory() {
        String category = commodityCategoryService.getCategory();
        return ResponseResult.success(category);
    }

}
