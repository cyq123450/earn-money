package com.cyq.money.taobao.controller;

import com.cyq.money.commons.ResponseCode;
import com.cyq.money.commons.ResponseMsg;
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
 * 物料类查询实体类
 */
@RestController
@RequestMapping("/taobao/material-optional")
public class MaterialOptionalController {

    @Autowired
    private TaoBaoMaterialService materialService;

    @Autowired
    private TaoBaoPropertiesReader taoBaoPropertiesReader;

    /**
     * 获取淘宝实时榜单信息
     * @param paramVO
     * @return
     */
    @PostMapping("/get-real-time")
    public Object getRealTimeList(@RequestBody PageHelperParamVO paramVO){
        try {
            // Map<String, String> params = paramVO.getParams();
            // params.put("material_id", taoBaoPropertiesReader.getVal("real-time-list"));

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

    /**
     * 获取商品的一级目录
     * @return
     */
    @GetMapping("/get-first-category")
    public Object getFirstCategor() {

        try {
          List firstCategory = materialService.getFirstCategory();
          if (firstCategory == null || firstCategory.size() <= 0) {
              return ResponseResult.success(ResponseCode.NULL_DATA.getCode(), ResponseMsg.NULL_DATA.getMsg(), null);
          }
          return ResponseResult.success(firstCategory);
      } catch (Exception e) {
          e.printStackTrace();
          return ResponseResult.failed();
      }

    }

}
