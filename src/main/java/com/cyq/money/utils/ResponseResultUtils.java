package com.cyq.money.utils;

import com.cyq.money.commons.PageHelper;
import com.cyq.money.commons.ResponseResult;
import com.cyq.money.vo.PageHelperParamVO;

import java.util.List;

/**
 * 响应实体工具类
 */
public class ResponseResultUtils {

    /**
     * 分页数据响应结果集处理
     * @param paramVO
     * @param list
     * @return
     */
    public static Object pageResultProcess(PageHelperParamVO paramVO , List list) {
        if (list == null || list.size() <= 0) {
            return ResponseResult.nullData();
        }
        // 封装返回数据
        PageHelper<Object> pageHelper = new PageHelper<>();
        pageHelper.setPageNum(paramVO.getPageNum());
        pageHelper.setPageSize(paramVO.getPageSize());
        pageHelper.setData(list);
        return ResponseResult.success(pageHelper);
    }

    /**
     * 无分页数据响应结果集处理
     * @param list
     * @return
     */
    public static Object resultProcess(List list) {
        if (list == null || list.size() <= 0) {
            return ResponseResult.nullData();
        }
        // 封装返回数据
        return ResponseResult.success(list);
    }

}
