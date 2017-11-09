package org.mybatis.extend.generic.service.impl;

import org.mybatis.extend.generic.mapper.GenericMapper;
import org.mybatis.extend.generic.model.BaseModel;
import org.mybatis.extend.generic.service.GenericService;
import org.mybatis.extend.page.param.Page;

import java.io.Serializable;
import java.util.List;

/**
 * GenericServiceImpl
 *
 * Created by Bob Jiang on 2017/2/13.
 */
public abstract class GenericServiceImpl<T extends BaseModel<PK>, PK extends Serializable, M extends GenericMapper<T, PK>>
        implements GenericService<T, PK> {

    public abstract M getGenericMapper();

    public int deleteByPrimaryKey(PK id) {
        return getGenericMapper().deleteByPrimaryKey(id);
    }

    public int insert(T model) {
        return getGenericMapper().insert(model);
    }

    public int insertSelective(T model) {
        return getGenericMapper().insertSelective(model);
    }

    public T selectByPrimaryKey(PK id) {
        return getGenericMapper().selectByPrimaryKey(id);
    }

    public List<T> selectAll() {
        return getGenericMapper().selectAll();
    }

    public int updateByPrimaryKey(T model) {
        return getGenericMapper().updateByPrimaryKey(model);
    }

    public int updateByPrimaryKeySelective(T model) {
        return getGenericMapper().updateByPrimaryKeySelective(model);
    }

    public List<T> select(T model) {
        return getGenericMapper().select(model);
    }

    public T selectOne(T model) {
        return getGenericMapper().selectOne(model);
    }

    public List<T> selectPageList(T model, Page page) {
        return getGenericMapper().selectPageList(model, page);
    }

    public Integer selectCount(T model) {
        return getGenericMapper().selectCount(model);
    }

    public int batchInsert(List<T> list) {
        return getGenericMapper().batchInsert(list);
    }

    public int deleteByIds(List<PK> list) {
        return getGenericMapper().deleteByIds(list);
    }
}
