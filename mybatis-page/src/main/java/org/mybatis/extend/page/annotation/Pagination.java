package org.mybatis.extend.page.annotation;

import org.mybatis.extend.page.constant.PageConstant;

import java.lang.annotation.*;

/**
 * 应用到的Mapper方法会执行分页
 *
 * Created by Bob Jiang on 2017/3/6.
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
public @interface Pagination {

    int pageSize() default PageConstant.PAGE_SIZE;

    String order();

}
