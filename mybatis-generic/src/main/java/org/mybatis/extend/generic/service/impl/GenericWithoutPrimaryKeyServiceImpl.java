package org.mybatis.extend.generic.service.impl;

import org.mybatis.extend.generic.mapper.GenericWithoutPrimaryKeyMapper;
import org.mybatis.extend.generic.model.BaseModel;
import org.mybatis.extend.generic.service.GenericWithoutPrimaryKeyService;
import org.mybatis.extend.page.param.Page;

import java.util.List;

/**
 * GenericWithoutPrimaryKeyServiceImpl
 *
 * Created by Bob Jiang on 18-5-14.
 */
public abstract class GenericWithoutPrimaryKeyServiceImpl<T extends BaseModel, M extends GenericWithoutPrimaryKeyMapper<T>> implements GenericWithoutPrimaryKeyService<T> {

    public abstract M getGenericMapper();

    public int insert(T model) {
        return getGenericMapper().insert(model);
    }

    public int insertSelective(T model) {
        return getGenericMapper().insertSelective(model);
    }

    public int batchInsert(List<T> list) {
        return getGenericMapper().batchInsert(list);
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
}
