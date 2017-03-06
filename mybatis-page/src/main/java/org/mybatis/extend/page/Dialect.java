package org.mybatis.extend.page;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.extend.page.annotation.Pagination;

/**
 * 基础方言接口
 *
 * Created by Bob Jiang on 2017/3/6.
 */
public interface Dialect {

    Pagination getPagination(String msId);

    boolean canBePaged(MappedStatement ms, String sql);

    String getCountSql(MappedStatement ms, BoundSql boundSql, Object parameterObject, RowBounds rowBounds);

    String getPageSql(MappedStatement ms, BoundSql boundSql, Object parameterObject, RowBounds rowBounds);

}
