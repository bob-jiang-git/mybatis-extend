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
        if (name == null || "".equals(name.trim())) {
            return MYSQL.clazz;
        }
        for (DialectInfo info : DialectInfo.values()) {
            if (info.getName() == name.toLowerCase()) {
                return info.getClazz();
            }
        }
        throw new RuntimeException("不支持[" + name + "]方言的分页");
    }

    public String getName() {
        return name;
    }

    public Class getClazz() {
        return clazz;
    }
}
