package com.cyq.money.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分页传参实体类
 */
@Setter
@Getter
@NoArgsConstructor
public class PageHelperParamVO {

    // 每页数据量大小
    private long pageSize;
    // 当前页
    private long pageNum;
    // 业务参数(使用“key”作为过滤的键  使用“val”作为过滤的值)
    private List<Map<String, String>> params = new ArrayList<>();
    // 平台类型(1:淘宝  2:京东  3:拼多多)
    private Integer goodCategory;

}
