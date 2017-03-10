package org.mybatis.extend.page.dialect.impl;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.extend.page.dialect.GenericDialect;
import org.mybatis.extend.page.util.ParseCountSql;
import org.mybatis.extend.page.util.StringTools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Bob Jiang on 2017/3/6.
 */
public class MySqlGenericDialect extends GenericDialect {

    @Override
    public String getPageSql(String sql, RowBounds rowBounds, CacheKey pageKey) {
        pageKey.update(rowBounds.getLimit());
        return StringTools.append(sql, " limit ", rowBounds.getOffset(), " , ", rowBounds.getLimit());
    }

    @Override
    public String getCountSql(String sql) {
        return ParseCountSql.parseSql(sql);
    }

}
