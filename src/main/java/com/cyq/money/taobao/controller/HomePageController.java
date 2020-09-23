package com.cyq.money.taobao.controller;

import com.cyq.money.commons.ResponseResult;
import com.cyq.money.commons.PageHelper;
import com.cyq.money.taobao.commons.TaoBaoPropertiesReader;
import com.cyq.money.taobao.service.TaoBaoMaterialService;
import com.cyq.money.vo.PageHelperParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    private TaoBaoMaterialService materialOptionalService;

    @Autowired
    private TaoBaoPropertiesReader taoBaoPropertiesReader;

    /**
     * 首页中的搜索框接口
     * @param paramVO
     * @return
     */
    @PostMapping("/search/by-title")
    public Object searchByTitle(@RequestBody PageHelperParamVO paramVO) {
        try {
            Map<String, Object> result = materialOptionalService.getProductByTitle(paramVO);
            return ResponseResult.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.failed();
        }
    }

    /**
     * 今日必抢
     * @param paramVO
     * @return
     */
    @PostMapping("/search/sale-day")
    public Object getSaleVeryDay(@RequestBody PageHelperParamVO paramVO) {
        try {
            Map<String, String> params = paramVO.getParams();
            params.put("material_id", taoBaoPropertiesReader.getVal("sale-every-day"));

            List list = materialOptionalService.seachProjectByMaterialId(paramVO);
            PageHelper pageHelper = new PageHelper();
            pageHelper.setPageNum(paramVO.getPageNum());
            pageHelper.setPageSize(paramVO.getPageSize());
            pageHelper.setData(list);
            return ResponseResult.success(pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.failed("数据查询失败");
        }
    }

}
