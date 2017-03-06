package org.mybatis.extend.page.dialect;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.extend.page.Dialect;
import org.mybatis.extend.page.annotation.Pagination;
import org.mybatis.extend.page.parser.CountSqlParser;

import java.lang.reflect.Method;

/**
 * Created by Bob Jiang on 2017/3/6.
 */
public abstract class GenericDialect implements Dialect {

    protected CountSqlParser countSqlParser = new CountSqlParser();

    @Override
    public Pagination getPagination(String msId) {
        try {
            Class clazz = Class.forName(msId.substring(0, msId.lastIndexOf(".")));
            Method method = clazz.getMethod(msId.substring(msId.lastIndexOf(".") + 1, msId.length()), null);
            return method.getAnnotation(Pagination.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean canBePaged(MappedStatement ms, String sql) {
        SqlCommandType sqlCommandType = ms.getSqlCommandType();
        if (SqlCommandType.SELECT == sqlCommandType) {
            if (sql.trim().toUpperCase().endsWith("FOR UPDATE")) {
                throw new RuntimeException("for update语句不支持分页");
            }
        } else {
            throw new RuntimeException("该SQL语句不支持分页");
        }
        return true;
    }

    @Override
    public String getCountSql(MappedStatement ms, BoundSql boundSql, Object parameterObject, RowBounds rowBounds) {
        String sql = boundSql.getSql();
        canBePaged(ms, sql);
        return countSqlParser.getCountSql(sql);
    }

    @Override
    public String getPageSql(MappedStatement ms, BoundSql boundSql, Object parameterObject, RowBounds rowBounds) {
        return getPageSql(boundSql.getSql(), rowBounds);
    }

    public abstract String getPageSql(String sql, RowBounds rowBounds);

}
