package org.mybatis.extend.page.interceptor;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.extend.page.Dialect;
import org.mybatis.extend.page.annotation.Pagination;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Bob Jiang on 2017/3/6.
 */
@Intercepts({
    @Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class PageInterceptor implements Interceptor {

    private static final String DEFAULT_DIALECT_CLASS = "org.mybatis.extend.page.dialect.MySqlGenericDialect";

    private Dialect dialect;
    private Field parametersField;

    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();

        MappedStatement ms          = (MappedStatement) args[0];
        Object parameter            = args[1];
        RowBounds rowBounds         = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];
        Executor executor           = (Executor) invocation.getTarget();
        BoundSql boundSql           = ms.getBoundSql(parameter);

        Pagination pagination = dialect.getPagination(ms.getId());
        if (pagination != null) {

            Map<String, Object> additionalParameters = (Map<String, Object>) parametersField.get(boundSql);
            doCount(executor, ms, parameter, rowBounds, resultHandler, boundSql, additionalParameters);

            CacheKey cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
            String pageSql = dialect.getPageSql(ms, boundSql, parameter, rowBounds);
        }
        return invocation.proceed();
    }

    private Long doCount(Executor executor, MappedStatement ms, Object parameter,
            RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql,
            Map<String, Object> additionalParameters) throws SQLException {

        CacheKey countKey = executor.createCacheKey(ms, parameter, RowBounds.DEFAULT, boundSql);
        countKey.update("_Count");
        MappedStatement countMs = (MappedStatement) ms.getCache().getObject(countKey);
        if (countMs == null) {
            countMs = ms;
            ms.getCache().putObject(countKey, ms);
        }

        String countSql = dialect.getCountSql(ms, boundSql, parameter, rowBounds);
        BoundSql countBoundSql = new BoundSql(ms.getConfiguration(), countSql, boundSql.getParameterMappings(), parameter);
        for (String key : additionalParameters.keySet()) {
            countBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
        }

        List countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
        Long count = (Long) countResultList.get(0);
        return count;
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        try {
            String dialectClass = properties.getProperty("dialect");
            if (dialectClass == null || "".equals(dialectClass)) {
                dialectClass = DEFAULT_DIALECT_CLASS;
            }

            Class<?> aClass = Class.forName(dialectClass);
            dialect = (Dialect) aClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            parametersField = BoundSql.class.getDeclaredField("additionalParameters");
            parametersField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
