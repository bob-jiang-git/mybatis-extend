package org.mybatis.extend.page.param;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.extend.page.constant.PageConstant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Page 作为输入
 *
 * Created by Bob Jiang on 2017/3/3.
 */
public class Page implements Serializable {

    private static final long serialVersionUID = -4882478724270973629L;

    private int pageSize;
    private int pageNum;
    private List<Map<String, OrderType>> orders = new ArrayList<Map<String, OrderType>>();

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

    public List<Map<String, OrderType>> getOrders() {
        return orders;
    }

    public void setOrders(List<Map<String, OrderType>> orders) {
        this.orders = orders;
    }

    public void setAscOrders(List<String> orders) {
        settingOrder(orders, OrderType.ASC);
    }

    public void setDescOrders(List<String> orders) {
        settingOrder(orders, OrderType.DESC);
    }

    private void settingOrder(List<String> orders, OrderType orderType) {
        orders.forEach(i -> {
            Map<String, OrderType> map = new HashMap<String, OrderType>();
            map.put(i, orderType);
            this.orders.add(map);
        });
    }

    public enum OrderType {
        ASC, DESC
    }

}
