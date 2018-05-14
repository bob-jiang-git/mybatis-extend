package org.mybatis.extend.generic.service;

import org.mybatis.extend.generic.mapper.GenericWithoutPrimaryKeyMapper;
import org.mybatis.extend.generic.model.BaseModel;

/**
 * GenericWithoutPrimaryKeyService
 *
 * Created by Bob Jiang on 18-5-14.
 */
public interface GenericWithoutPrimaryKeyService<T extends BaseModel> extends GenericWithoutPrimaryKeyMapper<T> {
    
}
