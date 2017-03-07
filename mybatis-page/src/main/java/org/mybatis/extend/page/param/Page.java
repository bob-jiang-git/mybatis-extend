package org.mybatis.extend.page.param;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.extend.page.constant.PageConstant;

import java.io.Serializable;

/**
 * Page 作为输入
 *
 * Created by Bob Jiang on 2017/3/3.
 */
public class Page implements Serializable {

    private static final long serialVersionUID = -4882478724270973629L;

    private int pageSize;
    private int pageNum;

    public Page() { }

    public Page(int pageNum) {
        this.pageNum = pageNum;
    }

    public Page(int pageSize, int pageNum) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    public RowBounds toRowBounds() {
        int offset = (getPageNum() - 1) * getPageSize();
        int limit = getPageSize();
        return new RowBounds(offset, limit);
    }

    public int getPageSize() {
        return pageSize > 0 ? pageSize : PageConstant.PAGE_SIZE;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum > 0 ? pageNum : 1;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

}
