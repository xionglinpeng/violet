package com.violet.web.support.returns.wrapper;

import java.util.Collection;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/21 下午5:30
 * @since 1.0.0
 */
public abstract class BasePageReturnWrapper<E> {

    /**
     * 当前页码
     */
    private long pageIndex;
    /**
     * 每页数量
     */
    private long pageSize;
    /**
     * 总页数
     */
    private long totalPage;
    /**
     * 总记录数
     */
    private long totalRecord;
    /**
     * 分页数据
     */
    private Collection<E> data;

    public BasePageReturnWrapper(Object data) {
        pageOf(data);
    }

    public abstract void pageOf(Object data);

    public long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Collection<E> getData() {
        return data;
    }

    public void setData(Collection<E> data) {
        this.data = data;
    }
}
