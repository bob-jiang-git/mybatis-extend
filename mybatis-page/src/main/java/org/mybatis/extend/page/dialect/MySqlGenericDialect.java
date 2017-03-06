package org.mybatis.extend.page.dialect;

import org.apache.ibatis.session.RowBounds;


/**
 * Created by Bob Jiang on 2017/3/6.
 */
public class MySqlGenericDialect extends GenericDialect {

    @Override
    public String getPageSql(String sql, RowBounds rowBounds) {
        StringBuilder sqlBuilder = new StringBuilder(sql.length() + 14);
        sqlBuilder.append(sql);
        if (rowBounds.getOffset() == 0) {
            sqlBuilder.append(" limit ");
            sqlBuilder.append(rowBounds.getLimit());
        } else {
            sqlBuilder.append(" limit ");
            sqlBuilder.append(rowBounds.getOffset());
            sqlBuilder.append(",");
            sqlBuilder.append(rowBounds.getLimit());
        }
        return sqlBuilder.toString();
    }
}
