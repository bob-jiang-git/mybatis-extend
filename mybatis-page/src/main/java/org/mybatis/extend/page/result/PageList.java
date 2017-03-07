package org.mybatis.extend.page.result;


import java.util.ArrayList;

/**
 * 分页结果集
 *
 * Created by Bob Jiang on 2017/3/6.
 */
public class PageList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 6452924799509915636L;

    private int pageSize;
    private int pageNum;
    private int totalPage;
    private int totalRows;

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
        totalPage = totalRows % pageSize == 0 ? totalRows / pageSize : totalRows / pageSize + 1;
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

}
