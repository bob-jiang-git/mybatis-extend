package org.mybatis.extend.page;

import org.mybatis.extend.page.constant.PageConstant;

import java.io.Serializable;

/**
 * Created by Bob Jiang on 2017/3/3.
 */
public class Page implements Serializable {

    private static final long serialVersionUID = -4882478724270973629L;

    private int pageSize;
    private int pageNum;
    private int totalPage;
    private int totalRows;

    public Page(int pageSize, int pageNum) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Page setPageSize(int pageSize) {
        this.pageSize = pageSize > 0 ? pageSize : PageConstant.PAGE_SIZE;
        return this;
    }

    public int getPageNum() {
        return pageNum;
    }

    public Page setPageNum(int pageNum) {
        this.pageNum = pageNum > 0 ? pageNum : 1;
        return this;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public Page setTotalPage(int totalPage) {
        this.totalPage = totalPage;
        return this;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public Page setTotalRows(int totalRows) {
        this.totalRows = totalRows;
        return this;
    }
}
