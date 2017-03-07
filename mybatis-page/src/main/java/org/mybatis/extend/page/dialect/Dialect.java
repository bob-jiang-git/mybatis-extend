package org.mybatis.extend.page.dialect;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.extend.page.param.Page;
import org.mybatis.extend.page.result.PageList;

import java.util.List;
import java.util.Map;

/**
 * 基础方言接口
 *
 * Created by Bob Jiang on 2017/3/6.
 */
public interface Dialect {

    boolean canBePaged(MappedStatement ms, String sql);

    BoundSql getCountSql(MappedStatement ms, BoundSql boundSql, Object parameter,
                       RowBounds rowBounds, Map<String, Object> additionalParameters);

    BoundSql getPageSql(MappedStatement ms, BoundSql boundSql, Object parameter,
                      RowBounds rowBounds, Map<String, Object> additionalParameters);

    PageList buildPageList(List resultList, Page page, int totalRows);
}
