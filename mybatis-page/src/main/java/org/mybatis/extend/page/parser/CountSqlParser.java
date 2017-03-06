package org.mybatis.extend.page.parser;


/**
 * Created by Bob Jiang on 2017/3/6.
 */
public class CountSqlParser {

    private static final String count = "count(0)";
    private static final String tableName = "t";

    public String getCountSql(String sql) {
        StringBuilder countSql = new StringBuilder();
        countSql.append("select ")
                .append(count)
                .append(" from (")
                .append(sql)
                .append(") ")
                .append(tableName);
        return countSql.toString();
    }

}
