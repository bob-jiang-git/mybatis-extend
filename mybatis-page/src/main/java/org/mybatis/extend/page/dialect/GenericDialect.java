package org.mybatis.extend.page.dialect;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.extend.page.annotation.Pagination;
import org.mybatis.extend.page.param.Page;
import org.mybatis.extend.page.result.PageList;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by Bob Jiang on 2017/3/6.
 */
public abstract class GenericDialect implements Dialect {

    public Pagination getPagination(String msId) {
        try {
            Class clazz = Class.forName(msId.substring(0, msId.lastIndexOf(".")));
            String methodName = msId.substring(msId.lastIndexOf(".") + 1, msId.length());

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Pagination pagination = method.getAnnotation(Pagination.class);
                    if (pagination.value()) {
                        return pagination;
                    }
                }
            }
        } catch (Exception e) { }
        return null;
    }

    public boolean canBePaged(MappedStatement ms, String sql) {
        if (getPagination(ms.getId()) == null) {
            return false;
        }
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

    public BoundSql getCountSql(MappedStatement ms, BoundSql boundSql, Object parameter,
            RowBounds rowBounds, Map<String, Object> additionalParameters) {
        String countSql = getCountSql(boundSql.getSql());

        BoundSql countBoundSql = new BoundSql(ms.getConfiguration(), countSql, boundSql.getParameterMappings(), parameter);
        for (String key : additionalParameters.keySet()) {
            countBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
        }
        return countBoundSql;
    }

    public BoundSql getPageSql(MappedStatement ms, BoundSql boundSql, Object parameter,
             RowBounds rowBounds, Map<String, Object> additionalParameters) {

        String pageSql = getPageSql(boundSql.getSql(), rowBounds);

        BoundSql pageBoundSql = new BoundSql(ms.getConfiguration(), pageSql, boundSql.getParameterMappings(), parameter);
        for (String key : additionalParameters.keySet()) {
            pageBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
        }
        return pageBoundSql;
    }

    public PageList buildPageList(List resultList, Page page, int totalRows) {
        PageList pageResult = new PageList();
        pageResult.addAll(resultList);
        pageResult.setPageNum(page.getPageNum());
        pageResult.setPageSize(page.getPageSize());
        pageResult.setTotalRows(totalRows);
        return pageResult;
    }

    public abstract String getPageSql(String sql, RowBounds rowBounds);

    public abstract String getCountSql(String sql);

}
