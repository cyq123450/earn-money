package com.cyq.money.commons;

/**
 * 分页实体类
 */
public class PageHelper<T> {

    // 当前页数
    private long pageNum;
    // 每页的数据量
    private long pageSize;
    // 总数量
    private long totalNum;
    // 总页数
    private long totalPage;
    // 数据
    private T data;

    public PageHelper() {
    }

    public PageHelper(long pageNum, long pageSize, long totalNum, long totalPage, T data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
        this.totalPage = totalPage;
        this.data = data;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
