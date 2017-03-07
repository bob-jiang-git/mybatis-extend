package org.mybatis.extend.page.constant;

import org.mybatis.extend.page.dialect.impl.MySqlGenericDialect;
import org.mybatis.extend.page.dialect.impl.OracleGenericDialect;

/**
 * Created by Bob Jiang on 2017/3/7.
 */
public enum DialectInfo {

    MYSQL("mysql", MySqlGenericDialect.class),
    ORACLE("oracle", OracleGenericDialect.class);

    private String name;
    private Class clazz;

    DialectInfo(String name, Class clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public static Class getClazzByName(String name) {
        Class clazz = MYSQL.clazz;
        if (name == null || "".equals(name.trim())) {
            return clazz;
        }
        for (DialectInfo info : DialectInfo.values()) {
            if (info.getName() == name.toLowerCase()) {
                return info.getClazz();
            }
        }
        return clazz;
    }

    public String getName() {
        return name;
    }

    public Class getClazz() {
        return clazz;
    }
}
