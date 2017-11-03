package org.mybatis.extend.generic.mapper.base;

import org.apache.ibatis.annotations.Param;
import org.mybatis.extend.generic.model.BaseModel;
import org.mybatis.extend.page.constant.PageConstant;
import org.mybatis.extend.page.param.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bob Jiang on 2017/11/2.
 */
public interface SelectMapper<T extends BaseModel<PK>, PK extends Serializable> {

    /**
     * 按主键查询
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(PK id);

    /**
     * 查询所有数据
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 按model属性做等值匹配
     *
     * @param model
     * @return
     */
    List<T> select(@Param("model") T model);

    /**
     * 按model属性做等值匹配, 结果大于一条时会报错
     *
     * @param model
     * @return
     */
    T selectOne(@Param("model") T model);

    /**
     * 分页
     *
     * @param model
     * @param page
     * @return
     */
    List<T> selectPageList(@Param("model") T model, @Param(PageConstant.PAGE_KEY) Page page);

    /**
     * 数量
     *
     * @param model
     * @return
     */
    Integer selectCount(@Param("model") T model);

}
