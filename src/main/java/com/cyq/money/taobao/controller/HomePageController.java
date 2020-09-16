package com.cyq.money.taobao.controller;

import com.cyq.money.commons.ResponseResult;
import com.cyq.money.taobao.service.MaterialOptionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author cyq
 * @description 首页控制器
 * @date 2020/9/16 21:33
 */
@RestController
@RequestMapping("/taobao/home-page")
public class HomePageController {

    @Autowired
    private MaterialOptionalService materialOptionalService;

    @GetMapping("/search/by-title")
    public Object searchByTitle(@RequestParam("titleName") String titleName) {
        try {
            Map<String, Object> result = materialOptionalService.getProductByTitle(titleName);
            return ResponseResult.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.failed();
        }
    }

}
