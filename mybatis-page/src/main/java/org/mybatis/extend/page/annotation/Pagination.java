package org.mybatis.extend.page.annotation;

import java.lang.annotation.*;

/**
 * 应用到的Mapper方法会执行分页
 *
 * Created by Bob Jiang on 2017/3/6.
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Pagination {

    boolean value() default true;

}
