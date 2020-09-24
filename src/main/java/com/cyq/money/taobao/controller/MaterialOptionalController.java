package com.cyq.money.taobao.controller;

import com.cyq.money.commons.ResponseResult;
import com.cyq.money.taobao.commons.TaoBaoPropertiesReader;
import com.cyq.money.taobao.service.TaoBaoMaterialService;
import com.cyq.money.utils.ResponseResultUtils;
import com.cyq.money.vo.PageHelperParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 物料类查询实体类
 */
@RestController
@RequestMapping("/taobao/material-optional")
public class MaterialOptionalController {

    @Autowired
    private TaoBaoMaterialService materialService;

    /**
     * 获取淘宝实时榜单信息
     * @param paramVO
     * @return
     */
    @PostMapping("/get-real-time")
    public Object getRealTimeList(@RequestBody PageHelperParamVO paramVO){
        try {
            List<Map> realTimeList = materialService.getRealTimeList(paramVO);
            return ResponseResultUtils.pageResultProcess(paramVO, realTimeList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.failed("数据查询失败");
        }
    }

    /**
     * 获取商品的一级目录
     * @return
     */
    @GetMapping("/get-first-category")
    public Object getFirstCategor() {

        try {
          List firstCategory = materialService.getFirstCategory();
          return ResponseResultUtils.resultProcess(firstCategory);
        } catch (Exception e) {
          e.printStackTrace();
          return ResponseResult.failed();
        }

    }

}
