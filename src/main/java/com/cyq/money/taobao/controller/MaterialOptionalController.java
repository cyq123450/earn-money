package com.cyq.money.taobao.controller;

import com.cyq.money.commons.ResponseResult;
import com.cyq.money.commons.PageHelper;
import com.cyq.money.taobao.commons.TaoBaoPropertiesReader;
import com.cyq.money.taobao.service.MaterialService;
import com.cyq.money.vo.PageHelperParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 物料类查询实体类
 */
@RestController
@RequestMapping("/taobao/material-optional")
public class MaterialOptionalController {

    @Autowired
    private MaterialService materialService;

    /**
     * 获取淘宝实时榜单信息
     * @param paramVO
     * @return
     */
    @PostMapping("/get-real-time")
    public Object getRealTimeList(@RequestBody PageHelperParamVO paramVO){
        try {
            Map<String, String> params = paramVO.getParams();
            params.put("material_id", TaoBaoPropertiesReader.getPros("taobao.real-time-list"));

            List list = materialService.seachProjectByMaterialId(paramVO);
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
