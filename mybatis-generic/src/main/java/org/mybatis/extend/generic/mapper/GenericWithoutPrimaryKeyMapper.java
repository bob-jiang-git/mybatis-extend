package org.mybatis.extend.generic.mapper;

import org.mybatis.extend.generic.mapper.base.InsertMapper;
import org.mybatis.extend.generic.mapper.base.SelectMapper;
import org.mybatis.extend.generic.model.BaseModel;

/**
 * GenericWithoutPrimaryKeyMapper
 *
 * Created by Bob Jiang on 18-5-14.
 */
public interface GenericWithoutPrimaryKeyMapper<T extends BaseModel>
        extends InsertMapper<T>, SelectMapper<T> {

}
