package org.mybatis.extend.page.result;

import org.mybatis.extend.page.Page;

import java.util.List;

/**
 * Created by Bob Jiang on 2017/3/6.
 */
public class PageResult<T> {

    private int pageSize;
    private int pageNum;
    private int totalPage;
    private int totalRows;
    private List<T> data;

    public PageResult() { }

    public PageResult(List<T> data) {
        this.data = data;
    }

    public PageResult(Page page, List<T> data) {
        this.pageSize = page.getPageSize();
        this.pageNum = page.getPageNum();
        this.totalPage = page.getTotalPage();
        this.totalRows = page.getTotalRows();
        this.data = data;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
