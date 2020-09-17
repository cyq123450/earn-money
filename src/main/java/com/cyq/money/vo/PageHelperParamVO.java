package com.cyq.money.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页传参实体类
 */
public class PageHelperParamVO {

    // 当前页
    private long pageSize;
    // 每页数据量大小
    private long pageNum;
    // 业务参数
    private Map<String, String> params = new HashMap<>();

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
